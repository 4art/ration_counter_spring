package de.metraf.service;

import de.metraf.model.Product;
import de.metraf.model.ProductRation;
import de.metraf.model.Ration;
import de.metraf.model.User;
import de.metraf.repository.RationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by metraf on 16.06.17.
 */
@Service
public class RationServiceImpl implements RationService {
    private RationRepository rationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    public RationServiceImpl(RationRepository rationRepository) {
        this.rationRepository = rationRepository;
    }

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    public void setRationRepository(RationRepository rationRepository) {
        this.rationRepository = rationRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Collection<Ration> findAll() {
        Collection<Ration> rations = rationRepository.findAll();
        return rations;
    }

    @Override
    public Ration save(Ration ration) {
        ration.setDatetime(getDateTime());
        rationRepository.save(ration);
        return ration;
    }

    @Override
    public void delete(Long id) {
        rationRepository.delete(id);
    }

    @Override
    public Ration findOne(Long id) {
        return rationRepository.findOne(id);
    }

    @Override
    public Collection<Ration> findByUserID(int user_id) {
        return rationRepository.findByUserID(user_id);
    }

    @Override
    public Collection<Ration> findByUserIDBetweenTimes(int user_id, String startDatetime, String endDateTime) {

        return rationRepository.findByUserIDBetweenTimes(user_id, startDatetime, endDateTime);
    }

    public static String getDateTime() {
        String[] dateTimeParts = LocalDateTime.now().toString().split("T");
        return dateTimeParts[0] + " " + dateTimeParts[1];
    }

    @Override
    public Collection<Ration> findByProductID(Long productID) {
        return rationRepository.findByProductID(productID);
    }

    @Override
    public void saveRationFromProductCollection(Collection<ProductRation> productsRations) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            String datetime = getDateTime();
            for (ProductRation p : productsRations) {
                save(new Ration(
                        p.getWeight(),
                        user.getId(),
                        p.getId(),
                        datetime
                ));
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public Collection<ProductRation> getListProductRationToListRation(Collection<Ration> rations) {
        Collection<ProductRation> productRations = new LinkedList<>();
        Long counter = 0L;
        for(Ration ration : rations){
            Product product = productService.findOne(ration.getId());
            productRations.add(new ProductRation(
               counter,
                    product.getName(),
                    toTwoFixed(product.getProtein() * ration.getWeight()),
                    toTwoFixed(product.getFat() * ration.getWeight()),
                    toTwoFixed(product.getCarbo() * ration.getWeight()),
                    toTwoFixed(product.getCarbo() * ration.getWeight()),
                    ration.getWeight()
            ));
            counter++;
        }
        return productRations;
    }

    public static double toTwoFixed(double a){
        String s = String.format("%.2f", a);
        s = s.replace(",", ".");
        return Double.valueOf(s);
    }


}

