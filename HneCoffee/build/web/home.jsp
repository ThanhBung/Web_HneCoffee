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
    <body>
        <!--header-->
        <jsp:include page="header.jsp"></jsp:include>

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

        <!--Body-->

        <!--slider-->
        <div style="max-width: 100%;">
            <div id="carouselExampleControls" class="carousel slide img-fluid" data-ride="carousel">
                <div class="carousel-inner" >
                    <div class="carousel-item ">
                        <img class="d-block w-100 img-fluid" src="Image/slider1.jpg" style="height: 550px" alt="First slide">
                    </div>
                    <div class="carousel-item active">
                        <img class="d-block w-100 img-fluid" src="Image/slider2.jpg" style="height: 550px" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100 img-fluid" src="Image/slider3.jpg" style="height: 550px" alt="Third slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100 img-fluid" src="Image/slider4.jpg" style="height: 550px" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <br/>
        <h1 style="text-align: center; color: red">Sản phẩm hot năm nay</h1>
        <br/>
        <div>
            <c:forEach items="${requestScope.listBestNew}" var="listNew">
                <div style="padding-right: 3vw; padding-left: 3vw;width: 25%;text-align: center">
                    <a href="detail?id=${listNew.proid}"><img src="Image/${listNew.image}" style="height:200px;width: 200px;"></a><br/>
                    <p style="font-size: 3vh;">${listNew.proname}</p>
                </div>
                <br/>
            </c:forEach>
        </div>          
        
        <br/>
        <h1 style="text-align: center;font-family: cursive; color: red">All the best selling items this year</h1>
        <br/>
        <div style="display: flex; justify-content: center">
            <c:forEach items="${requestScope.listBestSell}" var="listOrdDe">
                <c:forEach items="${requestScope.data}" var="listPo">
                    <c:if test="${listOrdDe.pid==listPo.proid}">
                        <div style="padding-right: 3vw; padding-left: 3vw;width: 25%;text-align: center">
                            <a href="detail?id=${listPo.proid}"><img class="zoom" src="Image/${listPo.image}" style="height:200px;width: 200px;"></a><br/>
                            <p style="font-size: 3vh;">${listPo.proname}</p>
                        </div>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
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
