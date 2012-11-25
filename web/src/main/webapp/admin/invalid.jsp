<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <form action="/pa165/admin/" method="get">
            <p><s:label name="admin.invalidCred" Invalid username / password</p>
            <input type="submit" class="btn btn-danger" value="Back"/>
        </form>
        
    </s:layout-component>
</s:layout-render>
