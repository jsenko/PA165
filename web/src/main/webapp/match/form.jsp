<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table>
    <tr>
        <th><s:label for="m1" name="match.round" /></th>
        <td><s:text id="m1" name="matchDTO.round" /></td>
    </tr>
    <tr>
        <th><s:label for="m7"  name="match.date" /></th>
        <td><s:text name="matchDTO.date" formatPattern="MM/dd/yyyy" id="m7" class="m7"/></td>
    </tr>

    <tr>
        <th><s:label for="m5" name="match.hTeam" /></th>
        <td><s:select name="matchDTO.homeTeamId" id="m5">
                <s:options-collection collection="${actionBean.teams}"
                                      label="name" value="id" />
            </s:select></td>
    </tr>
    <tr>
        <th><s:label for="m6"  name="match.aTeam" /></th>
        <td><s:select name="matchDTO.awayTeamId" id="m6">
                <s:options-collection collection="${actionBean.teams}"
                                      label="name" value="id" />
            </s:select></td>
    </tr>
    <script type="text/javascript">
        $(function() {
        $.datepicker.regional['sk'] = {clearText: 'Vyčistiť', clearStatus: 'Zmazať aktuálny dátum',
    closeText: 'Zatvoriť', closeStatus: 'Zatvoriť bez zmien',
    prevText: '<Pred', prevStatus: 'Zobraziť predchádzjúci mesiac',
    nextText: 'Nasl>', nextStatus: 'Zobraziť nasledujúci mesiac',
    currentText: 'Dnes', currentStatus: 'Zobraziť aktuálny mesiac',
    monthNames: ['Január','Február','Marec','Apríl','Máj','Jún',
    'Júl','August','September','Október','November','December'],
    monthNamesShort: ['Jan','Feb','Mar','Apr','Máj','Jún',
    'Júl','Aug','Sep','Okt','Nov','Dec'],
    monthStatus: 'Zobraziť iný mesiac', yearStatus: 'Zobraziť iný rok',
    weekHeader: 'Tý', weekStatus: 'Týždeň roku',
    dayNames: ['Nedeľa','Pondelok','Utorok','Streda','Štvrtok','Piatok','Sobota'],
    dayNamesShort: ['Ned','Pon','Uto','Str','Štv','Pia','Sob'],
    dayNamesMin: ['Ne','Po','Ut','St','Št','Pia','So'],
    dayStatus: 'Nastaviť DD ako prvý deň v týždni', dateStatus: 'Vybrať DD, MM d',
    dateFormat: 'dd.mm.yy', firstDay: 1, 
    initStatus: 'Vybrať dátum', isRTL: false};
        $( "#m7" ).datepicker( $.datepicker.regional[ "${actionBean.context.locale}" ] );
    });
                                </script>
</table>


