package sales.domain.services;

import sales.domain.entities.*;
import javax.ejb.*;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@LocalBean
@Stateless
@Path("/order")
public class OrderManagerEJB{

	@PersistenceContext
	private EntityManager em;

	@GET
	public long test(){
		return System.currentTimeMillis();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderOutput createOrder(OrderInput input){
		try{
			CounterEntity ctr = em.find(CounterEntity.class, "orders");
			int orderNo = ctr.getNextValue() + 1000;
			OrderEntity ord = new OrderEntity(orderNo, input.customerId, input.productNo, input.quantity);
			em.persist(ord);
			em.persist(ctr);
			OrderOutput result = new OrderOutput();
			result.orderNo = orderNo;
			return result;
		}catch(Exception e){
			throw new WebApplicationException(e, 500);
		}
	}

}

