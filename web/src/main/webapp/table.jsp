<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" var="actionBean"/>
        <h1><s:label name="table.header"/></h1>

        <table id="league_table">
            <tr>
                <td>No.</td>
                <td class="name">Name</td>
                <td><s:label name="table.gamesPlayed"/></td>
                <td><s:label name="table.wins"/></td>
                <td><s:label name="table.losses"/></td>
                <td><s:label name="table.tosses"/></td>
                <td><s:label name="table.score"/></td>
                <td><s:label name="table.points"/></td>
                <!--<td>Trend</td>-->
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${actionBean.teams}" var="team" varStatus="loop">

                <tr>
                    <td><c:out value="${loop.index + 1}"/>.</td>
                    <td class="name"><c:out value="${team.name}"/></td>
                    <td><c:out value="${team.won + team.lost + team.draw}"/></td>
                    <td><c:out value="${team.won}"/></td>
                    <td><c:out value="${team.lost}"/></td>
                    <td><c:out value="${team.draw}"/></td>
                    <td><c:out value="${team.goalsFor}"/>:<c:out value="${team.goalsAgainst}"/></td>
                    <td><c:out value="${team.points}"/></td>
                    <!--<td></td>-->
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" event="delete"><s:param name="team.id" value="${team.id}"/><i class="icon-remove"></i></s:link></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" event="edit"><s:param name="team.id" value="${team.id}"/><i class="icon-pencil"></i></s:link> </td>
                </tr>

            </c:forEach>
        </table> 

        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean">
            <fieldset><legend><s:label name="table.newTeam"/></legend>
                <%@include file="forms/teamForm.jsp"%>
                <s:submit value="table.createNewTeam" name="add"/>
                </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>

