<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hne Coffee</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <div class="header" style="background: #b22830; 
             padding: 8px 8px 0px 8px; 
             border-top: 10px solid #53382c;">

            <div style="display: flex; justify-content: flex-start">
                <div>
                    <a href="home" style="margin-left: 40%">
                        <img class="header-one img-fluid" src="Image/logo.png" style=" width: 30%; ">
                    </a>
                </div>

                <div class="topnav" id="myTopnav" style="margin-top: 6%;">
                    <div style="display: flex; justify-content: center; font-weight: bold">
                        <a href="shop">Tất cả sản phẩm</a>
                        <a href="product?cid=1">Cà phê</a>
                        <a href="product?cid=2">Freeze</a>
                        <a href="product?cid=3">Trà sữa</a>
                        <a href="https://www.facebook.com/pnt.55.01.NQ">Liên hệ</a>
                        <a href="javascript:void(0);" class="icon" onclick="myFunction()">
                            <i class="fa fa-bars"></i>
                        </a>
                    </div>
                </div>                

                <form action="search">
                    <div style="margin-top: 15%;">
                        <input class="text-search" type="text" placeholder="Fin,Freeze,35,50,..." name="name">
                        <button style="height: 29px; width: 30px; background: white"><img src="Image/search.png" style="height: 20px; width: 20px"></button>
                    </div>
                </form> 
            </div>

        </div>
        <!--Navbar-->       

    </body>
</html>
