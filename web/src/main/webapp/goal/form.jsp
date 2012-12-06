<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<s:hidden name="goalDTO.matchId"/>
<%--
    <tr>
        <th><s:label for="g1" name="goal.match" /></th>
        
        <td><s:select name="goalDTO.matchId" id="g1">
                <s:options-collection collection="${actionBean.matches}"
                                      label="id" value="id" />
            </s:select></td>
            
    </tr>--%>
        <div class="control-group">
            <s:label class="control-label" for="g4" name="goal.minute" />
            <div class="controls">
                <s:text name="goalDTO.goalMinute" id="g4" />
            </div>
        </div>
        
        <div class="control-group">
            <s:label class="control-label" for="g2"  name="goal.sPlayer" />
            <div class="controls">
                <s:select name="goalDTO.scoredPlayerId" id="g2">
                    <s:options-collection collection="${actionBean.players}" value="id" group="teamName" />
                </s:select>
            </div>
        </div>
        <div class="control-group">
            <s:label class="control-label" for="g3"  name="goal.aPlayer" />
            <div class="controls">
                <s:select name="goalDTO.assistPlayerId" id="g3">
                    <s:options-collection collection="${actionBean.players}" value="id" group="teamName" />
                </s:select>
            </div>
        </div>


