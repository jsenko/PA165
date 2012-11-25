<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/cover.jsp">
	<s:layout-component name="content">

		<s:useActionBean
			beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean"
			var="actionBean" />

		<h1>Create new Match</h1>

		<s:form beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean">
			<fieldset>
				<s:errors />
			<%@include file="/matches/form.jsp"%>
				
				<s:submit name="add">Create</s:submit>
			</fieldset>
		</s:form>

	</s:layout-component>
</s:layout-render>

