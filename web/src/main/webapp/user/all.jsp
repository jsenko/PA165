<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean" var="actionBean"/>
        <h1><s:label name="user.header"/></h1>

        <table class="match_table">
            <tr>
                <td><s:label name="global.no"/></td>
                <td class="name"><s:label name="user.name"/></td>
                <td><s:label name="user.hash"/></td>

            </tr>
            <c:forEach items="${actionBean.users}" var="user" varStatus="loop">

                <tr>
                    <td><c:out value="${loop.index + 1}"/>.</td>
                    <td class="name"><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <c:if test="${actionBean.canDelete}">
                    <td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean" onclick="return confirm('Delete ${user.login}?');" event="delete"><s:param name="user.id" value="${user.id}"/><i class="icon-remove"></i></s:link></td>
                    </tr>
                    </c:if>

            </c:forEach>
        </table> 
<c:if test="${actionBean.canCreate}">
        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean" event="create"><s:label class="btn btn-info" name="user.newUser"/></s:link>              
</c:if>
    </s:layout-component>
</s:layout-render>

