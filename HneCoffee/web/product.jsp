<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HneCoffee</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            h1{
                font-size: 7vh;
                margin-top: 5vh;
                color: #7e3d11;
                font-family: cursive;
                text-align: center;
            }

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
            }

            table td{
                border-bottom: 1px solid;
                padding-left: 2vh;
                padding-right: 2vh;
                font-size: 2.5vh;
            }

            .pagination {
                display: inline-block;
                margin-left: 8vh;
            }
            .pagination a , h3{
                color: black;
                font-size: 3vh;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                font-family: cursive;
            }
            .pagination a.active {
                background-color: #7e3d11;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: #ffb100;
            }
        </style>
    </head>
    <body>
        <!--header-->
        <jsp:include page="header.jsp"></jsp:include>

            <div class="icon-bar">
                <!--icon Shopping Cart-->
                <a href="showcart"><img src="Image/Cart.jpg" style="height: 60px; width: 60px; border-radius: 100px;margin-bottom: 1vh;"></a><br/>
                <p> ${requestScope.size}</p>
        </div>

        <!--icon Account-->
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
        <!--Body-->  
        <c:if test="${requestScope.cid == 0}">
            <h1>List of all product</h1>
        </c:if>
        <c:if test="${requestScope.cid == 1}">
            <h1>List of <span style="color: red">Coffee</span></h1>
        </c:if>
        <c:if test="${requestScope.cid == 2}">
            <h1>List of <span style="color: red">Freeze</span></h1>
        </c:if>
        <c:if test="${requestScope.cid == 3}">
            <h1>List of <span style="color: red">Milk Tea</span></h1>
        </c:if>

        <c:set var="page" value="${requestScope.page}"/>
        <div class="pagination">
            <p style="font-size: 4vh; font-family: cursive">Page&nbsp;</p>
            <c:forEach begin="${1}" end="${requestScope.num}" var="p">
                <a class="${p==page?"active":""}" href="product?cid=${requestScope.cid}&page=${p}">${p}</a>
            </c:forEach>
            <p style="padding-top: 1.6vh;font-size: 3vh;color: chocolate">(${requestScope.listSize}&nbsp;items)</p> 
        </div>
        <form name="f" action="" method="post">
            <input type="hidden" name="num" value="1"/>    
            <table>
                <tr>
                    <th>Id</th>
                    <th style="padding-left: 8vw;padding-right: 8vw">Name</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Category Name</th>
                    <th>Details</th>
                    <th>Add to ShoppingCart</th>
                </tr>
                <c:forEach items="${requestScope.data}" var="i">
                    <tr>
                        <td>${i.proid}</td>
                        <td>${i.proname}</td>
                        <td><img class="zoom" src="Image/${i.image}" style="max-height: 100px;max-width: 100px"/></td>
                    <c:if test="${i.prostatus == 'cold'}">
                        <td><img src="Image/cold.png" style="width:3vw;border-radius: 100%"></td>
                    </c:if>
                    <c:if test="${i.prostatus == 'hot'}">
                        <td><img src="Image/hot.png" style="width:3vw;border-radius: 100%"></td>
                    </c:if>
                        <td>${i.quantity}</td>
                        <td><span style="color: red">${i.price}.000VND</span></td>
                        <td>${i.cate.name}</td>
                        <td><a href="detail?id=${i.proid}" style="color: #a800fd"><img src="Image/detail.png" style="width:2vw;"></a></td>
                        <td><a onclick="buy('${i.proid}')" ><img src="Image/Cart.png" style="width:3vw;border-radius: 100%"></a></td>
                    </tr>
                </c:forEach>
            </table>                
        </form>

        <!--footer-->
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
    <script type="text/javascript">
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
        function buy(id) {
            var m = document.f.num.value;
            document.f.action = "buy?id=" + id + "&num=" + m;
            document.f.submit();
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>