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
        <h1>Add a new Product</h1>
        <h3 style="color: red">${requestScope.error1}</h3>
        <h3 style="color: red">${requestScope.error2}</h3>
        <h3 style="color: red">${requestScope.errorNull}</h3>
            
        <h3>
            <form action="addpro" method="post">
                Enter id: <input type="number" name="id" placeholder="1,11,34,..."/><br/>
                Choose category:<br/> 
                <c:forEach items="${requestScope.category}" var="cat">
                    <input type="radio" name="cid" value="${cat.id}">${cat.name}<br/>
                </c:forEach>
                Enter name: <input type="text" name="name"/><br/>
                Enter image: <input type="text" name="image" placeholder="???.png"/><br/>
                Enter status: <input type="text" name="status" placeholder="hot or cold"/><br/>
                Enter quantity: <input type="number" name="quantity" placeholder="1,11,34,..."/><br/>
                Enter price: <input type="number" name="price" placeholder="35,42,50,..."/><br/>
                <input type="submit" value="Add now"/>
            </form>
        </h3>
    </body>
</html>
