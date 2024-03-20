<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>

</head>
<body>

	<center>
		<h1>Student CRUD Using AJAX</h1>
	</center>

	<button id="gettime">Get Time</button>
	<h2 id="printDateTime"></h2>



	<div class="container mt-3">
		<form id="studentForm" name="studentform">
			<div class="row">
				<div class="col-6">
					<h3>Student Form</h3>


					<div class="row" id="idfield">
						<div class="col">
							<div class="form-group">
								<label for="id">ID</label> <input type="text"
									readonly="readonly" class="form-control" id="id" name="id">
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="name">Name</label> <input type="text"
									class="form-control" id="name" name="name"
									placeholder="Enter Name">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="age">Age</label> <input type="text"
									class="form-control" id="age" name="age"
									placeholder="Enter Age">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="course">Course</label> <input type="text"
									class="form-control" id="course" name="course"
									placeholder="Enter Course">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<button type="button" id="saveStudent" class="btn btn-primary">Submit</button>
							<button type="button" id="updateStudent"
								onclick="updateStudentbtn()" class="btn btn-primary">Update</button>
						</div>
					</div>
				</div>
				<div class="col-6">

					<h3>Student Record</h3>

					<br>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
								<th scope="col">Age</th>
								<th scope="col">Course</th>
								<th scope="col">Edit</th>
								<th scope="col">Delete</th>
							</tr>
						</thead>
						<tbody id="studentTable">

							<!-- table row came from AJAX -->
						</tbody>
					</table>


				</div>


			</div>
		</form>
	</div>

	<script>
    $(document).ready(function() {
    	
    	
        alert("Document is ready");
        $("#updateStudent").hide();
        $("#saveStudent").show();
        $("#idfield").hide();
        getAllrecord(); //calling getAllrecord method
        
        //get date and time
        $("#gettime").click(function(){
        	$.ajax({
        		type : "GET",
				url : 'date',
				success : function(data) {
					$('#printDateTime').html(data);
				}
			});
        });
        
        //insert data into database
        $('#saveStudent').click(function() {
			$.ajax({
				type : "POST",
				url : "insertStudent",
				data : {
					name : $("#name").val(),
					age : $("#age").val(),
					course : $("#course").val()
				},
				success : function(result) {
					alert("Insert Sucessfully");
					getAllrecord();
					$('#studentForm')[0].reset()
				},
				error : function(err) {
					alert("error is" + err)
				}
			});
		});
               
});
    
    
  //show all records in the table formate
    function getAllrecord() {
          $.ajax({
          type: "GET",
          url: "getAllStudent",
          success: function(students) {
              // Clear existing table rows
              $("#studentTable").empty();

              // Append new rows with fetched data
              students.forEach(function(student) {
                  var row = "<tr>" +
                      "<td>" + student.id + "</td>" +
                      "<td>" + student.name + "</td>" +
                      "<td>" + student.age + "</td>" +
                      "<td>" + student.course + "</td>" +
                      "<td><input type='button' class='btn btn-warning' onclick='editStudent(" + student.id + ")' value='Edit'></td>" +
                      "<td><input type='button' class='btn btn-danger' onclick='deleteStudent(" + student.id + ")' value='Delete'></td>" +
                      "</tr>";
                  $("#studentTable").append(row);
              });
          },
          error: function(err) {
              console.error("Error fetching students: ", err);
          }
      });
  }
    
    
    // Function to handle editing a student record
    function editStudent(id) {
        // Add your code here to handle editing the student record with the given ID
        console.log("Edit button clicked for student ID:", id); 
        $.ajax({
	        type: "GET",
	        url: "getSingleStudent/" + id,
	        dataType: 'json',
	        success: function(response) {
	            $("#id").val(response.id);
	            $("#name").val(response.name);
	            $("#age").val(response.age);
	            $("#course").val(response.course);
	            
	            $("#saveStudent").hide();
	            $("#updateStudent").show();
	            $("#idfield").show();
	        },
	        error: function(err) {
	            alert("Error: " + err);
	        }
	    });

    }
    
    //update form 
    function updateStudentbtn() {

		$.ajax({
			type: "POST", // Make sure it's POST
			url : "updateStudent",
			data : {
				id : $("#id").val(),
				name : $("#name").val(),
				age : $("#age").val(),
				course : $("#course").val()
			},
			success : function(result) {
				
				console.log("Update success:", result); // Log the response
				getAllrecord();
				$('#saveStudent').show();
				$('#updateStudent').hide();
				$('#idfield').hide();
				$('#studentForm')[0].reset();
			},
			error : function(xhr, status, error) {
				 var errorMessage = xhr.responseText; // Get the error message from the response
				    alert("Error updating data: " + errorMessage);
			}
		});

	}
    
    //delete function
    	function deleteStudent(id) {
		$.ajax({
			url : "deleteStudent/" + id,
			success : function(response) {
				getAllrecord();
			},
			error : function(err) {
				alert("error is" + err)
			}
		});
	}

    
    
</script>
</body>
</html>