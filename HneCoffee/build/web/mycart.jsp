<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HneCoffee</title>
        <style>
            .tr{
                text-align: right;
            }
            a{
                text-decoration: none;
                color: chocolate;
                font-size: 22px;
                font-weight: bold;
            }
            table tr th,td{
                text-align: center;
            }
        </style>
    </head>
    <body>      
        <div style="display: flex;justify-content: space-between;">
            <!--table-->
            <div>
                <div style="display: flex;justify-content: space-between;">
                    <h1>Shopping Cart<small style="font-size: 20px;color: chocolate">(${requestScope.cart.items.size()} items)</small></h1>
                </div>
                <hr/>
                <table style="font-size: 24px;">
                    <tr>
                        <th>No</th>
                        <th style="width: 20vw">Name</th>
                        <th >Image</th>
                        <th>Price</th>
                        <th style="width: 10vw">Quantity</th>
                        <th style="width: 10vw">Total money</th>
                        <th style="width: 10vw">Remove</th>
                    </tr>
                    <c:set var="o" value="${requestScope.cart}"/>
                    <c:set var="tt" value="0"/>
                    <c:forEach items="${o.items}" var="i">
                        <c:set var="tt" value="${tt+1}"/>
                        <tr>
                            <td>${tt}</td>
                            <td>${i.product.proname}</td>
                            <td><img class="zoom" src="Image/${i.product.image}" style="max-height: 100px;max-width: 100px"/></td>
                            <td class="tr">${i.price}.000VND</td>
                            <td style="text-align: center">
                                <button style="background: none;border: none;"><a href="process?num=-1&id=${i.product.proid}"><span style="color: black;font-size: 30px">-</span></a></button>
                                ${i.quantity}
                                <button style="background: none;border: none;"><a href="process?num=1&id=${i.product.proid}"><span style="color: black;font-size: 25px">+</span></a></button>
                            </td>
                            <td class="tr">
                                ${(i.price*i.quantity)}.000VND
                            </td>
                            <td style="text-align: center">
                                <form action="process" method="post">
                                    <input type="hidden" name="id" value="${i.product.proid}"/>
                                    <button style="background: white;border: none;"><img src="Image/delete.png" style="height: 25px;width:25px"></button>   
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
                
            <!--total-->  
            <div style="margin-right: 0.5vw; width: 25vw; 
                        background: lightgrey; border-radius: 5%;
                        padding-left: 1vw; padding-right: 1vw; padding-bottom: 3vh;
                        right: 1%;position: fixed;">
                <h1>Summary</h1>
                <hr/>
                <div style="display: flex;justify-content: space-between; 
                     font-size: 20px; font-weight: bold">
                    <div>
                        <p>ITEMS ${requestScope.cart.items.size()}</p>
                        <p>SHIPPING</p>
                    </div>
                    <p>
                        <c:if test="${o.totalMoney >= 1000}">
                            ${requestScope.total}.000VND
                        </c:if>
                        <c:if test="${o.totalMoney < 1000}"> 
                            ${o.totalMoney}.000VND
                        </c:if>
                    </p>
                </div>
                        
                <form action="showcart">
                    <input name="total" value="${requestScope.total}" type="hidden">
                    <select name="ship"
                            style="width: 25vw;font-size: 20px;padding: 5px 5px 5px 5px"
                            onchange="this.form.submit()">
                        <option hidden>Select:</option>
                        <option ${requestScope.ship=="urban"?"selected":""} value="urban">urban Hải Phòng(20.000VND)</option>
                        <option ${requestScope.ship=="suburban"?"selected":""} value="suburban">suburban Hải Phòng(30.000VND)</option>
                    </select>
                </form>
                    
                <p style="font-size: 20px; font-weight: bold">GIVE CODE</p>
                <c:if test="${requestScope.err!=null}">
                    <p style="font-size: 18px; font-weight: bold;color: red">${requestScope.err}</p>
                </c:if>
                <form action="showcart" method="post">
                    <input name="total" value="${requestScope.total}" type="hidden">
                    <input name="ship" value="${requestScope.ship}" type="hidden">
                    <input tyle="text" name="code" placeholder="Enter your code" style="width: 18.5vw ;font-size: 20px;padding: 5px 5px 5px 5px">
                    <input type="submit" value="Check" style="width:5vw;font-size: 20px;padding: 5px 5px 5px 5px">
                </form>
                <br/>
                <hr/>
                <c:if test="${requestScope.totalCode==null}">
                    <c:if test="${requestScope.ship!=null}">
                        <h2>Total money: &nbsp; <span style="color: red">${requestScope.totalShip}.000VND</span></h2>
                    </c:if>
                </c:if>
                <c:if test="${requestScope.totalCode!=null}">
                    <h2>Total money: &nbsp; <span style="color: red">${requestScope.totalCode}.000VND</span></h2>
                </c:if>
                        
                        
                <form action="checkout" method="post">
                    <input type="submit" value="Check out" style="width: 25vw;font-size: 20px;padding: 5px 5px 5px 5px"/>
                </form>
            </div>
        </div>  
        <br/>
        <hr/>
        <a href="shop"><span style="color: black;opacity: 0.6">&leftarrow;Back to shop</span></a>
        <br/><br/><br/>
    </body>
</html>