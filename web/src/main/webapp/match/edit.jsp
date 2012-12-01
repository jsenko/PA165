<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:layout-render name="/cover.jsp">
    <s:layout-component name="content">

        <s:useActionBean
            beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean"
            var="actionBean" />

        <s:label name="match.editMatch" class="text-info header1"/>

        <s:form beanclass="cz.muni.fi.pa165.fast.actionbean.MatchActionBean" class="form-horizontal">
            <fieldset>
                <s:errors />
                <s:hidden name="matchDTO.id"/>
                <%@include file="/match/form.jsp"%>

                <s:submit class="btn btn-warning" name="save"/>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>

