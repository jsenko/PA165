package cz.muni.fi.pa165.fast.security;

import java.lang.annotation.*;

/**
 * 
 * @author Jakub Senko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Acl
{
    Role value();
}
