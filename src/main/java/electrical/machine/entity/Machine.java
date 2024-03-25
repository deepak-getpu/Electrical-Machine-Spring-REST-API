package electrical.machine.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Machine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long machineId;
	private Long roomNumber;
	private Long machinePower;
	private Long runTime;
	private Long downTime;
	
	
	@OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Operator> operators = new HashSet<>();                    					// one to many
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "machine_engineer", 
	joinColumns = @JoinColumn(name = "machine_id"), 
	inverseJoinColumns = @JoinColumn(name = "engineer_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Engineer> engineers = new HashSet<>();                    					// many to many

}
