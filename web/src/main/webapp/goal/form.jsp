<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

				
				<table>



	<tr>
		<th><s:label for="p7" name="Match" /></th>
		<td><s:select name="matchId" id="p7">
				<s:options-collection collection="${actionBean.matches}"
					label="id" value="id" />
			</s:select></td>
	</tr>
	
											<tr>
						<th><s:label for="p7"  name="Scoring player" /></th>
						<td><s:select name="goalDTO.scoredPlayerId" id="p7">
								<s:options-collection collection="${actionBean.players}"
									label="name" value="id" />
							</s:select></td>
					</tr>
					
																<tr>
						<th><s:label for="p7"  name="assisting player" /></th>
						<td><s:select name="goalDTO.assistPlayerId" id="p7">
								<s:options-collection collection="${actionBean.players}"
									label="name" value="id" />
							</s:select></td>
					</tr>
</table>


