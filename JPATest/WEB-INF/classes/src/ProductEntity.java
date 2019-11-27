package sales.web.app;

import java.util.*;
import javax.validation.constraints.*;

public class ProductEntity implements java.io.Serializable{

	private int productNo;

	@NotNull
	private double price;

	@NotNull
	@Min(5)
	private int stock;

	private List<OrderEntity> orders;

	public int getProductNo(){return productNo;}

	public double getPrice(){return price;}
	public void setPrice(double value){price = value;}

	public int getStock(){return stock;}
	public void setStock(int value){stock = value;}

	public List<OrderEntity> getOrders(){return orders;}
}


