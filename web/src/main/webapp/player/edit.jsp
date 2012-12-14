<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/cover.jsp" nadpis="Edit team">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" var="playerActionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" class="form-horizontal">
            <s:hidden name="player.id"/>
            <fieldset><legend>Edit data</legend>
                <%@include file="/player/form.jsp"%>
                <div class="control-group">
                    <s:label class="control-label" name="Team" />
                    <div class="controls">
                        <s:select name="player.teamId" id="p6">
                            <c:forEach items="${playerActionBean.teams}" var="teams" varStatus="loop">
                                <s:option label="${teams.name}" value="${teams.id}"/>  
                            </c:forEach>
                        </s:select>
                    </div>
                </div>
                <s:submit class="btn btn-warning" name="save"/>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>