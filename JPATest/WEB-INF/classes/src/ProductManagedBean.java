package sales.web.app;

import java.util.*;
import javax.persistence.*;

@javax.inject.Named("productModel")
@javax.enterprise.context.SessionScoped
public class ProductManagedBean implements java.io.Serializable{

	@PersistenceContext
	private EntityManager em;

	@javax.annotation.Resource
	private javax.transaction.UserTransaction utx;

	public List<ProductEntity> getProducts(){
		TypedQuery<ProductEntity> query = em.createQuery("SELECT e FROM ProductEntity e WHERE e.stock >= ?1 ORDER BY e.productNo", ProductEntity.class);
		query.setParameter(1, 5);
		return query.getResultList();	
	}

	private ProductEntity selectedProduct;

	public ProductEntity getSelectedProduct(){return selectedProduct;}

	public String showOrders(ProductEntity product){
		selectedProduct = product;
		return "detail";
	}

	public String editProduct(ProductEntity product){
		selectedProduct = product;
		return "edit";
	}

	public String saveProduct() throws Exception{
		utx.begin();
		em.merge(selectedProduct);
		utx.commit();
		return "index?faces-redirect=true";
	}
}


