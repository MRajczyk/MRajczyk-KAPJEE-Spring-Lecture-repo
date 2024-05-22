<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
    <title>Hello world!</title>
</head>
<body>
<h3>Hello world!</h3>

<sec:authorize access="isAnonymous()">
    <a href="/login"><spring:message code="label.login"/></a> <br/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/appUsers"><spring:message code="label.addAppUser"/></a> <br/>
    <a href="/appUserRole"><spring:message code="label.role"/></a> <br/>
    <a href="addresses"><spring:message code="label.address"/></a> <br/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_USER')">
    <a href="/exampleOne"><spring:message code="label.example"/> 1</a> <br/>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')">
    <a href="/exampleTwo"><spring:message code="label.example"/> 2</a> <br/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_STUDENT')">
    <a href="/exampleThree"><spring:message code="label.example"/> 3</a> <br/>
</sec:authorize>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<!-- csrf for log out-->
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>
            <spring:message code="label.welcome"/> : ${pageContext.request.userPrincipal.name} |
            <a href="javascript:formSubmit()"> Logout</a>
        </p>
    </c:if>
</div>

<br />
${serverTime}
<br />
${message}
</body>
</html>

