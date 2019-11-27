package sales.domain.entities;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="orders")
public class OrderEntity implements java.io.Serializable{

	@Id
	@Column(name="ord_no")
	private int orderNo;

	@Column(name="ord_date")
	private Date orderDate;

	@Column(name="cust_id")
	private String customerId;

	@Column(name="pno")
	private int productNo;

	@Column(name="qty")
	private int quantity;

	public OrderEntity(){}

	public OrderEntity(int orderNo, String customerId, int productNo, int quantity){
		this.orderNo = orderNo;
		this.orderDate = new Date();
		this.customerId = customerId;
		this.productNo = productNo;
		this.quantity = quantity;
	}
}

