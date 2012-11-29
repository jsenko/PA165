<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<s:label for="p1" name="player.name"/>
<s:text id="p1" name="player.name"/>
<br />
<s:label for="p2" name="player.surname"/>
<s:text id="p2" name="player.surname"/>
<br />  
<s:label for="p3" name="player.age"/>
<s:text id="p3" name="player.age"/>
<br />
<s:label for="p4" name="player.weight"/>
<s:text id="p4" name="player.weight"/>
<br />
<s:label for="p5" name="player.height"/>
<s:text id="p5" name="player.height"/>
<br />
<s:label for="p6" name="player.teamId"/>
<s:select name="player.teamId" id="p6">
    
    <s:options-collection collection="${actionBean.teams}" label="name" value="id"/>             

</s:select><br />
