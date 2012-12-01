<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" var="actionBean"/>
        
        
        <h1>Create new player</h1>

        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean">
            <fieldset><legend><s:label name="player.newPlayer"/></legend>
                <%@include file="/player/form.jsp"%>
                <%--<s:submit name="add"><s:label name="player.createNewPlayer"/></s:submit>--%>
                <s:submit name="add" class="btn btn-info"/>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>
