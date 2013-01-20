package cz.muni.fi.pa165.fast.security.impl;

import java.security.AccessControlException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import cz.muni.fi.pa165.fast.security.SecurityFacade;

@Stateless
public class AuthorizationInterceptor
{
    @EJB
    SecurityFacade sf;
    
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception
    {
       if(!sf.authorize(
               ctx.getMethod()))
       {
           throw new AccessControlException("Operation '" 
                   + ctx.getMethod().getName()
                   + "' is not authorized for current user.");
       }
       
       return ctx.proceed();
    }
}
