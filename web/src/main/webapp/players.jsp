<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <h1>Players</h1>

        <s:form action="team.jsp" method="post">

            <s:select name="team">
                <s:option></s:option> 
                <s:option>FC Team</s:option>
            </s:select>
            <input type="submit" name="submit" value="Show" title="show" class="btn btn-info team-show"/>
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
        <%--<c:forEach items="">--%>
        <table>
            <%--<c:forEach items="">--%>
            <tr>
                <td class="round">1</td>
                <td>18.11.2012 17:00</td>
                <td class="team">Lorim</td>
                <td class="team">Ipsum</td>
                <td>2:1 (1:1)</td>
            </tr>
            <%--</c:forEach>--%>
        </table> 
        <%--</c:forEach>--%>


    </s:layout-component>
</s:layout-render>

