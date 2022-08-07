<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HneCoffee</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <style>
            table{
                width: 100%;
                margin-top: 5vh;
                margin-bottom: 5vh;
                margin-left: auto;
                margin-right: auto;
                text-align: center;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            
            table th{
                border-bottom: 1px solid;
                padding-left: 2vh;
                padding-right: 2vh;
                font-size: 3vh;
                width: 10vw;
            }

            table td{
                border-bottom: 1px solid;
                padding-left: 2vh;
                padding-right: 2vh;
                font-size: 2.5vh;
            }        
    </style>
    <body>
        <!--header-->
        <jsp:include page="header.jsp"></jsp:include>
        <br/>

        <div style="z-index: 1;position: relative">
            <!--icon Shopping Cart-->
            <div class="icon-bar">
                <a href="showcart"><img src="Image/Cart.jpg" style="height: 60px; width: 60px; border-radius: 100%;margin-bottom: 1vh;"></a>
                <p> ${requestScope.size}</p>
            </div>   
            <!--login-->
            <div class="action">
                <div class="profile" onclick="menuToggle();">
                    <img class="zoom" src="Image/login.png" alt="" style="height: 60px; width: 60px;">
                </div>

                <div class="menu">
                    <!--login with Admin-->
                    <c:if test="${sessionScope.admin != null}">
                        <h3>Hello,&nbsp;<span style="color: red;">Admin</span>!</h3>
                        <ul>
                            <li><a href="manage?menu=1">Manage</a></li>
                            <li><a href="updateacc?id=${sessionScope.admin.getId()}">Edit Account</a></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </c:if>
                    <!--logout-->
                    <c:if test="${sessionScope.admin == null && sessionScope.acc == null}">
                        <h3>User Account</h3>
                    </c:if>
                    <c:if test="${sessionScope.admin == null && sessionScope.acc == null}">
                        <ul>
                            <li><a href="login">Login</a></li>
                        </ul>
                    </c:if>
                    <!--login with Customer-->
                    <c:if test="${sessionScope.acc != null}">
                        <h3>Hello,&nbsp;<span style="color: #eaf800;">${sessionScope.acc.getName()}</span>!</h3>
                    </c:if>
                    <c:if test="${sessionScope.acc != null}">
                        <ul>
                            <li><a href="updateacc?id=${sessionScope.acc.getId()}">Edit Account</a></li>
                            <li><a href="updatepass?id=${sessionScope.acc.getId()}">Change Password</a></li>
                            <li><a href="vieworder">View Order</a></li>
                            <li><a href="logout">Logout</a></li>
                        </ul>
                    </c:if>    
                </div>
            </div>
        </div>
         
        <!--body-->   
        <h1 style="text-align: center;">View Order of <span style="color: red">'${sessionScope.acc.name}'</span></h1>
        <table>
            <tr>
                <th>Name item</th>
                <th>Image item</th>
                <th>Status item</th>
                <th>Quantity item</th>
                <th>Price item</th>
                <th>Money has to pay</th>
                <th>Date</th>
                <th>Address </th>
            </tr>
            <c:forEach items="${requestScope.data}" var="list">
                <tr>
                    <td>${list.proItem.proname}</td>
                    <td><img src="Image/${list.proItem.image}" style="max-height: 100px;max-width: 100px"></td>
                    <td>${list.proItem.prostatus}</td>
                    <td>${list.proItem.quantity}</td>
                    <td>${list.proItem.price}</td>
                    <td>${list.totalMoney}</td>
                    <td>${list.dateOrder}</td>
                    <td>${list.cusAddress}</td>
                </tr>
            </c:forEach>
        </table>
            
        <br/>      
        <!--footer-->
        <jsp:include page="footer.jsp"></jsp:include>            
    </body>

    <script>
        function myFunction() {
            var x = document.getElementById("myTopnav");
            if (x.className === "topnav") {
                x.className += " responsive";
            } else {
                x.className = "topnav";
            }
        }
        function menuToggle() {
            const toggleMenu = document.querySelector('.menu');
            toggleMenu.classList.toggle('active');
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
