<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<div class="control-group">
    <s:label class="control-label" for="u1" name="user.name"/>
    <div class="controls">
        <s:text id="u1" name="userDTO.login"/>
    </div>
</div>
    
<div class="control-group">
    <s:label class="control-label" for="u2" name="user.password"/>
    <div class="controls">
        <s:password id="u2" name="userDTO.password"/>
    </div>
</div>
