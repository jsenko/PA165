<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<s:hidden name="goalDTO.matchId"/>

        <div class="control-group">
            <s:label class="control-label" for="g4" name="goalDTO.goalMinute" />
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


