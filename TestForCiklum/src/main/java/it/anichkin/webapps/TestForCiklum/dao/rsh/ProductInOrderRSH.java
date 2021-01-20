package it.anichkin.webapps.TestForCiklum.dao.rsh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import it.anichkin.webapps.TestForCiklum.models.Product;

public class ProductInOrderRSH implements ResultSetHandler<List<Product>> {
	public List<Product> handle(ResultSet rs) throws SQLException {
		List<Product> result = new ArrayList<>();
		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setStatus(rs.getString("status"));
			product.setCreatedAt(new Date(rs.getTimestamp("created_at").getTime()));
			product.setQuantity(rs.getInt("quantity"));
			result.add(product);

		}
		return result;
	}
}
