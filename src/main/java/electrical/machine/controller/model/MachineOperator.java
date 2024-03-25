package electrical.machine.controller.model;

import electrical.machine.entity.Operator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MachineOperator {
	
	private Long operatorId;                                 
	private String operatorFirstName; 
	private String operatorLastName;
		
	
	public MachineOperator(Operator operator) { 
		operatorId = operator.getOperatorId();
		operatorFirstName = operator.getOperatorFirstName();
		operatorLastName = operator.getOperatorLastName();
	}
		

}
