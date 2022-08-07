<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HneCoffee</title>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Sofia' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" media="screen" href="css/register.css" /> 
    </head>
    <body>
        <div class='login'>
            <h1 style="color: white; font-family: sans-serif">Register Form</h1><br/>
            <h2 style="color: red; font-family: sans-serif">${requestScope.err}</h2><br/>
            <form action="register" method="post">
                Name:<input name='name' placeholder='Yourname' type='text' value="${param.name}">
                Phone:<input name='phone' placeholder='Phone' type='text' value="${param.phone}">
                Password:<input id='pw' name='pass' placeholder='Password' type='password'>
                Address:<input name='address' placeholder='Address' type='text' value="${param.address}">
                <input class='animated' type='submit' value='Register'>
            </form>
            <a class='forgot' href='login' style="font-size: 2vh;">Already have an account?</a>
        </div>
    </body>
</html>