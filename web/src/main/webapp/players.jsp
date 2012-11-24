<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" var="actionBean"/>
        <h1>Players</h1>

        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean">
            <s:select name="${team.id}">
                <s:options-collection collection="${actionBean.teams}" label="name" value="id"/>             
            </s:select>
            <s:submit class="btn btn-info team-show" name="selectTeam">Show</s:submit>
        </s:form>

        <div class="btn-group">
            <button class="btn btn-primary">Sort by</button>
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><s:link href="#">Name</s:link></li>
                <li><a href="#">Goals</a></li>
                <li><a href="#">Age</a></li>
                <li><a href="#">Weight</a></li>
                <li><a href="#">Height</a></li>
            </ul>
        </div>
        <table>
            <tr>
                <td>No.</td>
                <td>Name</td>
                <td>Goals</td>
                <td>Assistances</td>
                <td>Age</td>
                <td>Weight</td>
                <td>Height</td>
            </tr>
            <c:forEach items="${actionBean.players}" var="player" varStatus="loop">
                <tr>
                    <td><c:out value="${loop.index + 1}"/>.</td>
                    <td><c:out value="${player.name}"/> <c:out value="${player.surname}"/></td>
                    <td><c:out value="${player.goals}"/></td>
                    <td><c:out value="${player.assists}"/></td>
                    <td><c:out value="${player.age}"/></td>
                    <td><c:out value="${player.weight}"/></td>
                    <td><c:out value="${player.height}"/></td>
                </tr>
            </c:forEach>
        </table>
                
        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean">
            <fieldset><legend>New player</legend>
                <%@include file="forms/playerForm.jsp"%>
                <s:submit name="add">Create new player</s:submit>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>

