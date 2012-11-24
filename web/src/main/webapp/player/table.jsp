<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
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
            </tr>
            <%--<c:forEach items="">--%>
                <tr>
                    <td>1.</td>
                    <td class="name">Lorem</td>
                    <td>10</td>
                    <td>6</td>
                    <td>0</td>
                    <td>4</td>
                    <td>17:5</td>
                    <td>16</td>
                </tr>  
            <%--</c:forEach>--%>
        </table> 
        
    </s:layout-component>
</s:layout-render>
