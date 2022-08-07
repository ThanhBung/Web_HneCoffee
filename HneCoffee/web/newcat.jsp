<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HneCoffee</title>
        <style >
            body{
                font-family: cursive;
                background-color: #a7e6fa;
            }
            h1{
                text-align: center;
                color: brown;
            }
        </style>
    </head>
    
    <body>
        
        <!--Product-->
        <h1>Add a new Category</h1>
        <h3 style="color: red">${requestScope.error1}</h3>
        <h3 style="color: red">${requestScope.error2}</h3>
        <h3 style="color: red">${requestScope.errorNull}</h3>
            
        <h3>
            <form action="addcat" method="post">
                Enter id: <input type="number" name="id" placeholder="1,2,3.."/><br/>
                Enter name: <input type="text" name="name" placeholder="tea,coffee,..."/><br/>
                Enter description: <input type="text" name="description" placeholder="..."/><br/>
                <input type="submit" value="Add now"/>
            </form>
        </h3>
    </body>
</html>
