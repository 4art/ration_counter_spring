package de.metraf.controller;

import de.metraf.model.Contact;
import de.metraf.model.ProductRation;
import de.metraf.model.User;
import de.metraf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
//todo remove Registration, Einloggen from view for auth users
/**
 * Created by metraf on 26.05.17.
 */
@Controller
public class RationController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private RationService rationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView productsView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("counter");
        return modelAndView;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView contactView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", new Contact());
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView saveContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            User user = userService.findUserByEmail(auth.getName());
            contact.setName(user.getName());
            contact.setEmail(user.getEmail());
            contactService.save(contact);
            modelAndView.addObject("successMessage", "Ihre Nachricht wurde erfolgreich gesendet.");
            contact.setText(null);
        } catch (Exception e) {
            if (!bindingResult.hasErrors()) {
                contactService.save(contact);
                contact.setName(null);
                contact.setEmail(null);
                contact.setText(null);
                modelAndView.addObject("successMessage", "Ihre Nachricht wurde erfolgreich gesendet.");
            }
        }

        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @RequestMapping(value = "/myrat", method = RequestMethod.GET)
    public ModelAndView myRationView(){
        User authUser = userService.getAuthUser();
        Collection<ProductRation> productRations = rationService.getListProductRationToListRation(rationService.findByUserIDBetweenTimes(authUser.getId(), "2017-01-01 00:00:00", RationServiceImpl.getDateTime()));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("rations", productRations);
        modelAndView.setViewName("myRation");
        return modelAndView;
    }
}
