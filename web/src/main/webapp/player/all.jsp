<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" var="actionBean"/>
        <h1><s:label class="header1 text-info" name="player.header"/></h1>

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

            <table id="player_table">
                <tr>
                    <td><s:label name="global.no"/></td>
                <td><s:label name="player.name"/></td>
                <td><s:label name="player.goals"/></td>
                <td><s:label name="player.assistances"/></td>
                <td><s:label name="player.age"/></td>
                <td><s:label name="player.weight"/></td>
                <td><s:label name="player.height"/></td>
            </tr>
            <c:forEach items="${actionBean.players}" var="player" varStatus="loop">
                <tr>
                    <td><c:out value="${loop.index + 1}"/>.</td>
                    <td><c:out value="${player.name}"/> <c:out value="${player.surname}"/></td>
                    <th><c:out value="${player.goals}"/></th>
                    <th><c:out value="${player.assists}"/></th>
                    <td><c:out value="${player.age}"/></td>
                    <td><c:out value="${player.weight}"/></td>
                    <td><c:out value="${player.height}"/></td>
                    <c:if test="${actionBean.canUpdate}"><td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" onclick="return confirm('Delete ${player.name}?');" event="delete"><s:param name="player.id" value="${player.id}"/><i class="icon-remove"></i></s:link></td></c:if>
                    <c:if test="${actionBean.canDelete}"><td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="edit"><s:param name="player.id" value="${player.id}"/><i class="icon-pencil"></i></s:link> </td></c:if>
                    </tr>
            </c:forEach>
        </table>
        <c:if test="${actionBean.canCreate}">
            <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="create"><s:label class="btn btn-info" name="player.newPlayer" /></s:link> 
        </c:if>
    </s:layout-component>
</s:layout-render>

