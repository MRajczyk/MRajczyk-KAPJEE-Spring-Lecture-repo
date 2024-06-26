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
    <script src="https://www.google.com/recaptcha/api.js"></script>
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
                <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
                <td><form:input path="login"/></td>
                <td><form:errors path="login"/></td>
            </tr>
            <tr>
                <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
                <td><form:input type="password" path="password" /></td>
                <td><form:errors path="password"/></td>
            </tr>
            <tr>
                <td><form:label path="enabled"><spring:message code="label.enabled"/></form:label></td>
                <td><form:checkbox path="enabled" /></td>
                <td><form:errors path="enabled"/></td>
            </tr>
            <tr>
                <td><form:label path="firstName"><spring:message code="label.firstName"/></form:label></td>
                <td><form:input path="firstName"/></td>
                <td><form:errors path="firstName"/></td>
            </tr>
            <tr>
                <td><form:label path="lastName"><spring:message code="label.lastName"/></form:label></td>
                <td><form:input path="lastName"/></td>
                <td><form:errors path="lastName"/></td>
            </tr>
            <tr>
                <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email"/></td>
            </tr>
            <tr>
                <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
                <td><form:input path="telephone"/></td>
                <td><form:errors path="telephone"/></td>
            </tr>
            <tr>
                <td><form:label path="pesel.PESEL"><spring:message code="label.pesel"/></form:label></td>
                <td><form:input path="pesel.PESEL" /></td>
                <td><form:errors path="pesel"/></td>
            </tr>
            <tr>
                <td><form:label path="address"><spring:message code="label.address"/></form:label></td>
                <td><form:select path="address">
                    <c:forEach items="${addressesList}" var="address">
                        <option value="${address.id}" ${address.id == selectedAddress ? 'selected="selected"' : ''}>${address.street}</option>
                    </c:forEach>
                </form:select></td>
                <td><form:errors path="address"/></td>
            </tr>
            <tr>
                <td><form:label path="appUserRole"><spring:message code="label.role"/></form:label></td>
                <td><form:select path="appUserRole" multiple="true">
                    <form:options items="${appUserRoleList}" itemValue="id" itemLabel="role"/>
                </form:select></td>
                <td><form:errors path="appUserRole"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <div class="g-recaptcha" data-sitekey=${captchaSiteKey}></div>
                </td>
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
                <th><spring:message code="label.firstName"/></th>
                <th><spring:message code="label.lastName"/></th>
                <th><spring:message code="label.email"/></th>
                <th><spring:message code="label.telephone"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th> </th>
            </tr>
            <c:forEach items="${appUserList}" var="appUser">
                <tr>
                    <td>${appUser.firstName} </td>
                    <td>${appUser.lastName} </td>
                    <td>${appUser.email}</td>
                    <td>${appUser.telephone}</td>
                    <td><a href="delete/${appUser.id}">delete</a></td>
                    <td><a href="appUsers?appUserId=${appUser.id}">edit</a></td>
                    <td><a href="generatePdf-${appUser.id}">pdf</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>
