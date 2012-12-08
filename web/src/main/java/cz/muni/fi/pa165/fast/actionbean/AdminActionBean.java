package cz.muni.fi.pa165.fast.actionbean;

import cz.muni.fi.pa165.fast.users.Admin;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

/**
 *
 * @author Lauro
 */
@UrlBinding("/admin/{$event}")
public class AdminActionBean implements ActionBean {

    private ActionBeanContext context;
    @ValidateNestedProperties(value = {
        @Validate(on = {"login"}, field = "name", required = true, expression = "admin"),
        @Validate(on = {"login"}, field = "password", required = true)
    })
    private Admin admin;

    public Resolution login() {
        if (admin.getName().equals("admin") && admin.getPassword().equals("admin")) {
            HttpSession session = context.getRequest().getSession();
            Long adminSession = (Long) session.getAttribute("session");
            if (adminSession == null) {
                Long newSession = 1l;
                session.setAttribute("session", newSession);
            }

            return new RedirectResolution("");
        } else {
            return new RedirectResolution("/admin/invalid.jsp");
        }

    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
}
