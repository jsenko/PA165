<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/cover.jsp" nadpis="Edit team">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" var="actionBean"/>
        <s:label class="header1 text-info" name="team.edit" />
        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean">
            <s:hidden name="team.id"/>
            <%@include file="/team/form.jsp"%>
            <s:submit class="btn btn-warning" name="save"/>
        </s:form>

    </s:layout-component>
</s:layout-render>