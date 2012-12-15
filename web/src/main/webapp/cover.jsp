<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-definition>
    <html>
        <head>
            <title>FAST - Football analytical and statistical tool</title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
            <!--these scripts must be loaded here because of calendar-->
            <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
            <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>


            <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/png">    

        </head>
        <body>
            <div id="header">
                <img src="${pageContext.request.contextPath}/img/logo.jpg" alt="FAST - Fotball analytical and statistical tool" id="logo" />
            </div>
            <div class="container">

                <div id="menu">
                    <ul>
                        <li><s:link href="${pageContext.request.contextPath}/index.jsp"><s:label name="home"/></s:link></li>
                        <li><s:link href="${pageContext.request.contextPath}/team/all.jsp"><s:label name="teams"/></s:link></li>
                        </ul>
                    </div>

                    <div id="content">
                    <s:layout-component name="content"/>
                </div>


            </div>

            <div id="footer">
                <p>Project of course PA165, Faculty of Informatics, Masaryk University. Copyright © 2012</p>
            </div>

            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>    
        </body>

    </html>
</s:layout-definition>