<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" var="actionBean"/>
        <h1><s:label name="player.header"/></h1>
<%-- 
        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean">
            <s:select name="team.id">
                <s:options-collection collection="${actionBean.teams}" label="name" value="id"/>             
            </s:select>
            <s:submit class="btn btn-info team-show" name="selectTeam"/>
        </s:form>
--%>
        <div class="btn-group">
            <button class="btn btn-primary"><s:label name="player.sortBy"/></button>
            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="order"><s:param name="order" value="0"/><s:label name="player.name"/></s:link></li>
                <li><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="order"><s:param name="order" value="1"/><s:label name="player.goals"/></s:link></li>
                <li><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="order"><s:param name="order" value="2"/><s:label name="player.age"/></s:link></li>
                <li><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="order"><s:param name="order" value="3"/><s:label name="player.weight"/></s:link></li>
                <li><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="order"><s:param name="order" value="4"/><s:label name="player.height"/></s:link></li>
            </ul>
        </div>
            
        <table id="players_table">
            <tr>
                <td><s:label name="global.no"/></td>
                <td><s:label name="player.name"/></td>
                <td><s:label name="player.goals"/></td>
                <td><s:label name="player.assistances"/></td>
                <td><s:label name="player.age"/></td>
                <td><s:label name="player.weight"/></td>
                <td><s:label name="player.height"/></td>
                <td></td>
                <td></td>
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
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" onclick="return confirm('Delete ${player.name}?');" event="delete"><s:param name="player.id" value="${player.id}"/><i class="icon-remove"></i></s:link></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="edit"><s:param name="player.id" value="${player.id}"/><i class="icon-pencil"></i></s:link> </td>
                </tr>
            </c:forEach>
        </table>

 		<s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean"
                event="create">New player</s:link> 
 
 
    </s:layout-component>
</s:layout-render>

