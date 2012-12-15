<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" var="actionBean"/>
        <h1><s:label name="table.header"/></h1>

        <table id="team_table">
            <tr>
                <td><s:label name="global.no"/></td>
                <td class="name"><s:label name="team.name"/></td>
                <td><s:label name="table.gamesPlayed"/></td>
                <td><s:label name="table.wins"/></td>
                <td><s:label name="table.losses"/></td>
                <td><s:label name="table.tosses"/></td>
                <td><s:label name="table.score"/></td>
                <td><s:label name="table.points"/></td>

            </tr>
            <c:forEach items="${actionBean.teams}" var="team" varStatus="loop">

                <tr>
                    <td><c:out value="${loop.index + 1}"/>.</td>
                    <td class="name"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="selectTeam"><s:param name="team.id" value="${team.id}"/><c:out value="${team.name}"/></s:link></td>
                    <td><c:out value="${team.won + team.lost + team.draw}"/></td>
                    <td><c:out value="${team.won}"/></td>
                    <td><c:out value="${team.lost}"/></td>
                    <td><c:out value="${team.draw}"/></td>
                    <td><c:out value="${team.goalsFor}"/>:<c:out value="${team.goalsAgainst}"/></td>
                    <th><c:out value="${team.points}"/></th>
                    <td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" onclick="return confirm('Delete ${team.name}?');" event="delete"><s:param name="team.id" value="${team.id}"/><i class="icon-remove"></i></s:link></td>
                    <td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" event="edit"><s:param name="team.id" value="${team.id}"/><i class="icon-pencil"></i></s:link> </td>
                    </tr>

            </c:forEach>
        </table> 

        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" event="create"><s:label class="btn btn-info" name="team.newTeam"/></s:link>              

        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" event="generate"><s:label class="btn btn-info" name="team.generateTeams" /></s:link>

    </s:layout-component>
</s:layout-render>

