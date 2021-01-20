package it.anichkin.webapps.TestForCiklum.dao.rsh;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import it.anichkin.webapps.TestForCiklum.models.Order;

public class OrderResulSetHandler implements ResultSetHandler<Order> {

	@Override
	public Order handle(ResultSet rs) throws SQLException {
		Order result = new Order();
		if (rs.next()) {
			result.setId(rs.getInt("id"));
			result.setUserId(rs.getInt("user_id"));
			result.setStatus(rs.getString("status"));
			result.setCreatedAt(rs.getString("created_at"));
		}
		return result;
	}

}
