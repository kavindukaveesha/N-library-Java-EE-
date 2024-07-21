<%@page import="java.util.List"%>
<%@page import="com.team2.models.User"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>N-Library Dashboard || Manage Students</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqk1wZxL+h7yzfrPLIXD" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="<%=assetsUrl.giveUrl(request, "styles/admin.css")%>"/>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .content {
            padding: 20px;
        }
        .btn-outline-secondary {
            background-color: #f0f0f0;
            color: #343a40;
            border-color: #343a40;
        }
        .btn-outline-secondary:hover {
            background-color: #343a40;
            color: #ffffff;
        }
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        table thead {
            background-color: #343a40;
            color: #ffffff;
        }
        table tbody tr:nth-child(odd) {
            background-color: #e9ecef;
        }
        table tbody tr:nth-child(even) {
            background-color: #ffffff;
        }
        .truncate {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        table tr th{
            font-size: .7rem;
            text-align: start;

        }
        table tr td{
            font-size: .8rem;
            text-align: start;

        }
        .mobileTable{
            display: none;
        }
        .desktopTable{
            overflow-x: auto;
        }

        /* Mobile devices and tablets (0px to 1024px) */
        @media only screen and (max-width: 1024px) {
          

        }

    </style>
</head>

<body>
    <%@ include file="/views/Admin/admincommon/sidebar.jspf" %>
    <div class="content">
        <%@ include file="/views/Admin/admincommon/header.jsp" %>
        <div id="message" class="container-fluid"></div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-6">
                    <h2 class="mb-5">Student Details</h2>
                </div>
                <div class="col-6 d-flex justify-content-end mb-5">
                    <a href="${pageContext.request.contextPath}/admin/students/" class="btn btn-outline-secondary">Refresh</a> 
                    <a href="new" class="btn btn-success ml-2">Add New Student</a>
                </div>
            </div>
            <div class="container mt-1"></div>
            <% List<User> students = (List<User>) request.getAttribute("students");%>  
            <% List<User> students1 = (List<User>) request.getAttribute("students1");%>  

            <%
                // Check if a specific student ID is provided
                if (students1 != null) {
                    students = students1;
            %>

            <%
                }
            %>


            <div class="row">
                <div class="col">
                    <%
                        if (students != null && !students.isEmpty()) {
                    %>
                    <p>All Students (<%=students.size()%>)</p>
                    <%
                        }
                    %>
                </div>
                <div class="col">
                    <form action="search" method="GET">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search" name="query" id="searchInput">
                            <div class="input-group-append">
                                <select class="custom-select btn btn-outline-secondary dropdown-toggle ml-2" name="selectType" id="selectType">
                                    <option value="studentId" selected>Student ID</option>
                                    <option value="firstName">First Name</option>
                                    <option value="lastName">Last Name</option>
                                    <option value="email">Email</option>
                                </select>
                                <button class="btn bg-dark text-white ml-2" type="submit">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div> 

            <% if (students != null && !students.isEmpty()) { %> 
            <table class="table table-hover table-striped desktopTable">
                <thead>
                    <tr>
                        <th>No</th>        
                        <th>StudentID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Profile Image</th>
                        <th>NIC</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% int count = 1; %>
                    <% for (User student : students) {%>
                    <tr>
                        <td><%= count++%></td>    
                        <td><%= student.getUserId()%></td>
                        <td class="truncate"><%= student.getFirstName()%></td>
                        <td class="truncate"><%= student.getLastName()%></td>
                        <td>
                            <img src="<%= assetsUrl.giveUrl(request, "DBImages/") + student.getImage()%>" style="width:5rem;height:5rem">
                        </td>
                        <td><%= student.getUserNic()%></td>
                        <td class="truncate"><%= student.getEmail()%></td>
                        <td><%= student.getPhoneNumber()%></td>
                        <td class="truncate"><%= student.getAddress()%></td>
                        <td>
                            <a href="#" onclick="return changeStatus('<%= student.getUserId()%>');"
                               class="<%= student.isActive() ? "btn btn-outline-success" : "btn btn-danger"%>" style="width: 3rem; font-size: .5rem; font-weight: bold;">
                                <%= student.isActive() ? "Active" : "Disable"%>
                            </a>
                        </td>
                        <td>
                            <a href="edit?id=<%= student.getUserId()%>" class="btn btn-outline-primary" style="width: 3.5rem; font-size: .5rem">Edit</a>
                            <a href="#" onclick="return confirmDelete('<%= student.getUserId()%>');" class="btn btn-outline-danger" style="width: 3.5rem; font-size: .5rem">Delete</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <div class="alert alert-info" role="alert">
                No students found.
            </div>
            <% }%>



        </div>
    
    </div>

    <!-- Include Bootstrap JavaScript -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
                                function confirmDelete(studentId) {
                                    swal({
                                        title: "Are you sure?",
                                        text: "Once deleted, you will not be able to recover this student!",
                                        icon: "warning",
                                        buttons: true,
                                        dangerMode: true,
                                    }).then(function (willDelete) {
                                        if (willDelete) {
                                            window.location.href = "<%= request.getContextPath()%>/admin/students/delete?studentId=" + studentId;
                                        }
                                    });
                                    return false;
                                }

                                function changeStatus(studentId) {
                                    swal({
                                        title: "Are you sure?",
                                        text: "Are you sure you want to change the status of the student?",
                                        icon: "warning",
                                        buttons: true,
                                        dangerMode: true,
                                    }).then(function (willDelete) {
                                        if (willDelete) {
                                            window.location.href = "<%= request.getContextPath()%>/admin/students/changeStatus?studentId=" + studentId;
                                        }
                                    });
                                    return false;
                                }

                                var status = "<%= request.getAttribute("status")%>";
                                var messageGet = "<%= request.getAttribute("message")%>";
                                if (status === "DeleteSuccess") {
                                    document.getElementById("message").innerHTML = "<p class='alert alert-success'>Student deleted successfully!</p>";
                                } else if (status === "DeleteFailed") {
                                    document.getElementById("message").innerHTML = "<p class='alert alert-danger'>Failed to delete student. Please try again.</p>";
                                } else if (status === "updateStatus") {
                                    document.getElementById("message").innerHTML = "<p class='alert alert-success'>" + messageGet + "</p>";
                                } else if (status === "errorUpadateStatus") {
                                    document.getElementById("message").innerHTML = "<p class='alert alert-danger'>" + messageGet + "</p>";
                                }






    </script>
</body>
</html>
