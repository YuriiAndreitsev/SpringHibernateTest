package com;

import com.configuration.Config;
import com.model.ErrorObj;
import com.model.Product;
import com.service.ProductService;
import com.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = ctx.getBean(UserService.class);
        ProductService productService = ctx.getBean(ProductService.class);


        List<Product> productList = productService.getAllProducts();

        for (Product p : productList) {
            System.out.println(p.getImage());
        }
       


//        System.out.println(productService.getProductById(7).getImage());
//        System.out.println(productService.getProductById(7).getCategories().iterator().next().getCategory());
//        List<Product> productList = productService.getProductsByCategories("cigars");
//
//        for (Product p : productList) {
//            System.out.println(p.getName());
//        }
//        ErrorObj error = new ErrorObj();
//
//        System.out.println(userService.checkEmail("user3"));
    }
}
