<%@page import="com.team2.controller.utill.assetsUrl"%>
<!DOCTYPE html>
<html lang="en">

    <head> 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="<%=assetsUrl.giveUrl(request, "styles/client.css")%>"/>
        <title>N-Library||Login Form</title>
    </head>
    <style>
        body, html {
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f8f9fa;
        }
        .login-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
        }
        .login-image {
            width: 100%;
            text-align: center;
            margin-bottom: 20px;
        }
        .login-image img {
            max-width: 100%;
            border-radius: 10px;
        }
        .login-form {
            width: 100%;
        }
        .login-form h2 {
            margin-bottom: 20px;
        }
        .form-control {
            margin-bottom: 15px;
        }
        .btn-primary {
            background-color: blue;
            border-color: blue;
        }
        .success {
            background-color: green;
            color: white;
            font-weight: bold;
        }

        .error {
            background-color: red;
            color: white;
            font-weight: bold;
        }
        @media (min-width: 768px) {
            .login-container {
                flex-direction: row;

            }
            .login-image, .login-form {
                width: 50%;
            }
            .login-image {
                padding-right: 20px;
            }
            .login-form {
                padding-left: 20px;
            }
        }
    </style>
</head>

<body>
    <div class="login-container m-3 m-md-0">
        <div class="login-image">
            <img src="<%=assetsUrl.giveUrl(request, "Images/library1.png")%>" alt="Login Image">
        </div>
        <div class="login-form">
            <h2>Welcome to N-Library</h2>
            <div id="message" class="container mt-2"></div>

            <form action="login" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" placeholder="Enter email" required="">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" placeholder="Enter password" required="">
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </form>
        </div>
    </div>

    <script>
            var status = "<%= request.getAttribute("status")%>";
            if (status === "loginError") {
                document.getElementById("message").innerHTML = "<p class='error p-2'>Login Error Please Try Again!</p>";
            }
    </script>
</body>

</html>
