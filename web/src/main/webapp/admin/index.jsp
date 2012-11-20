<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
        <h1>Sign in</h1>
        
        
        <s:form action="${pageContext.request.contextPath}/index.jsp" method="post" class="form-horizontal">
            <p>
                <input type="text" placeholder="Username"/>
            </p>
            <p>
                <input type="password" placeholder="password"/>
            </p>
            <p>
                <input type="submit" class="btn" value="Sign in" name="sent" title="Sign in"/>
            </p>
        </s:form>
        
    </s:layout-component>
</s:layout-render>
