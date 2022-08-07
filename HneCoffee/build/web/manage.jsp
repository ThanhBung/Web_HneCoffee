<%@page import="dal.OrderDAO"%>
<%@page import="java.util.List"%>
<%@page import="dal.CategoryDAO"%>
<%@page import="model.Category"%>
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
            .menuManager{
                font-size: 3vh;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.admin == null}">
            <h1 style="color: red;text-align: center">Login by administrator please!!!</h1>
        </c:if>
        <c:if test="${sessionScope.admin != null}">
            <!--header-->
            <jsp:include page="header.jsp"></jsp:include>

            <div class="icon-bar" style="z-index: 2;position: absolute">
                    <!--icon Shopping Cart-->
                    <a href="showcart"><img src="Image/Cart.png" style="height: 60px; width: 60px; border-radius: 100px;margin-bottom: 1vh;"></a>
                    <c:if test="${requestScope.size!=null}">
                    <p> ${requestScope.size}</p>
                </c:if>
            </div>   

            <!--Body-->  
            <br>
            <div style="display: flex;justify-content: space-between;">

                <!--menu manage-->
                <div class="menuManager" style="margin-left: 5vw;">
                    <h1>Menu</h1>
                    <div style="display: flex; justify-content: flex-start; height: 5vh;">
                        <button onclick="window.location = 'manage?menu=1'" style="width: 7vw">Category</button>
                        <c:if test="${requestScope.menu==1}">&LeftTriangle;</c:if><br/>
                        </div>

                        <div style="display: flex; justify-content: flex-start;height: 5vh;">
                            <button onclick="window.location = 'manage?menu=2'" style="width: 7vw">Product</button>
                        <c:if test="${requestScope.menu==2}">&LeftTriangle;</c:if><br/>
                        </div>                    

                        <div style="display: flex; justify-content: flex-start;height: 5vh;">
                            <button onclick="window.location = 'manage?menu=3'" style="width: 7vw">Customer</button>
                        <c:if test="${requestScope.menu==3}">&LeftTriangle;</c:if><br/>
                        </div>

                        <div style="display: flex; justify-content: flex-start;height: 5vh;">
                            <button onclick="window.location = 'manage?menu=4'" style="width: 7vw">Income</button>
                        <c:if test="${requestScope.menu==4}">&LeftTriangle;</c:if><br/>
                        </div>                    
                    </div>
                    <!--product-->
                    <div style="width: 80%; margin-right:auto; margin-left: auto;">
                    <c:if test="${requestScope.menu==2}">
                        <c:set var="page" value="${requestScope.page}"/>
                        <div style="display: flex; justify-content: center;">
                            <div class="pagination" style="margin-right: 10vw">
                                <c:forEach begin="${1}" end="${requestScope.num}" var="p">
                                    <a class="${p==page?"active":""}" href="manage?menu=2&page=${p}">${p}</a>
                                </c:forEach>
                            </div>
                            <h1 style="text-align: center">Product</h1>
                        </div>

                        <table>
                            <tr>
                                <th>Id</th>
                                <th style="padding-left: 8vw;padding-right: 8vw">Name</th>
                                <th>Image</th>
                                <th>Status</th>
                                <th style="padding-left: 2vw;">Quantity</th>
                                <th>Price</th>
                                <th>Category Name</th>
                                <th style="padding-left: 2vw">Details</th>
                                <th>Change</th>
                            </tr>
                            <c:forEach items="${requestScope.data}" var="i">
                                <tr>
                                    <td>${i.proid}</td>
                                    <td>${i.proname}</td>
                                    <td><img src="Image/${i.image}" style="max-height: 100px;max-width: 100px"/></td>
                                    <td>${i.prostatus}</td>
                                    <td>${i.quantity}</td>
                                    <td><span style="color: red">${i.price}.000VND</span></td>
                                    <td>${i.cate.name}</td>
                                    <td><a href="detail?id=${i.proid}" style="color: #a800fd">Detail</a></td>
                                    <td style="padding-left: 1.2vw">                    
                                        <a href="#" onclick="doDelete('${i.proid}')" style="text-decoration: none;">
                                            <img src="Image/delete.png" style="height: 40px; width: 40px;">
                                        </a>
                                        <a href="updatepro?id=${i.proid}" style="text-decoration: none;">
                                            <img src="Image/edit.png" style="height: 40px; width: 40px;">
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <p style="text-align: center;"><a href="addpro" style=" color: red;"><img src="Image/addnew.png" style="height: 40px; width: 40px;"></a></p>
                            </c:if>

                    <!--category-->                    
                    <c:if test="${requestScope.menu==1}">
                        <h1 style="text-align: center">Category</h1>
                        <table style="width: 40%">
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th style="width: 10vw">Change</th>
                            </tr>
                            <c:forEach items="${requestScope.dataCat}" var="cat">
                                <tr>
                                    <td>${cat.id}</td>
                                    <td>${cat.name}</td>
                                    <td>${cat.description}</td>
                                    <td>
                                        <form action="deletepro" method="post">
                                            <button style="border: none; background: none;"><img src="Image/delete.png" style="height: 30px; width: 30px;"></button>
                                            <input name="cid" value="${cat.id}" type="hidden">
                                        </form>
                                        <a href="updatecat?cid=${cat.id}">
                                            <img src="Image/edit.png" style="height: 30px; width: 30px;">
                                        </a>
                                    </td>      
                                </tr>
                            </c:forEach> 
                        </table>
                        <p style="text-align: center;"><a href="newcat.jsp" style=" color: red;"><img src="Image/addnew.png" style="height: 30px; width: 30px;"></a></p>
                            </c:if>     


                    <!--customer-->
                    <c:if test="${requestScope.menu==3}">
                        <c:set var="page" value="${requestScope.page}"/>
                        <div style="display: flex; justify-content: center;">
                            <div class="pagination" style="margin-right: 10vw;">
                                <c:forEach begin="${1}" end="${requestScope.num}" var="p">
                                    <a class="${p==page?"active":""}" href="manage?menu=3&page=${p}">${p}</a>
                                </c:forEach>
                            </div>
                            <h1 style="text-align: center;">Customer</h1>
                        </div>
                        <table style="width: 60%">
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Password</th>
                                <th>Address</th>
                            </tr>
                            <c:forEach items="${requestScope.dataCus}" var="cus">
                                <tr>
                                    <td>${cus.id}</td>
                                    <td>${cus.name}</td>
                                    <td>${cus.phone}</td>
                                    <td>${cus.pass}</td>
                                    <td>${cus.address}</td>
                                    <td>
                                        <form action="deletecus">
                                            <input name="id" value="${cus.id}" type="hidden">
                                            <button style="border: none; background: none;">
                                                <img src="Image/delete.png" style="height: 30px; width: 30px;">
                                            </button>
                                        </form>
                                        <a href="updateacc?id=${cus.id}">
                                            <img src="Image/edit.png" style="height: 30px; width: 30px;">
                                        </a>
                                    </td>                        
                                </tr>
                            </c:forEach>
                        </table>
                        <p style="text-align: center;"><a href="register.jsp" style=" color: red;"><img src="Image/addnew.png" style="height: 30px; width: 30px;"></a></p>     
                            </c:if>

                    <!--Income-->
                    <c:if test="${requestScope.menu==4}">
                        <div style="display: flex; justify-content: space-between">
                            <h3>Number of Purchase: ${requestScope.numberOfPurchase}</h3><br/>
                            <h3>Quantity Purchased: ${requestScope.totalQuantity}</h3><br/>
                            <h3>Income: ${requestScope.income}.000VND</h3>
                        </div>
                        <div style="display: flex; justify-content: space-between; z-index: 0;position: relative">
                            <div id="piechart_3d" style="width: 800px; height: 400px;"></div>
                            <div id="piechart2_3d" style="width: 800px; height: 400px;"></div>
                        </div>
                    </c:if>
                </div>
            </div>  
            <!--footer-->
            <br/><br/><br/>
            <jsp:include page="footer.jsp"></jsp:include>
        </c:if>

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
            </div>
        </div>
    </body>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
                function doDelete(id) {
                    if (confirm("Are you sure to delete Product with id='" + id + "'?")) {
                        window.location = "deletepro?id=" + id;
                    }
                }
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

        
                google.charts.load("current", {packages: ["corechart"]});
                google.charts.setOnLoadCallback(drawChart);
                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Task', 'Hours per Day'],
                <c:set value="${requestScope.listCat}" var="listCat"/>                
                <c:set value="${requestScope.listQuantity}" var="listQuan" />                
                <c:set value="${requestScope.sizeInt}" var="size" />                
               <c:forEach begin="0" end="${size-1}" var="i">
                    [' ${listCat.get(i).getName()}',  ${listQuan.get(i)}],
                </c:forEach>
                ]);

                    var options = {
                        title: 'Statistics quantity of sold categories',
                        is3D: true,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
                    chart.draw(data, options);
                }
                
                google.charts.load("current", {packages: ["corechart"]});
                google.charts.setOnLoadCallback(drawChart2);
                function drawChart2() {
                    var data = google.visualization.arrayToDataTable([
                        ['Task', 'Hours per Day'],
                <c:set value="${requestScope.listCat}" var="listCat"/>                
                <c:set value="${requestScope.listIncome}" var="listIncome" />                
                <c:set value="${requestScope.sizeInt}" var="size" />                
               <c:forEach begin="0" end="${size-1}" var="i">
                    [' ${listCat.get(i).getName()}',  ${listIncome.get(i)}],
                </c:forEach>
                ]);

                    var options = {
                        title: 'Statistics income of sold categories',
                        is3D: true,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart2_3d'));
                    chart.draw(data, options);
                }                 
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>