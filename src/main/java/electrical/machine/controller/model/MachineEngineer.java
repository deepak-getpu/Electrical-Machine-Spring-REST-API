package electrical.machine.controller.model;

import electrical.machine.entity.Engineer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MachineEngineer {

	private Long engineerId;
	private String engineerFirstName;
	private String engineerLastName;
	
	public MachineEngineer(Engineer engineer) {      
		engineerId = engineer.getEngineerId();
		engineerFirstName = engineer.getEngineerFirstName();
		engineerLastName = engineer.getEngineerLastName();
	}

}
