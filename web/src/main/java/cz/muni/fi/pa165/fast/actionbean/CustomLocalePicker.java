/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.actionbean;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.localization.DefaultLocalePicker;
import org.apache.log4j.Logger;

/**
 *
 * @author Stefan
 */
public class CustomLocalePicker extends DefaultLocalePicker {

    public static final String LOCALE = "locale" ;
    
    @Override
    public Locale pickLocale(HttpServletRequest request) {
       
        HttpSession session= request.getSession();
        String requestLocaleAttr = (String) request.getParameter(LOCALE);
        if(requestLocaleAttr!=null) session.setAttribute(LOCALE, requestLocaleAttr);
        String sessionLocaleAttr = (String) session.getAttribute(LOCALE);
        
        if(sessionLocaleAttr!=null){
            return new Locale(sessionLocaleAttr);
        }
        else{
            return new Locale("en");
        }
        
    }
}