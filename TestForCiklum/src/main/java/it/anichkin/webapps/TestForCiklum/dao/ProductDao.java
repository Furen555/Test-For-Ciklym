package it.anichkin.webapps.TestForCiklum.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import it.anichkin.webapps.TestForCiklum.dao.rsh.ProductInOrderRSH;
import it.anichkin.webapps.TestForCiklum.dao.rsh.ProductResultSetHandler;
import it.anichkin.webapps.TestForCiklum.models.Order;
import it.anichkin.webapps.TestForCiklum.models.Product;

public class ProductDao {
	DataSource dataSource;

	public ProductDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private final QueryRunner queryRunner = new QueryRunner();

	public void createProduct(Product product) {
		try {
			Connection connection = dataSource.getConnection();
			queryRunner.update(connection, "INSERT INTO products VALUES (nextval('products_id_seq'),?,?,?)",
					product.getName(), product.getPrice(), product.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something wrong, please try again");
		}

	}

	public List<Product> getAllProducts() {
		try {
			Connection connection = dataSource.getConnection();
			return queryRunner.query(connection, "SELECT * FROM products", new ProductResultSetHandler());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something wrong, please try again");
			return null;
		}

	}

	public List<Product> getProductByOrder(Order order) {
		try {
			Connection connection = dataSource.getConnection();
			return queryRunner.query(connection,
					"SELECT id,name,price,status,created_at,quantity FROM products,order_items WHERE order_items.product_id=products.id AND order_items.order_id=?",
					new ProductInOrderRSH(), order.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
