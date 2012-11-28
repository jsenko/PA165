<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">

        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" var="actionBean"/>

        <h1>Goals</h1>

        <table>
            <tr>
                <td>Home Team</td>
                <td>Result</td>
                <td>Away Team</td>
                <td></td>
                <td></td>
            </tr>
            <c:set var="home" value="0" scope="page" />
            <c:set var="away" value="0" scope="page" />

            <c:forEach items="${actionBean.goals}" var="goalDTO" varStatus="loop">
                <tr>
                    <!-- <td><c:out value="${loop.index + 1}"/>.</td>-->
                    <c:if test="${goalDTO.isHomeTeam}">
                        <c:set var="home" value="${home + 1}" scope="page" />
                        <td><c:out value="${goalDTO.scoredPlayerName}"/> (<c:out value="${goalDTO.assistPlayerName}"/>)</td>
                        <td>${home} : ${away}</td>
                    </c:if>

                    <c:if test="${!goalDTO.isHomeTeam}">
                        <c:set var="away" value="${away + 1}" scope="page" />
                        <td>${home} : ${away}</td>
                        <td><c:out value="${goalDTO.scoredPlayerName}"/> (<c:out value="${goalDTO.assistPlayerName}"/>)</td>
                    </c:if>

                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" event="delete">
                            <s:param name="goalDTO.id" value="${goalDTO.id}"/><i class="icon-remove"></i></s:link></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" event="edit">
                            <s:param name="goalDTO.id" value="${goalDTO.id}"/><i class="icon-pencil"></i></s:link> </td>
                    </tr>
            </c:forEach>
        </table>

        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean"
                event="create">novy gol</s:link>

    </s:layout-component>
</s:layout-render>

