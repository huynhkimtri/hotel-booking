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
                                            ${a.location}
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
                            <label for="amount">Amount</label>
                            <input class="form-control" type="number" id="amount" min="0" required="true"  
                                   name="amount"
                                   value="${requestScope.AMOUNT}" />
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
                    <c:set value="${requestScope.LIST_HOTEL}" var="hotels"/>
                    <c:if test="${not empty hotels}">
                        <div class="row">
                            <c:forEach items="${hotels}" var="hotel">
                                <div class="col-md-4">
                                    <div class="card mb-4 shadow-sm">
                                        <img class="bd-placeholder-img card-img-top" height="256" src="${hotel.imageUrl}"/>
                                        <div class="card-body">
                                            <h5 class="card-title mb-1">${hotel.name}</h5>
                                            <h5 class="card-title"><b style="color: var(--danger)"><fmt:formatNumber value="${cake.price}" type="currency"/></b></h5>
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
                    <c:if test="${empty hotel}">
                        <p>No hotel was found!</p>
                    </c:if>
                </div>
            </div>
            <footer class="text-muted mb-5 text-center text-small">
                <p class="mb-1">Hotel Booking - ©2020 - Power by TriHK</p>
            </footer>
        </div>
    </body>
</html>
