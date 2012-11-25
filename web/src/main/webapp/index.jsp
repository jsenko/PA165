<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
    
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" var="actionBean"/>
    
        <h1>Matches</h1>

        <table>
            <tr>
                <td>Round</td>
                <td>Date</td>
                <td>Home Team</td>
                <td>Result</td>
                <td>Away Team</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${actionBean.matches}" var="matchDTO" varStatus="loop">
                <tr>
                    <!-- <td><c:out value="${loop.index + 1}"/>.</td>-->
                    <td><c:out value="${matchDTO.round}"/></td>
                    <td><c:out value="${matchDTO.date}"/></td>
                    <td><c:out value="${matchDTO.homeTeamName}"/></td>
                    <td><c:out value="${matchDTO.homeTeamGoals}"/> : <c:out value="${matchDTO.awayTeamGoals}"/></td>
                    <td><c:out value="${matchDTO.awayTeamName}"/></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" event="delete">
                    	<s:param name="matchDTO.id" value="${matchDTO.id}"/><i class="icon-remove"></i></s:link></td>
                    <td><s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" event="edit">
                    	<s:param name="matchDTO.id" value="${matchDTO.id}"/><i class="icon-pencil"></i></s:link> </td>
                </tr>
            </c:forEach>
        </table>
        
        <s:link beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean"
         event="create">novy zapas</s:link>

    </s:layout-component>
</s:layout-render>

