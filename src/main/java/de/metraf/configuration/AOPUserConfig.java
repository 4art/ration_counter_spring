package de.metraf.configuration;

import de.metraf.model.User;
import de.metraf.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * Created by metraf on 04.06.17.
 */
@Configuration
@Aspect
public class AOPUserConfig {
    @Autowired
    private UserService userService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
    }

    @Pointcut("execution(* de.metraf.controller.LoginController.*(..))")
    public void loginPointcut() {
    }

    @Pointcut("execution(* de.metraf.controller.RationController.*(..))")
    public void rationPointcut() {
    }

    @Around("rationPointcut() && requestMapping() || loginPointcut() && requestMapping()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ModelAndView modelAndView = (ModelAndView) proceedingJoinPoint.proceed();
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("login", user.getName());
            System.out.println("username added");
        }catch (Exception e){
            System.out.println("Auth exeption");
        }
        return modelAndView;
    }
}
