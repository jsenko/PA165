<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">
    
		<s:form beanclass="cz.muni.fi.pa165.fast.actionbean.TestActionBean">
			<fieldset>
			<s:errors />
				<s:submit name="submit">Save</s:submit>
			</fieldset>
		</s:form>

    </s:layout-component>
</s:layout-render>

