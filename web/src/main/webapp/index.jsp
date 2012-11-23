<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <s:useActionBean beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" var="actionBean"/>
        <h1>League</h1>

        <table>
            <c:forEach items="${actionBean.matches}" var="match">

                <tr>
                    <td class="round"><c:out value="${match.round}"/></td>
                    <td><c:out value="${match.date}"/></td>
                    <td class="team"><c:out value="${match.homeTeamName}"/></td>
                    <td class="team"><c:out value="${match.awayTeamName}"/></td>
                    <td><c:out value="${match.homeTeamGoals}"/>:<c:out value="${match.awayTeamGoals}"/></td>
                </tr>

            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>