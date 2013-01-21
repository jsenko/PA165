<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">

        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" var="actionBean"/>

        <h1><s:label class="text-info header1" name="goal.goals"/></h1>

        <table id="goal_table">
            <tr>
                <td><s:label name="goal.homeTeam"/></td>
                <th><s:label name="goal.result"/></th>
                <td><s:label name="goal.awayTeam"/></td>
            </tr>
            <c:set var="home" value="0" scope="page" />
            <c:set var="away" value="0" scope="page" />

            <c:forEach items="${actionBean.goals}" var="goalDTO" varStatus="loop">
                <tr>
                    <c:if test="${goalDTO.isHomeTeam}">
                        <c:set var="home" value="${home + 1}" scope="page" />
                        <td><c:out value="${goalDTO.goalMinute}\'"/> <c:out value="${goalDTO.scoredPlayerName}"/> (<c:out value="${goalDTO.assistPlayerName}"/>)</td>
                        <th>${home} : ${away}</th>
                        <td></td>
                    </c:if>

                    <c:if test="${!goalDTO.isHomeTeam}">
                        <c:set var="away" value="${away + 1}" scope="page" />
                        <td></td>
                        <th>${home} : ${away}</th>
                        <td><c:out value="${goalDTO.scoredPlayerName}"/> (<c:out value="${goalDTO.assistPlayerName}"/>) <c:out value="${goalDTO.goalMinute}\'"/></td>
                    </c:if>
                    
                     <c:if test="${actionBean.canUpdate}">     
                    <td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" onclick="return confirm('Delete goal by ${goalDTO.scoredPlayerName} in ${goalDTO.goalMinute} minute?');" event="delete">
                            <s:param name="goalDTO.id" value="${goalDTO.id}"/>
                            <s:param name="goalDTO.matchId" value="${goalDTO.matchId}"/>
                            <i class="icon-remove"></i></s:link>
                    </td>
                    </c:if>
                    
                    <c:if test="${actionBean.canDelete}">  
                    <td class="admin_section"><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" event="edit">
                            <s:param name="goalDTO.id" value="${goalDTO.id}"/><i class="icon-pencil"></i></s:link> 
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${actionBean.canCreate}">    
            <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" event="create">
                <s:param name="goalDTO.matchId" value="${actionBean.goalDTO.matchId}"/>
                <s:label class="btn btn-info" name="goal.newGoal"/>
            </s:link>
        </c:if>

    </s:layout-component>
</s:layout-render>

