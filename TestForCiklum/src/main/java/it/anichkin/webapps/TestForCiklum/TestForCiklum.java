package it.anichkin.webapps.TestForCiklum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import it.anichkin.webapps.TestForCiklum.Context.ApplicationContext;
import it.anichkin.webapps.TestForCiklum.models.Order;
import it.anichkin.webapps.TestForCiklum.models.Product;

public class TestForCiklum {
	static ApplicationContext context = ApplicationContext.getApplicationContext();
	static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	static Date date = new Date();

	public static void main(String[] args) {

		operations();

	}

	public static void operations() {
		System.out.println("Chose what to do:");
		System.out.println("1 - Create product");// +
		System.out.println("2 - Create order");// +
		System.out.println("3 - Update order by id");
		System.out.println("4 - Show all products");// +
		System.out.println("5 - List all products, which have been ordered at least once");
		System.out.println("6 - Show order by order id");// +
		System.out.println("7 - Show all orders");// +
		System.out.println("8 - Remove product by id");
		System.out.println("9 - Remove all products");
		System.out.println("0 - Shut Down");// +
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		switch (num) {
		case (1):
			createProduct();
			break;
		case (2):
			createOrder();
			break;
		case (3):
			System.out.println("3");
			break;
		case (4):
			showAllProducts();
			break;
		case (5):
			System.out.println("5");
			break;
		case (6):
			showOrderById();
			break;
		case (7):
			showAllOrders();
			break;
		case (8):
			System.out.println("8");
		case (9):
			System.out.println("9");
			break;
		}
		if (num != 0)
			operations();

	}

	private static void createProduct() {
		System.out.println("write all product characteristics (after entering each press enter)");
		System.out.println("name, price");
		Product product = new Product();
		Scanner in = new Scanner(System.in);
		product.setName(in.nextLine());
		product.setPrice(in.nextInt());
		System.out.println("choose status:");
		System.out.println("1 - " + ProductStatus.in_stock);
		System.out.println("2 - " + ProductStatus.out_of_stock);
		System.out.println("3 - " + ProductStatus.running_low);
		switch (in.nextInt()) {
		case (1):
			product.setStatus(ProductStatus.in_stock.toString());
			break;
		case (2):
			product.setStatus(ProductStatus.out_of_stock.toString());
			break;
		case (3):
			product.setStatus(ProductStatus.running_low.toString());
			break;
		}
		context.getProductDao().createProduct(product);

	}

	private static void createOrder() {
		Scanner in = new Scanner(System.in);
		Order order = new Order();
		System.out.println("Set status of order");
		order.setStatus(in.nextLine());
		order.setUserId((int) (1 + Math.random() * 50));
		List<Product> products = context.getProductDao().getAllProducts();
		Collections.sort(products);
		for (Product product : products) {
			System.out.println("|" + product.getId() + "|" + product.getName() + "|" + product.getPrice() + "|"
					+ product.getStatus() + "|");
		}
		System.out.println("select the required product by id(0 to exit)");
		List<Product> result = new ArrayList<Product>();
		for (int i = 0; i < products.size(); i++) {
			int num = in.nextInt();
			int quantity = in.nextInt();
			if (num != 0) {
				for (Product product : products) {
					if (product.getId() == num) {
						product.setQuantity(quantity);
						result.add(product);
					}
				}

			} else {
				break;
			}
		}
		order.setProducts(result);

		order.setCreatedAt(formatter.format(date).toString());
		context.getOrderDao().createOrder(order);

	}

	private static void updateOrderById() {

	}

	private static void showAllProducts() {
		List<Product> products = context.getProductDao().getAllProducts();
		for (Product product : products) {
			System.out.println("|" + product.getName() + "|" + product.getPrice() + "|" + product.getStatus() + "|");

		}

	}

	private static void allOrderedProducts() {// по условиям задачи математика на SQL

	}

	private static void showOrderById() {
		System.out.println("write order ID");
		Scanner in = new Scanner(System.in);
		Order order = context.getOrderDao().showOrderById(in.nextInt());
		order.setProducts(context.getProductDao().getProductByOrder(order));
		for (Product product : order.getProducts()) {
			System.out.println("|" + order.getId() + "|" + (product.getPrice() * product.getQuantity()) + "|"
					+ product.getName() + "|" + product.getQuantity() + "|" + order.getCreatedAt() + "|");

		}

	}

	private static void showAllOrders() {
		ArrayList<Integer> allIdOfOrders = (ArrayList<Integer>) context.getOrderDao().showAllOrdersId();
		for (Integer id : allIdOfOrders) {
			Order order = context.getOrderDao().showOrderById(id);
			order.setProducts(context.getProductDao().getProductByOrder(order));
			for (Product product : order.getProducts()) {
				System.out.println("|" + order.getId() + "|" + (product.getPrice() * product.getQuantity()) + "|"
						+ product.getName() + "|" + product.getQuantity() + "|" + order.getCreatedAt() + "|");

			}

		}

	}

	private static void removeOrderById() {

	}

	private static void removeAllOrders() {

	}
}
