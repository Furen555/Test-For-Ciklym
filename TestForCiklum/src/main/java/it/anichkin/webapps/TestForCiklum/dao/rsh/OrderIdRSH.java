package it.anichkin.webapps.TestForCiklum.dao.rsh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class OrderIdRSH implements ResultSetHandler<List<Integer>> {

	@Override
	public List<Integer> handle(ResultSet rs) throws SQLException {
		ArrayList<Integer> result = new ArrayList();
		while (rs.next()) {
			result.add(rs.getInt("id"));
		}
		return result;
	}

}
