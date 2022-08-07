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
        <c:if test="${requestScope.dataCat==null}">
            <h1>Update Product</h1>
            <c:set var="p" value="${requestScope.product}"/>
            <p>
                <form action="updatepro" method="post">
                        Enter id: <input type="number" name="id" value="${p.proid}" readonly/><br/>
                        Choose category:<br/> 
                        <c:forEach items="${sessionScope.category}" var="cat">
                            <input type="radio" name="cid" value="${cat.id}" ${p.catid==cat.id?"checked":""} >${cat.name}<br/>
                        </c:forEach>
                        Enter name: <input type="text" name="name" value="${p.proname}"/><br/>
                        Enter image: <input type="text" name="image" value="${p.image}"/><br/>
                        Enter status: <input type="text" name="status" value="${p.prostatus}"/><br/>
                        Enter quantity: <input type="number" name="quantity" value="${p.quantity}"/><br/>
                        Enter price: <input type="number" name="price" value="${p.price}"/><br/>
                        <input type="submit" value="Update now"/>
                </form>
            </p>
        </c:if>

        <c:if test="${requestScope.dataCat!=null}">
            <h1>Update Category</h1>
            <c:set var="c" value="${requestScope.dataCat}"/>
            <p>
                <form action="updatecat" method="post">
                        Enter id: <input type="number" name="id" value="${c.id}" readonly/><br/>
                        Enter name: <input type="text" name="name" value="${c.name}" /><br/>
                        Enter description: <input type="text" name="description" value="${c.description}"/><br/>
                        <input type="submit" value="Update now"/>
                </form>
            </p>
        </c:if> 
            
    </body>
</html>
