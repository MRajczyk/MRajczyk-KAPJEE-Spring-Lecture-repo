<%--
  Created by IntelliJ IDEA.
  User: rajsi
  Date: 09.04.2024
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title><spring:message code="label.addAppUser"/>App User</title>
</head>
<body>
    <div class="header">
        <span>
            <a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
        </span>
    </div>
    <h1>App user info</h1>
    <form:form method="post" action="addAppUser" modelAttribute="appUser">
        <table>
            <tr>
                <td><form:hidden path="id"/>
            </tr>
            <tr>
                <td><form:label path="firstName"><spring:message code="label.firstname"/></form:label></td>
                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td><form:label path="lastName"><spring:message code="label.lastname"/></form:label></td>
                <td><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
                <td><form:input path="telephone"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${appUser.id == 0}">
                        <input type="submit" value="<spring:message code="label.addAppUser"/>"/>
                    </c:if>
                    <c:if test="${appUser.id != 0}">
                        <input type="submit" value="<spring:message code="label.editAppUser"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
    <h3><spring:message code="label.userList"/></h3>
    <c:if test="${!empty appUserList}">
        <table class="data">
            <tr>
                <th><spring:message code="label.firstname"/></th>
                <th><spring:message code="label.lastname"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${appUserList}" var="appUser">
                <tr>
                    <td>${appUser.firstName} </td>
                    <td>${appUser.lastName} </td>
                    <td>${appUser.email}</td>
                    <td>${appUser.telephone}</td>
                    <td><a href="delete/${appUser.id}">delete</a></td>
                    <td><a href="appUsers?appUserId=${appUser.id}">edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
