<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="control-group">
    <s:label class="control-label" for="m1" name="matchDTO.round" />
    <div class="controls">
        <s:text id="m1" name="matchDTO.round" />
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="m7"  name="matchDTO.date" />
    <div class="controls">
        <s:text name="matchDTO.date" formatPattern="MM/dd/yyyy" id="m7" class="m7"/>
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="m5" name="matchDTO.homeTeamId" />
    <div class="controls">
        <s:select name="matchDTO.homeTeamId" id="m5">
            <s:options-collection collection="${actionBean.teams}" label="name" value="id" />
        </s:select>
    </div>
</div>

<div class="control-group">
    <s:label class="control-label" for="m6"  name="matchDTO.awayTeamId" />
    <div class="controls"> 
        <s:select name="matchDTO.awayTeamId" id="m6">
            <s:options-collection collection="${actionBean.teams}"  label="name" value="id" />
        </s:select>
    </div>
</div>

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



