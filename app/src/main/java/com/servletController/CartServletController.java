package com.servletController;

import com.configuration.Config;
import com.model.Product;
import com.service.ProductService;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller

public class CartServletController {

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(ModelMap model, HttpSession session) {

        Map<Product, Integer> productList = new HashMap<>();
        if (session.getAttribute("cart") != null) {
            productList = (Map<Product, Integer>) session.getAttribute("cart");

            model.addAttribute("productList", productList);
        }
        return "cartview";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @ResponseBody
    public String cart(ModelMap model, HttpSession session,
                       @RequestParam(value = "id", required = false) String id,
                       @RequestParam(value = "qnt", required = false) String qnt) {
        JSONObject j = new JSONObject();
        Integer productId = Integer.parseInt(id);
        Integer productQnt = Integer.valueOf(qnt);

        if (productId != null) {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
            ProductService productService = ctx.getBean(ProductService.class);
//            DAOFactory factory = DAOFactory.getFactory(1);
//            ProductControllerDAO productController = factory.getProductControllerDAO();

            if (session.getAttribute("cart") == null) {
                Map<Product, Integer> productList = new HashMap<>();
                session.setAttribute("cart", productList);
            }
            Product product = productService.getProductById(productId);
            @SuppressWarnings("unchecked")
            Map<Product, Integer> productList = (Map<Product, Integer>) session.getAttribute("cart");
            if (productList.get(product) == null) {
                productList.put(product, productQnt);
            } else {
                int quantity = productList.get(product);
                productList.put(product, quantity + productQnt);
            }

            int cartSize = 0;
            for (Product p : productList.keySet()) {
                cartSize += productList.get(p);
            }

            try {
                j.put("cartSize", String.valueOf(cartSize));
//                j.put("cart", productList);
//                j.put("productList", productList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.setAttribute("cart", productList);
            session.setAttribute("cartSize", cartSize);
            model.addAttribute("productList", productList);
        }
//        System.out.println(j.toString());
        return j.toString();
    }

}

