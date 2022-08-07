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
            <h1 style="color: white; font-family: sans-serif">Edit Account</h1><br/>
            <h2 style="color: red; font-family: sans-serif">${requestScope.err}</h2><br/>
            <c:set var="c" value="${requestScope.customer}"/>
            <form action="updateacc" method="post">
                <input name="id" value="${c.id}" hidden>
                Name:<input name="name" type="text" value="${c.name}">
                Phone:<input name="phone" type="text" value="${c.phone}">
                <input id="pw" name="pass" value="${c.pass}" hidden>
                Address:<input name="address" type="text" value="${c.address}">
                <input class="animated" type="submit" value='Update'>
            </form>
            <a class='forgot' href='home' style="font-size: 2vh;">Cancel?</a>
        </div>
    </body>
</html>