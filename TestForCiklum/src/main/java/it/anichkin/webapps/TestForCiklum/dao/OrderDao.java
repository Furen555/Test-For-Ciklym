package it.anichkin.webapps.TestForCiklum.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import it.anichkin.webapps.TestForCiklum.dao.rsh.OrderIdRSH;
import it.anichkin.webapps.TestForCiklum.dao.rsh.OrderResulSetHandler;
import it.anichkin.webapps.TestForCiklum.models.Order;
import it.anichkin.webapps.TestForCiklum.models.Product;

public class OrderDao {
	DataSource dataSource;

	public OrderDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private final QueryRunner queryRunner = new QueryRunner();

	public void createOrder(Order order) {

		try {
			Connection connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			queryRunner.update(connection, "INSERT INTO orders VALUES (nextval('orders_id_seq'),?,?,?)",
					order.getUserId(), order.getStatus(), order.getCreatedAt());
			for (Product product : order.getProducts()) {
				queryRunner.update(connection, "INSERT INTO order_items VALUES (currval('orders_id_seq'),?,?)",
						product.getId(), product.getQuantity());
			}

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Order showOrderById(int id) {

		try {
			Connection connection = dataSource.getConnection();
			return queryRunner.query(connection, "SELECT * FROM orders WHERE id=?", new OrderResulSetHandler(), id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Integer> showAllOrdersId() {
		try {
			Connection connection = dataSource.getConnection();
			return queryRunner.query(connection, "SELECT id FROM orders", new OrderIdRSH());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
