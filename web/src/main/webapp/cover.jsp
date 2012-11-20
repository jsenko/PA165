<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-definition>
<html>
<head>
    <title>FAST - Football analytical and statistical tool</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    
</head>
<body>
    <div class="header">
	<img src="${pageContext.request.contextPath}/img/sk.png" alt="Slovenčina" class="language"/>
        <img src="${pageContext.request.contextPath}/img/us.png" alt="English" class="language"/>
        <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="FAST - Fotball analytical and statistical tool" id="logo" />
    </div>
    <div class="container">
        
        <div id="menu">
            <ul>
                <li><s:link href="${pageContext.request.contextPath}/index.jsp">HOME</s:link></li>
                <li><s:link href="${pageContext.request.contextPath}/table.jsp">TABLE</s:link></li>
                <li><s:link href="${pageContext.request.contextPath}/team.jsp">TEAM</s:link></li>	
            </ul>
        </div>
    
        <div id="content">
            <s:layout-component name="content"/>
        </div>
        
        
    </div>
    
    <div id="footer">
        <p>Project of course PA165, Faculty of Informatics, Masaryk University. Copyright © 2012</p>
    </div>

    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
</s:layout-definition>