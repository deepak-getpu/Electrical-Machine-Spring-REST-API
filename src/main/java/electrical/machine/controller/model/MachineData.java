package electrical.machine.controller.model;

import java.util.HashSet;
import java.util.Set;

import electrical.machine.entity.Engineer;
import electrical.machine.entity.Machine;
import electrical.machine.entity.Operator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MachineData {
	private Long machineId;
	private Long roomNumber;
	private Long machinePower;
	private Long runTime;
	private Long downTime;

	private Set<MachineOperator> operators = new HashSet<>();                    				
	private Set<MachineEngineer> engineers = new HashSet<>(); 

	public MachineData (Machine machine) {
		machineId = machine.getMachineId();
		roomNumber = machine.getRoomNumber();
		machinePower = machine.getMachinePower();
		runTime = machine.getRunTime();
		downTime = machine.getDownTime();
	
		for(Engineer engineer : machine.getEngineers()) {
			engineers.add(new MachineEngineer(engineer)); 									
		}
	
		for(Operator operator : machine.getOperators()) {
			operators.add(new MachineOperator(operator));
		}
		
	}
	
	

}

