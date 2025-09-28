<style>
    .dropdown-container {
        position: relative;
        display: inline-block;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        right: .2rem;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        z-index: 1;
    }

    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropdown-content a:hover {background-color: #f1f1f1}

    .show {display: block;}
</style>
<%@page import="com.team2.controller.utill.assetsUrl"%>
<%
    // Check if the user is logged in
    if (session == null || session.getAttribute("loggedInUser") == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    } else {
        // Get user attributes if logged in
        String userName = (String) session.getAttribute("username");
        String userImage = (String) session.getAttribute("userImage");
        int userId = (int) session.getAttribute("userId");

%>
<div class="adminName">
    <input type="hidden" name="userId" value="${userId}">
    <h2><%=userName%></h2>

    <div class="dropdown-container">
        <a href="javascript:void(0)" onclick="toggleDropdown()">
            <img 
                src="<%= assetsUrl.giveUrl(request, "DBImages/") + userImage%>" alt="User Image" 
                class="img-fluid rounded-circle" width="50">

        </a>
        <div id="dropdown" class="dropdown-content">
            <a href="/profile">Profile</a>
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</div>
<%    }
%>

<script>
    function toggleDropdown() {
        document.getElementById("dropdown").classList.toggle("show");
    }

    // Close the dropdown if the user clicks outside of it
    window.onclick = function (event) {
        if (!event.target.matches('.dropdown-container a img')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            for (var i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>
