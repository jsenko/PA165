<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/cover.jsp" nadpis="Edit team">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean">
            <s:hidden name="team.id"/>
            <fieldset><legend>Edit data</legend>
                <%@include file="/team/form.jsp"%>
                <s:submit name="save"/>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>