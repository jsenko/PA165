<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.TeamActionBean" var="actionBean"/>
        <h1>Table</h1>
       
        <table id="league_table">
            <tr>
                <td>No.</td>
                <td class="name">Name</td>
                <td>GP</td>
		<td>W</td>
		<td>L</td>
		<td>T</td>
		<td>SC</td>
		<td>PTS</td>
                <td>Trend</td>
            </tr>
            <c:forEach items="${actionBean.teams}" var="team">

                <tr>
                    <td></td>
                    <td class="name"><c:out value="${team.name}"/></td>
                    <td><c:out value="${team.won + team.lost + team.draw}"/></td>
                    <td><c:out value="${team.won}"/></td>
                    <td><c:out value="${team.lost}"/></td>
                    <td><c:out value="${team.draw}"/></td>
                    <td><c:out value="${team.goalsFor}"/>:<c:out value="${team.goalsAgainst}"/></td>
                    <td><c:out value="${team.points}"/></td>
                    <td><c:out value="${team.trend}"/></td>
                </tr>

            </c:forEach>
        </table> 
        
    </s:layout-component>
</s:layout-render>
