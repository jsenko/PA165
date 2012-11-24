<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="p1" name="player.name"/></th>
        <td><s:text id="p1" name="player.name"/></td>
    </tr>
    <tr>
        <th><s:label for="p2" name="player.surname"/></th>
        <td><s:text id="p2" name="player.surname"/></td>
    </tr>
    <tr>
        <th><s:label for="p3" name="player.age"/></th>
        <td><s:text id="p3" name="player.age"/></td>
    </tr>
    <tr>
        <th><s:label for="p4" name="player.weight"/></th>
        <td><s:text id="p4" name="player.weight"/></td>
    </tr>
    <tr>
        <th><s:label for="p5" name="player.height"/></th>
        <td><s:text id="p5" name="player.height"/></td>
    </tr>
    <tr>
        <th><s:label for="p6" name="player.teamId"/></th>
        <td><s:select name="player.teamId" id="p6">
                <s:options-collection collection="${actionBean.teams}" label="name" value="id"/>             
            </s:select></td>
    </tr>
</table>