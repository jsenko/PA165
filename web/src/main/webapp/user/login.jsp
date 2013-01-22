<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/cover.jsp">
	<s:layout-component name="content">
		<s:useActionBean
			beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean"
			var="actionBean" />

		<s:label class="header1 text-info" name="login" />
		<s:form beanclass="cz.muni.fi.pa165.fast.actionbean.UserActionBean" 
		action="/users/doLogin">
			<s:errors />
                        <c:if test="${actionBean.invalidLogin}">
                            <s:label class="text-error" name="login.invalidLogin"/>
                        </c:if>
                        <c:if test="${actionBean.loggedUser}">
                            <s:label class="text-error" name="login.alreadyLogged"/>
                        </c:if>    
			<div class="control-group">

				<div class="control-group">
					<s:label class="control-label" for="p2" name="user.name" />
					<div class="controls">
						<s:text id="p2" name="userDTO.login" />
					</div>
				</div>

				<div class="control-group">
					<s:label class="control-label" for="p3" name="user.password" />
					<div class="controls">
						<s:password id="p3" name="userDTO.password" />
					</div>
				</div>

				<s:submit  name="doLogin" class="btn btn-info" value="Login" />
                                <%--<s:submit  name="doLogin" class="btn btn-info" />--%>
		</s:form>

	</s:layout-component>
</s:layout-render>

