package springapp.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import springapp.domain.Product;
import springapp.repository.InMemoryProductDao;
import springapp.repository.ProductDao;

public class SimpleProductManagerTests extends TestCase{

	SimpleProductManager simpleProductManager;
	  private List<Product> products;
	    
	    private static int PRODUCT_COUNT = 2;
	    private static int POSITIVE_PRICE_INCREASE = 10;
	    private static Double CHAIR_PRICE = new Double(20.50);
	    private static String CHAIR_DESCRIPTION = "Chair";
	    
	    private static String TABLE_DESCRIPTION = "Table";
	    private static Double TABLE_PRICE = new Double(150.10);    
	protected void setUp() throws Exception {
		simpleProductManager = new SimpleProductManager();
		products = new ArrayList<Product>();
        
        // stub up a list of products
        Product product = new Product();
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        product = new Product();
        product.setDescription("Table");
        product.setPrice(TABLE_PRICE);
        products.add(product);
        ProductDao productDao = new InMemoryProductDao(products);
        simpleProductManager.setProductDao(productDao);
       // simpleProductManager.setProducts(products);
	}
	 public void testGetProductsWithNoProducts() {
		 simpleProductManager = new SimpleProductManager();
		 simpleProductManager.setProductDao(new InMemoryProductDao(null));
	        assertNull(simpleProductManager.getProducts());
	    }
	 
	 public void testGetProducts() {
	        List<Product> products = simpleProductManager.getProducts();
	        assertNotNull(products);        
	        assertEquals(PRODUCT_COUNT, simpleProductManager.getProducts().size());
	    
	        Product product = products.get(0);
	        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
	        assertEquals(CHAIR_PRICE, product.getPrice());
	        
	        product = products.get(1);
	        assertEquals(TABLE_DESCRIPTION, product.getDescription());
	        assertEquals(TABLE_PRICE, product.getPrice());      
	    } 
	  public void testIncreasePriceWithNullListOfProducts() {
	        try {
	            simpleProductManager = new SimpleProductManager();
	            simpleProductManager.setProductDao(new InMemoryProductDao(null));
	            simpleProductManager.increasePrice(POSITIVE_PRICE_INCREASE);
	        }
	        catch(NullPointerException ex) {
	            fail("Products list is null.");
	        }
	    }
	    
	    public void testIncreasePriceWithEmptyListOfProducts() {
	        try {
	            simpleProductManager = new SimpleProductManager();
	            simpleProductManager.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
	            //simpleProductManager.setProducts(new ArrayList<Product>());
	            simpleProductManager.increasePrice(POSITIVE_PRICE_INCREASE);
	        }
	        catch(Exception ex) {
	            fail("Products list is empty.");
	        }           
	    }
	    
	    public void testIncreasePriceWithPositivePercentage() {
	        simpleProductManager.increasePrice(POSITIVE_PRICE_INCREASE);
	        double expectedChairPriceWithIncrease = 22.55;
	        double expectedTablePriceWithIncrease = 165.11;
	        
	        List<Product> products = simpleProductManager.getProducts();      
	        Product product = products.get(0);
	        assertEquals(expectedChairPriceWithIncrease, product.getPrice());
	        
	        product = products.get(1);      
	        assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
	    }
}
