package electrical.machine.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Operator {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long operatorId;                                 //machine Id not included
	private String operatorFirstName; 
	private String operatorLastName;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	private Machine machine;						// Many to one 
		

}
