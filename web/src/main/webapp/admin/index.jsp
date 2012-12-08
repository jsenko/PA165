<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.AdminActionBean" var="actionBean"/>
        <h1><s:label name="admin.signIn"/></h1>

        <s:errors/>
        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.AdminActionBean" class="form-horizontal" action="admin">
            <p>
                <s:text id="p1" name="admin.name"/>
            </p>
            <p>
                <s:password id="p2" name="admin.password"/>
            </p>
            <p>
                <s:submit class="btn" name="login" title="Sign in">Sign in</s:submit>
            </p>
        </s:form>

    </s:layout-component>
</s:layout-render>
