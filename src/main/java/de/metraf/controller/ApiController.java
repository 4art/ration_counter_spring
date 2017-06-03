package de.metraf.controller;

import de.metraf.model.Product;
import de.metraf.model.User;
import de.metraf.repository.ProductsRepository;
import de.metraf.service.ProductService;
import de.metraf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by metraf on 30.05.17.
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Set<Product>> getAllProducts() {
        Set<Product> products = productService.findAll();
        if (products == null) {
            return new ResponseEntity<Set<Product>>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.findOne(id);
        if (product == null) {
            return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
        Product updatedProduct = productService.save(product);
        if (updatedProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @RequestMapping(value = "/checkNewName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map> checkNewName(@PathVariable String name) {
        User user = userService.findByName(name);
        Map res = new HashMap();
        res.put("uniq", user == null ? true : false);
        return new ResponseEntity<Map>(res, HttpStatus.OK);
    }


}
