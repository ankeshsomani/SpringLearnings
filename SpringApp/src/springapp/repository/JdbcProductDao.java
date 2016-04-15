package springapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import springapp.domain.Product;

public class JdbcProductDao extends SimpleJdbcDaoSupport implements ProductDao{
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public List<Product> getProductList() {
		logger.info("Getting products!");
		String sql="select id, description, price from products";
		List<Product> products = getSimpleJdbcTemplate().query(sql, new ProductMapper());
		return products;
	}

	@Override
	public void saveProduct(Product prod) {
		logger.info("saving products!");
		Map<String,Object> values= new HashMap<String,Object>();
		values.put("description", prod.getDescription());
		values.put("id", prod.getId());
		values.put("price", prod.getPrice());
		int count=getSimpleJdbcTemplate().update("update products set description=:description,"
				+ "price=:price where id =:id"
				, new MapSqlParameterSource(values));
		logger.info("Rows affected: " + count);
	}

	private static class ProductMapper implements ParameterizedRowMapper<Product>{

		@Override
		public Product mapRow(ResultSet rs, int rownum) throws SQLException {
			Product prod=new Product();
			prod.setDescription(rs.getString("description"));
			prod.setId(rs.getInt("id"));
			prod.setPrice(rs.getDouble("price"));
			return prod;
		}
		
	}
}
