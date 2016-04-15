package springapp.web;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import springapp.service.ProductManager;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InventoryController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Returning hello view");
        String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);
        Map<String,Object> modelMap= new HashMap<String,Object>();
        modelMap.put("now", now);
        modelMap.put("products",this.productManager.getProducts());
        //return new ModelAndView("WEB-INF/jsp/hello.jsp", "now", now);
        return new ModelAndView("hello", "model",modelMap);
    }
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
}