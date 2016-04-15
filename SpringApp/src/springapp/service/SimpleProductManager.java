package springapp.service;

import java.util.List;

import springapp.domain.Product;
import springapp.repository.ProductDao;

public class SimpleProductManager implements ProductManager {
	
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private List<Product> products;
	  private ProductDao productDao;
	@Override
	public void increasePrice(int percentage) {
		List<Product> products = productDao.getProductList();
		   if (products != null) {
	            for (Product product : products) {
	                double newPrice = product.getPrice().doubleValue() * 
	                                    (100 + percentage)/100;
	                product.setPrice(newPrice);
	                productDao.saveProduct(product);
	            }
	        }
	}

	@Override
	public List<Product> getProducts() {
		 return productDao.getProductList();
	}


	   public void setProductDao(ProductDao productDao) {
	        this.productDao = productDao;
	    }
}
