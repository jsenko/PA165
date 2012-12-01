<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">

        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" var="actionBean"/>

        <h1><s:label name="index.matches"/></h1>
        
        <c:forEach var="round" begin="1" end="${actionBean.rounds}" step="1" varStatus="status" >
            <table>
                <h1><s:label name="match.round"/> <c:out value="${round}" /></h1>
                <c:forEach items="${actionBean.matches}" var="matchDTO" varStatus="loop">
                    <c:if test="${matchDTO.round eq round}">
                        <tr>
                            <!-- <td><c:out value="${loop.index + 1}"/>.</td>-->
                            
                            <td>
                                <c:out value="${matchDTO.round}"/>
                            </td>
                            
                            <td>
                                <f:formatDate type="date" dateStyle="short" value="${matchDTO.date}" />
                            </td>

                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="selectTeam"><s:param name="team.id" value="${matchDTO.homeTeamId}"/><c:out value="${matchDTO.homeTeamName}"/></s:link>
                            </td>
                            
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.GoalActionBean" event="all">
                                    <s:param name="goalDTO.matchId" value="${matchDTO.id}"/>
                                    <c:out value="${matchDTO.homeTeamGoals}"/> : <c:out value="${matchDTO.awayTeamGoals}"/>
                                </s:link>
                            </td>
                            
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.PlayerActionBean" event="selectTeam"><s:param name="team.id" value="${matchDTO.awayTeamId}"/><c:out value="${matchDTO.awayTeamName}"/></s:link>
                            </td>
                            
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" onclick="return confirm('Delete ${matchDTO.homeTeamName} vs. ${matchDTO.awayTeamName}?');" event="delete">
                                    <s:param name="matchDTO.id" value="${matchDTO.id}"/><i class="icon-remove"></i>
                                </s:link>
                            </td>
                            
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" event="edit">
                                    <s:param name="matchDTO.id" value="${matchDTO.id}"/><i class="icon-pencil"></i>
                                </s:link>
                            </td>

                        </tr>
                    </c:if>
                </c:forEach>

            </table>
        </c:forEach>
        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean"
                event="create"><s:label name="match.newMatch"/></s:link>
        
        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean"
                event="generate">Generuj Zapas</s:link>

    </s:layout-component>
</s:layout-render>

