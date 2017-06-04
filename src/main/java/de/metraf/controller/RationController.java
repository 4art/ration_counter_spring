package de.metraf.controller;

import de.metraf.model.Contact;
import de.metraf.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by metraf on 26.05.17.
 */
@Controller
public class RationController {
    @Autowired
    private ContactService contactService;

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
        modelAndView.setViewName("contact");
        return modelAndView;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView saveContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            contactService.save(contact);
        }
        modelAndView.setViewName("contact");
        return modelAndView;
    }
}
