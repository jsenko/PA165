<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table>
<s:hidden name="goalDTO.matchId"/>
<%--
    <tr>
        <th><s:label for="g1" name="goal.match" /></th>
        
        <td><s:select name="goalDTO.matchId" id="g1">
                <s:options-collection collection="${actionBean.matches}"
                                      label="id" value="id" />
            </s:select></td>
            
    </tr>--%>
    <tr>
        <th><s:label for="g4" name="goal.minute" /></th>
        <th><s:text name="goalDTO.goalMinute" id="g4" /></th>
    </tr>
    <tr>
        <th><s:label for="g2"  name="goal.sPlayer" /></th>
        <td><s:select name="goalDTO.scoredPlayerId" id="g2">
                <s:options-collection collection="${actionBean.players}"
                                      label="name" value="id" group="teamId" />
            </s:select></td>
    </tr>
    <tr>
        <th><s:label for="g3"  name="goal.aPlayer" /></th>
        <td><s:select name="goalDTO.assistPlayerId" id="g3">
                <s:options-collection collection="${actionBean.players}"
                                      label="name" value="id" group="teamId" />
            </s:select></td>
    </tr>
</table>


