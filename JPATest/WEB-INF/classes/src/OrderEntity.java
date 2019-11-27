package sales.web.app;

import java.util.*;

public class OrderEntity implements java.io.Serializable{

	private int orderNo;
	private Date orderDate;
	private String customerId;
	private int productNo;
	private int quantity;

	public int getOrderNo(){return orderNo;}

	public Date getOrderDate(){return orderDate;}

	public String getCustomerId(){return customerId;}

	public int getProductNo(){return productNo;}

	public int getQuantity(){return quantity;}
}


