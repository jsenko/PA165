<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<div class="control-group">
    <s:label class="control-label" for="p1" name="player.name"/>
    <div class="controls">
        <s:text id="p1" name="player.name"/>
    </div>
</div>
        
<div class="control-group">         
    <s:label class="control-label" for="p2" name="player.surname"/>
    <div class="controls">
        <s:text id="p2" name="player.surname"/>
    </div>
</div>
    
<div class="control-group">         
    <s:label class="control-label" for="p3" name="player.age"/>
    <div class="controls">
        <s:text id="p3" name="player.age"/>
    </div>
</div>
    
<div class="control-group">         
    <s:label class="control-label" for="p4" name="player.weight"/>
    <div class="controls">
        <s:text id="p4" name="player.weight"/>
    </div>
</div>
    
<div class="control-group">         
    <s:label class="control-label" for="p5" name="player.height"/>
    <div class="controls">  
        <s:text id="p5" name="player.height"/>
    </div>
</div>
    

<%--
<s:hidden name="player.teamId" />
<s:select name="player.teamId" id="p6">
    
    <s:options-collection collection="${actionBean.teams}" label="name" value="id"/>  
    </s:select>           
--%>
<br />
