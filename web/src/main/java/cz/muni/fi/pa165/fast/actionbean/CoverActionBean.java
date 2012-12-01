/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fast.actionbean;

import cz.muni.fi.pa165.fast.actionbean.context.PlayerActionBeanContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;

/**
 *
 * @author Stefan
 */
public class CoverActionBean implements ActionBean {

    private ActionBeanContext context;
   
    public Resolution sk(){
        System.out.println("prepnutie do slovenciny");
        return new RedirectResolution("/");
    }
    
    public Resolution en(){
        System.out.println("switch to english");
        return new RedirectResolution("/");
    }

    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return this.context;
    }
}
