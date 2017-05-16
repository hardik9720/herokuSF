<%-- 
    Document   : index.jsp
    Created on : Aug 18, 2016, 11:09:13 AM
    Author     : Hardik Mehta
--%>

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CC-POC</title>  
        <style>
            html{
                background-color:#2F2F2F;
            }
            body {
                height: 100%;
                background-image: -webkit-gradient(
                    linear,
                    right bottom,
                    right top,
                    color-stop(0, #EDEDED),
                    color-stop(0.08, #EAEAEA),
                    color-stop(1, #2F2F2F),
                    color-stop(1, #AAAAAA)
                    );
                background-image: -o-linear-gradient(top, #EDEDED 0%, #EAEAEA 8%, #2F2F2F 100%, #AAAAAA 100%);
                background-image: -moz-linear-gradient(top, #EDEDED 0%, #EAEAEA 8%, #2F2F2F 100%, #AAAAAA 100%);
                background-image: -webkit-linear-gradient(top, #EDEDED 0%, #EAEAEA 8%, #2F2F2F 100%, #AAAAAA 100%);
                background-image: -ms-linear-gradient(top, #EDEDED 0%, #EAEAEA 8%, #2F2F2F 100%, #AAAAAA 100%);
                background-image: linear-gradient(to top, #EDEDED 0%, #EAEAEA 8%, #2F2F2F 100%, #AAAAAA 100%);
            }
            .username.ng-valid {
                background-color: lightgreen;
            }
            .username.ng-dirty.ng-invalid-required {
                background-color: red;
            }
            .username.ng-dirty.ng-invalid-minlength {
                background-color: yellow;
            }

            .email.ng-valid {
                background-color: lightgreen;
            }
            .email.ng-dirty.ng-invalid-required {
                background-color: red;
            }
            .email.ng-dirty.ng-invalid-email {
                background-color: yellow;
            }

        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
        <link rel="shortcut icon" href="<c:url value='/static/img/favicon.ico' />" />

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.8/angular-ui-router.min.js"></script>
        <script src="<c:url value='/static/js/app.js' />"></script>
        <script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
        <script src="<c:url value='/static/js/controller/address_controller.js' />"></script>
        <script src="<c:url value='/static/js/controller/phone_controller.js' />"></script>


    </head>
    <body ng-app="myApp">
    <c:if test="${pageContext.request.userPrincipal.name == null}">
            <h2>
                <c:redirect url="/login"/>
            </h2>
    </c:if> 

    <sec:authorize access="hasRole('ROLE_USER')">
        <!-- For login user -->
        <c:url value="/logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
           <h5 style="color: #ffffff">
                User : ${pageContext.request.userPrincipal.name} | <a
                    href="javascript:formSubmit()"> Logout</a>
            </h5>

            <ul class="nav nav-pills nav-justified">
                <li><a data-toggle="pill" ui-sref="person">Person</a></li>
                <li><a data-toggle="pill" ui-sref="address">Address</a></li>
                <li><a data-toggle="pill" ui-sref="phone">Phone</a></li>
            </ul> 
            <div ui-view>

            </div>
        </c:if>
    </sec:authorize>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
            $(document).ready(function () {
                $('ul.nav-pills li a').click(function (e) {
                    $('ul.nav-pills li.active').removeClass('active')
                    $(this).parent('li').addClass('active')
                })
            });
</script>    
</html>
