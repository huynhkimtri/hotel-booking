<%-- 
    Document   : home
    Created on : Oct 4, 2020, 3:30:49 PM
    Author     : TriHuynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Hotel Booking</title>
        <%@include  file="component/headLink.jspf" %>
    </head>
    <c:set value="${sessionScope.USER}" var="user"/>
    <c:if test="${not empty user and user.roleId.id eq 1}">
        <c:redirect url="dashboard"/>
    </c:if>

    <fmt:setLocale value="vi_VN"/>

    <body class="bg-light">
        <%@include  file="component/navbar.jspf" %>
        <section class="jumbotron" style="padding: 2rem !important">
            <div class="container">
                <form action="search" method="get">
                    <div class="row mb-4">
                        <div class="col-md-3">
                            <label for="area">Where are you going?</label>
                            <c:set value="${requestScope.LIST_AREA}" var="areas"/>
                            <c:if test="${not empty areas}">
                                <select name="area" class="form-control" id="area" required="true"> 
                                    <c:forEach items="${areas}" var="a">
                                        <option value="${a.id}" 
                                                <c:if test="${requestScope.AREA == a.id}"> selected="true"</c:if>>
                                            ${a.name}
                                        </option>F
                                    </c:forEach>
                                </select>
                            </c:if>
                        </div>
                        <div class="col-md-3">
                            <label for="checkin">Check-in Date:</label>
                            <input class="form-control" type="date" id="checkin" required="true"  
                                   name="checkin"
                                   value="${requestScope.CK_IN_DATE}" />
                        </div>
                        <div class="col-md-3">
                            <label for="checkout">Check-out Date:</label>
                            <input class="form-control" type="date" id="checkout" required="true" 
                                   name="checkout"
                                   value="${requestScope.CK_OUT_DATE}" />
                        </div>
                        <div class="col-md-3">
                            <label for="type">Room type:</label>
                            <c:set value="${requestScope.LIST_ROOM_TYPE}" var="types"/>
                            <c:if test="${not empty types}">
                                <select name="type" class="form-control" id="type" required="true">
                                    <c:forEach items="${types}" var="type">
                                        <option value="${type.id}" 
                                                <c:if test="${requestScope.ROOM_TYPE == type.id}"> selected="true"</c:if>>
                                            ${fn:toUpperCase(type.description)}
                                        </option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit">Search</button>
                    <button class="btn btn-secondary" type="reset">Reset</button>
                </form>
            </div>
        </section>
        <div class="container">
            <div class="album py-3 bg-light">
                <div class="container">
                    <c:set value="${requestScope.LIST_CAKES}" var="cakes"/>
                    <c:if test="${not empty cakes}">
                        <div class="row">
                            <c:forEach items="${cakes}" var="cake">
                                <div class="col-md-4">
                                    <div class="card mb-4 shadow-sm">
                                        <img class="bd-placeholder-img card-img-top" height="256" src="${cake.imageUrl}"/>
                                        <div class="card-body">
                                            <span class="badge badge-warning mb-2">#${cake.categoryId.name}</span>
                                            <h5 class="card-title mb-1">${cake.name}</h5>
                                            <h5 class="card-title"><b style="color: var(--danger)"><fmt:formatNumber value="${cake.price}" type="currency"/></b></h5>
                                            <div class="text-muted mb-4">
                                                ${cake.description}<br>
                                                Cre: <fmt:formatDate pattern="dd/MM/yyyy" value="${cake.createDate}" /> - Exp: <fmt:formatDate pattern="dd/MM/yyyy" value="${cake.expirationDate}" />
                                            </div>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="btn-group">
                                                    <form action="addToCart" method="POST">
                                                        <c:set var="urlParams" value="${requestScope['javax.servlet.forward.query_string']}"/>
                                                        <input type="hidden" name="cakeId" value="${cake.id}"/>
                                                        <input type="hidden" name="cakeName" value="${cake.name}"/>
                                                        <input type="hidden" name="urlParams" value="${urlParams}"/>
                                                        <c:if test="${cake.quantity > 0}">
                                                            <input type="hidden" name="queryString" value="${requestScope['javax.servlet.forward.query_string']}"/>
                                                            <button type="submit" class="btn btn-success" style="font-weight: bold">ADD TO CART</button>
                                                        </c:if>
                                                        <c:if test="${cake.quantity <= 0}">
                                                            <button type="button" class="btn btn-secondary">CONTACT LATER</button>
                                                        </c:if>
                                                    </form>
                                                </div>
                                                <c:if test="${cake.quantity > 0}">
                                                    <span class="badge badge-primary">Stock: ${cake.quantity}</span>
                                                </c:if>
                                                <c:if test="${cake.quantity <= 0}">
                                                    <span class="badge badge-dark">Out of Stock</span>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination pagination-template d-flex justify-content-center">
                                <c:set value="${requestScope.PAGE_NUMBER}" var="pageNummber" />
                                <c:set value="${requestScope.NUMBER_OF_PAGES}" var="numOfPages" />
                                <c:set value="${requestScope.CURRENT_PAGE}" var="currentPage" />
                                <c:forEach begin="1" end="${numOfPages}" step="1" varStatus="theCount">
                                    <li class="page-item <c:if test="${currentPage eq theCount.count}">active</c:if>">
                                            <a class="page-link" 
                                               href="search?keyword=${requestScope.KEYWORD}&min=${requestScope.MIN}&max=${requestScope.MAX}&category=${requestScope.CATEGORY}&page=${theCount.count}">
                                            ${theCount.count}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${empty cakes}">
                        <p>No cakes was found!</p>
                    </c:if>
                </div>
            </div>
            <footer class="text-muted mb-5 text-center text-small">
                <p class="mb-1">Yellow Moon Shop - ©2020 - Power by TriHK</p>
            </footer>
        </div>
    </body>
</html>
