<%-- 
    Document   : checkout
    Created on : Oct 13, 2020, 11:02:00 PM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout - YMS</title>
        <link href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
              crossorigin="anonymous">
    </head>
    <body>

        <fmt:setLocale value="vi_VN"/>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark flex-md-nowrap shadow">
            <div class="container-xl">
                <a class="navbar-brand" style="color: var(--warning)" href="./"><b>Moon Shop</b></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07XL" aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample07XL">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="./">Home </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="myCart.jsp">My Cart <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="tracking.jsp" >Tracking Order</a>
                        </li>

                        <c:set value="${sessionScope.USER}" var="user"/>
                        <c:if test="${not empty user}">
                            <li class="nav-item">
                                <a class="nav-link" href="#">Hello, ${user.fullName}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="signOut">Sign out</a>
                            </li>
                        </c:if>
                        <c:if test="${empty user}">
                            <li class="nav-item">
                                <a class="btn btn-primary" href="signup.jsp">Register</a>
                            </li>
                            <li class="nav-item">
                                <a class="btn btn-outline-primary" href="signin.jsp">Sign in</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <section class="mt-5 pb-5">
            <div class="container">

                <c:set value="${sessionScope.USER}" var="user"/>
                <c:if test="${not empty user and user.roleId.id == 1}">
                    <c:redirect url="./"/>
                </c:if>
                <h1 class="mb-4">Checkout</h1>
                <c:set value="${sessionScope.MY_CART}" var="myCart"/>
                <c:if test="${not empty myCart.items.entrySet()}">
                    <table class="col-md-8 table table-bordered" border="1">
                        <thead class="thead-light">
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th style="width:10%">Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${myCart.getItems().entrySet()}" var="cart" varStatus="counter">
                                <c:set var="item" value="${cart.getKey()}" />
                            <form action="updateItem" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.name}</td>
                                    <td><fmt:formatNumber value="${item.price}" type="currency"/></td>
                                    <td>${cart.getValue()}</td>
                                    <td><fmt:formatNumber value="${item.price * cart.getValue()}" type="currency"/></td>
                                </tr>
                            </form>
                        </c:forEach>
                        <tr>
                            <td colspan="3"></td>
                            <td colspan="2"><b style="color: var(--danger)">Total Price: <fmt:formatNumber value="${myCart.totalAmount}" type="currency"/></b></td>
                        </tr>
                        </tbody>
                    </table>
                </c:if>

                <form class="mb-4" action="placeOrder" method="POST">
                    <h3 class="mb-3">Your information details:</h3>
                    <table>
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="name" value="${user.fullName}" required="true"/></td>
                        </tr>
                        <tr>
                            <td>Phone:</td>
                            <td><input type="tel" name="phone" value="${user.phone}" required="true"/></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input type="email" name="email" value="${user.email}" required="true"/></td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td><input type="text" name="address" value="${user.address}" required="true"/></td>
                        </tr>
                    </table>
                    <h3 class="mb-3 mt-3">Payment method:</h3> 
                    <input type="radio" id="cod" checked="true" name="payment" value="2">
                    <label for="cod">Cash on Delivery</label><br>
                    <input type="radio" id="paypal" name="payment" value="3">
                    <label for="paypal">Paypal</label><br>
                    <input type="radio" id="bank" name="payment" value="4">
                    <label for="bank">Internet Banking</label>
                    <br>
                    <button class="btn btn-success" type="submit">Place an Order</button>
                </form>
            </div>
        </section>
    </body>
</html>
