<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:layout-definition>
	<html>
<head>
<title>FAST - Football analytical and statistical tool</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<!--these scripts must be loaded here because of calendar-->
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>


<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.png"
	type="image/png">

</head>
<body>
	<div id="header">
		<img src="${pageContext.request.contextPath}/img/logo.jpg"
			alt="FAST - Fotball analytical and statistical tool" id="logo" />
	</div>
	<div class="container">
		<s:useActionBean
			beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean"
			var="userBean" />
		<div id="menu">
			<ul>
				<li><s:link href="${pageContext.request.contextPath}/">
						<s:label name="home" />
					</s:link></li>
				<li><s:link href="${pageContext.request.contextPath}/teams/all">
						<s:label name="teams" />
					</s:link></li>
				<li>
					<c:choose>
						<c:when test="${userBean.loggedIn}">
							<s:link href="${pageContext.request.contextPath}/users/logout">
								<s:label name="logout" />
							</s:link>
						</c:when>
						<c:otherwise>
							<s:link href="${pageContext.request.contextPath}/users/login">
								<s:label name="login" />
							</s:link>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>

		<div id="content">
			<s:layout-component name="content" />
		</div>


	</div>

	<div id="footer">
		<p>Project of course PA165, Faculty of Informatics, Masaryk
			University. Copyright © 2012</p>
	</div>

	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>

	</html>
</s:layout-definition>