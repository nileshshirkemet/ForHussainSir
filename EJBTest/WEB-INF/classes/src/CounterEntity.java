package sales.domain.entities;

import javax.persistence.*;

@Entity
@Table(name="counters")
@Cacheable(false)
public class CounterEntity implements java.io.Serializable{

	@Id
	@Column(name="ctr_name")
	private String name;

	@Column(name="cur_val")
	private int currentValue;

	public int getNextValue(){
		return ++currentValue;
	}
}


