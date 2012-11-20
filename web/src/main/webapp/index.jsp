<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <h1>League</h1>
        
        
        <%--<c:forEach items="">--%>
            <table>
                <%--<c:forEach items="">--%>
                    <tr>
                        <td class="round">1</td>
			<td>18.11.2012 17:00</td>
			<td class="team">Lorem</td>
			<td class="team">Ipsum</td>
			<td>2:1 (1:1)</td>
                    </tr>
                    <%--</c:forEach>--%>
            </table> 
        <%--</c:forEach>--%>
        
    </s:layout-component>
</s:layout-render>
