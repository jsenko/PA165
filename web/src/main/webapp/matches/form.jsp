<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

				
				<table>
					<tr>
						<th><s:label for="p4" name="Round" /></th>
						<td><s:text id="p4" name="matchDTO.round" /></td>
					</tr>
					<tr>
						<th><s:label for="p1" name="Date [1-31]" /></th>
						<td><s:text id="p1" name="date" /></td>
					</tr>
					<tr>
						<th><s:label for="p2" name="Month [1-12]" /></th>
						<td><s:text id="p2" name="month" /></td>
					</tr>
					<tr>
						<th><s:label for="p3" name="Year" /></th>
						<td><s:text id="p3" name="year" /></td>
					</tr>


					<tr>
						<th><s:label for="p6" name="Home Team" /></th>
						<td><s:select name="matchDTO.homeTeamId" id="p6">
								<s:options-collection collection="${actionBean.teams}"
									label="name" value="id" />
							</s:select></td>
					</tr>
					
										<tr>
						<th><s:label for="p7"  name="Away Team" /></th>
						<td><s:select name="matchDTO.awayTeamId" id="p7">
								<s:options-collection collection="${actionBean.teams}"
									label="name" value="id" />
							</s:select></td>
					</tr>
				</table>


