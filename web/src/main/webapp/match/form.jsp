<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table>
    <tr>
        <th><s:label for="m1" name="Round" /></th>
        <td><s:text id="m1" name="matchDTO.round" /></td>
    </tr>
    <!--<tr>
        <th><s:label for="m2" name="Date [1-31]" /></th>
        <td><s:text id="m2" name="date" /></td>
    </tr>
    <tr>
        <th><s:label for="m3" name="Month [1-12]" /></th>
        <td><s:text id="m3" name="month" /></td>
    </tr>
    <tr>
        <th><s:label for="m4" name="Year" /></th>
        <td><s:text id="m4" name="year" /></td>
    </tr>-->

    <tr>
        <th><s:label for="m7"  name="Date" /></th>
        <td><s:text name="matchDTO.date" formatPattern="MM/dd/yyyy" id="m7" class="m7"/></td>
    </tr>

    <tr>
        <th><s:label for="m5" name="Home Team" /></th>
        <td><s:select name="matchDTO.homeTeamId" id="m5">
                <s:options-collection collection="${actionBean.teams}"
                                      label="name" value="id" />
            </s:select></td>
    </tr>

    <tr>
        <th><s:label for="m6"  name="Away Team" /></th>
        <td><s:select name="matchDTO.awayTeamId" id="m6">
                <s:options-collection collection="${actionBean.teams}"
                                      label="name" value="id" />
            </s:select></td>
    </tr>
    <script type="text/javascript">
        $(function() {
        $( "#m7" ).datepicker();
    });
                                </script>
</table>


