<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean" var="actionBean"/>
        <c:if test="${actionBean.invalidLogin}">
            <s:label class="text-error" name="user.sameLogin" />
        </c:if>
        <s:label class="header1 text-info" name="user.create" />
        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean">
            <%@include file="/user/form.jsp"%>
            <s:submit name="add" class="btn btn-info"/>
        </s:form>

    </s:layout-component>
</s:layout-render>

