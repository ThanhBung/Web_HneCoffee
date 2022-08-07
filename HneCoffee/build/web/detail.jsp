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
            table{
                width: 100%;
                margin-top: 5vh;
                margin-bottom: 5vh;
                margin-left: auto;
                margin-right: auto;
                text-align: center;
            }

            table td ,th{
                border-bottom: 1px solid;
                padding-left: 2vh;
                padding-right: 2vh;
                font-size: 3vh;
            }

            .value-button {
                display: inline-block;
                border: 1px solid #ddd;
                width: 40px;
                height: 40px;
                text-align: center;
                vertical-align: middle;
                background: #0099ff;
                -webkit-touch-callout: none;
                -webkit-user-select: none;
                -khtml-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
            }

            .value-button:hover {
                cursor: pointer;
            }

            form #decrease {
                margin-right: -4px;
                border-radius: 8px 0 0 8px;
            }

            form #increase {
                margin-left: -4px;
                border-radius: 0 8px 8px 0;
            }

            form #input-wrap {
                margin: 0px;
                padding: 0px;
            }

            input#number {
                text-align: center;
                border: none;
                border-top: 1px solid #ddd;
                border-bottom: 1px solid #ddd;
                margin: 0px;
                width: 40px;
                height: 40px;
            }

            input[type=number]::-webkit-inner-spin-button,
            input[type=number]::-webkit-outer-spin-button {
                -webkit-appearance: none;
                margin: 0;
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
        <c:set value="${requestScope.data}" var="i" />
        <h1 style="font-family: cursive;text-align: center;;font-family: cursive">
            ${i.proname}
        </h1>
        <br/>
        <div style="display: flex; justify-content: center">
            <img class="img-fluid" src="Image/${i.image}" style="max-height:300px;max-width: 300px"/>
            <div>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Status</th>
                        <th>The remaining quantity</th>
                        <th>Cost</th>
                    </tr>
                    <tr>
                        <td>${i.proname}</td>
                        <td>${i.prostatus}</td>
                        <td>${i.quantity}</td>
                        <td>${i.price}.000VND</td>
                    </tr>
                </table>
                <form name="f" action="" method="post" style="display: flex; justify-content: center">
                    <div style="padding-right: 7vw; padding-top: 4vh;">
                        <div class="value-button" id="decrease" onclick="decreaseValue()" value="Decrease Value"><p style="font-size: 25px">-</p></div>
                        <input type="number" id="number" value="0" name="num" style="height: 37px; width: 36px;"/>
                        <div class="value-button" id="increase" onclick="increaseValue()" value="Increase Value"><p style="font-size: 25px">+</p></div>  
                    </div>
                    <div>
                        <p style="font-weight: bold">Add to Shopping Cart</p>
                        <a onclick="buy('${i.proid}')" style="padding-left: 4vw"><img src="Image/Cart.png" style="width:50px;border-radius: 100%"></a>
                    </div>
                </form>
            </div>
        </div>
        <hr style="margin-left: 50px; margin-right: 50px"/>
        <br/>
        <h3 style="text-align: center">Some drinks you might like?</h3>
        <br/>
        <div style="display: flex; justify-content: center">
            <c:forEach items="${requestScope.listTop}" var="li">
                <div style="padding-right: 3vw; padding-left: 3vw">
                    <a href="detail?id=${li.proid}"><img src="Image/${li.image}" style="height:200px;width: 200px;"></a><br/>
                    <p style="font-size: 3vh;text-align: center;">${li.proname}</p>
                </div>
            </c:forEach>
        </div>
        <br/>
                            
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
        function increaseValue() {
            var value = parseInt(document.getElementById('number').value, 10);
            value = isNaN(value) ? 0 : value;
            value++;
            document.getElementById('number').value = value;
        }

        function decreaseValue() {
            var value = parseInt(document.getElementById('number').value, 10);
            value = isNaN(value) ? 0 : value;
            value < 1 ? value = 1 : '';
            value--;
            document.getElementById('number').value = value;
        }
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>