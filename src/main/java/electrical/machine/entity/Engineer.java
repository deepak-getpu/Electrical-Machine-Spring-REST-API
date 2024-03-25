package electrical.machine.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Engineer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long engineerId;
	private String engineerFirstName;
	private String engineerLastName;
	

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "engineers", cascade = CascadeType.PERSIST)
	private Set<Machine> machines = new HashSet<>();                             // many to many 

}
