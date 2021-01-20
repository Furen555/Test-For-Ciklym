package it.anichkin.webapps.TestForCiklum.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.dbcp2.BasicDataSource;

import it.anichkin.webapps.TestForCiklum.dao.OrderDao;
import it.anichkin.webapps.TestForCiklum.dao.ProductDao;

public final class ApplicationContext {

	private BasicDataSource dataSource;
	private ProductDao productDao;
	private OrderDao orderDao;
	private String fileName = "src/main/resources/local.properties.txt";

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public static ApplicationContext getApplicationContext() {
		ApplicationContext context = new ApplicationContext();
		context.setConnection();
		context.setProductDao();
		context.setOrderDao();
		return context;
	}

	private void setProductDao() {
		this.productDao = new ProductDao(getDataSource());
	}

	private void setOrderDao() {
		this.orderDao = new OrderDao(getDataSource());
	}

	private void setConnection() {
		BasicDataSource ds = new BasicDataSource();
		File file = new File(fileName);
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);) {
			String line;
			if ((line = br.readLine()) != null) {
				ds.setDriverClassName(line);
			}
			if ((line = br.readLine()) != null) {
				ds.setUrl(line);
			}
			if ((line = br.readLine()) != null) {
				ds.setUsername(line);
			}
			if ((line = br.readLine()) != null) {
				ds.setPassword(line);
			}
			if ((line = br.readLine()) != null) {
				ds.setInitialSize(Integer.parseInt(line));
			}
			if ((line = br.readLine()) != null) {
				ds.setMaxTotal(Integer.parseInt(line));
			}
			this.dataSource = ds;
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}

	}

	public void shutDown() {
		try {
			dataSource.close();
		} catch (Exception e) {
		}
	}

}
