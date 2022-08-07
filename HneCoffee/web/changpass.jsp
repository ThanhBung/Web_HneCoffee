<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HneCoffee</title>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" media="screen" href="css/register.css"/> 
    </head>
    <body>
        <div class="login">
            <h1 style="color: white; font-family: sans-serif">Change Password</h1>
            <p style="opacity: 1; font-weight: bold;">You should use a strong password that you have not used anywhere else.</p><br/>
            <h2 style="color: red; font-family: sans-serif">${requestScope.error}</h2><br/>
            <form action="updatepass" method="post">
                Current Password:<input name="currentpass" type="password">
                New Password:<input name="newpass1" type="password">
                Again New Password:<input name="newpass2" type="password">
                <input class="animated" type="submit" value='Update'>
            </form>
                <a class='forgot' href='home' style="font-size: 2vh">Cancel?</a>
        </div>
    </body>
</html>