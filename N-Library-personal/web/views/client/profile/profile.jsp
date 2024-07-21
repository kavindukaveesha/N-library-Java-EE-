<%@page import="com.team2.models.User"%>
<%@page import="com.team2.controller.utill.assetsUrl"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>N-Library||Profile</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=assetsUrl.giveUrl(request, "styles/client.css")%>"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }


        .profileContainer {
            height: auto;
            margin-top: 3%; 
            margin-bottom:  2%;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            background-color: #fff;
        }

        .profile-header {
            margin-left: 3rem;
            display: flex;
            align-items: center;
            margin-bottom: 30px;
        }

        .profile-image {
            width: 12%;
            height: 12%;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 30px;
            border: 4px solid #fff; /* White border for image */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            cursor: pointer; /* Make the image clickable */
        }

        .profile-info h3 {
            font-size: 24px;
            margin-bottom: 10px;
            color: #333;
        }

        .profile-info p {
            font-size: 16px;
            color: #555;
        }

        .profile-details {
            display: flex;
            flex-wrap: wrap;
        }

        .profile-details .form-group {
            width: 100%;
            margin-bottom: 20px;
        }

        .profile-details .form-group label {
            font-weight: bold;
            color: #333;
        }

        .profile-details .form-control {
            border-radius: 5px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .profile-details .form-control:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .change-password-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .change-password-button:hover {
            background-color: #0056b3;
        }

        .change-password-form {
            margin-left: 1rem;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }

        .change-password-form .form-group {
            margin-bottom: 15px;
        }

        .change-password-form .form-control {
            border-radius: 5px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .change-password-form .form-control:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .success {
            color: green;
        }

        .error {
            color: red;
        }
        .image-upload-form {
            display: none; 
            position: absolute; 
            top: 30%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .image-upload-form input[type="file"] {
            display: block;
            width: 100%;
            margin-bottom: 10px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .image-upload-form button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
        }

        .image-upload-form button:hover {
            background-color: #0056b3;
        }
    </style>
    <body>

        <div class="">
            <%@ include file="/views/client/clientCommon/header.jspf" %>

            <!-- Profile Page -->
            <% User loggedInUser = (User) request.getAttribute("loggedInUser"); %>

            <% if (loggedInUser != null) {%>

            <div class="container profileContainer">

                <div class="row">
                    <div class="col-md-12">
                        <div class="profile-header">
                            <img src="<%= assetsUrl.giveUrl(request, "DBImages/") + loggedInUser.getImage()%>" class="profile-image" alt="Profile Image" id="profileImage">
                            <div class="profile-info">
                                <h3><%= loggedInUser.getFirstName()%> <%= loggedInUser.getLastName()%></h3>
                                <p><%= loggedInUser.getEmail()%></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="image-upload-form" id="imageUploadForm">
                    <form action="${pageContext.request.contextPath}/student/profile/change-profile-picture" method="post" enctype="multipart/form-data">
                        <input type="file" name="profileImage" accept="image/*" id="imageInput">
                        <button type="submit">Update Image</button>
                    </form>
                </div>

                <div class="row mx-4">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="firstName">First Name</label>
                            <input type="text" class="form-control" id="firstName" value="<%= loggedInUser.getFirstName()%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control" id="lastName" value="<%= loggedInUser.getLastName()%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="contactNumber">Contact Number</label>
                            <input type="text" class="form-control" id="contactNumber" value="<%= loggedInUser.getPhoneNumber()%>" disabled>
                        </div>
                    </div>  
                    <div class="col-6">

                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" value="<%= loggedInUser.getEmail()%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="nic">NIC</label>
                            <input type="text" class="form-control" id="nic" value="<%= loggedInUser.getUserNic()%>" disabled>
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <textarea class="form-control" id="address" name="address" required style="background-color: #d5dbe0"><%= loggedInUser.getAddress()%></textarea>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <button type="button" class="btn btn-warning change-password-button mx-3" id="changePassword">Change Password</button>
                            </div>
                        </div>           
                        <div class="col-6">
                            <div id="changePasswordForm" class="d-none change-password-form">
                                <form action="${pageContext.request.contextPath}/student/profile/change-password" method="post">
                                    <div class="form-group">
                                        <label for="currentPassword">Current Password</label>
                                        <input type="password" class="form-control" id="currentPassword" placeholder="Enter current password" required name="currentPassword">
                                    </div>
                                    <div class="form-group">
                                        <label for="newPassword">New Password</label>
                                        <input type="password" class="form-control" id="newPassword" placeholder="Enter new password" required name="newPassword">
                                    </div>
                                    <button type="submit" class="btn btn-primary">Update Password</button>
                                </form>
                            </div>
                        </div>
                        <div class="mt-3" id="message"></div>

                    </div>





                </div>
            </div>
        </div>


    </div>
    <% } else { %>
    <p>You are not logged in. Please <a href="/login">login</a> to view your profile.</p>
    <% }%>
    <%@ include file="/views/client/clientCommon/footer.jspf" %>


    <script>

        $(document).ready(function () {
            $("#changePassword").click(function () {
                $("#changePasswordForm").toggleClass("d-none");
            });

            // Image Upload Form Handling
            $("#profileImage").click(function () {
                $("#imageUploadForm").toggle(); // Toggle the form's visibility
            });

            //  Hide the image upload form if the user closes it 
            $("#imageUploadForm").on('hidden.bs.modal', function () {
                // Reset the input field (optional)
                $('#imageInput').val('');
            });

            // Get the status message from the server-side
            var status = "${status}";

            // Check the status and display the appropriate message
            if (status === "passwordUpdateSuccess") {
                document.getElementById("message").innerHTML =
                        "<p class='success'>Successfully update Your Password.</p>";
            } else if (status === "passwordUpdateFailed") {
                document.getElementById("message").innerHTML =
                        "<p class='error'>Failed to update Your Password. Please try again.</p>";
            }

        });


    </script>

</body>


</html>  