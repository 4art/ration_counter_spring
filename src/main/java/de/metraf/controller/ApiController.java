package de.metraf.controller;

import de.metraf.model.Product;
import de.metraf.model.ProductRation;
import de.metraf.model.User;
import de.metraf.model.WeatherModern;
import de.metraf.service.ProductService;
import de.metraf.service.RationService;
import de.metraf.service.UserService;
import de.metraf.service.WeatherService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by metraf on 30.05.17.
 */
@Async
@RestController
public class ApiController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private RationService rationService;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Set<Product>> getAllProducts() {
        Set<Product> products = productService.findAll();
//        Set<Product> products = null;
        if (products == null) {
            return new ResponseEntity<Set<Product>>(products, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.findOne(id);
        if (product == null) {
            return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
        Product updatedProduct = productService.save(product);
        if (updatedProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/checkNewName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map> checkNewName(@PathVariable String name) {
        User user = userService.findByName(name);
        Map res = new HashMap();
        res.put("uniq", user == null ? true : false);
        return new ResponseEntity<Map>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/weather/{city}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WeatherModern> getWeatherByCity(@PathVariable String city) {
        WeatherModern weatherModern = weatherService.getWeatherModern(city);
        if (weatherModern != null) {
            return new ResponseEntity<WeatherModern>(weatherModern, HttpStatus.OK);
        }
        return new ResponseEntity<WeatherModern>(weatherModern, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/secure/api/ration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<ProductRation>> getProductRation(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to) {

        User user = userService.getAuthUser();
        Collection<ProductRation> productRations = rationService.getListProductRationToListRation(rationService.findByUserIDBetweenTimes(user.getId(), new String(Base64Utils.decodeFromString(from)), new String(Base64Utils.decodeFromString(to))));
        return new ResponseEntity<Collection<ProductRation>>(productRations, HttpStatus.OK);
    }

    @RequestMapping(value = "/secure/api/saveRation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map> saveSecureRation(@RequestBody Collection<ProductRation> rationProducts) {
        Map returnMap = new HashMap();
        if (rationProducts != null) {
            returnMap.put("status", "success");
            rationService.saveRationFromProductCollection(rationProducts);
            return new ResponseEntity<Map>(returnMap, HttpStatus.OK);
        }
        returnMap.put("status", "error");
        return new ResponseEntity<Map>(returnMap, HttpStatus.NO_CONTENT);
    }

}
