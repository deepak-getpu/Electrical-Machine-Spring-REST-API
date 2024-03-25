package electrical.machine.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import electrical.machine.controller.model.MachineData;
import electrical.machine.controller.model.MachineEngineer;
import electrical.machine.controller.model.MachineOperator;
import electrical.machine.dao.EngineerDao;
import electrical.machine.dao.MachineDao;
import electrical.machine.dao.OperatorDao;
import electrical.machine.entity.Engineer;
import electrical.machine.entity.Machine;
import electrical.machine.entity.Operator;

@Service
public class MachineService {
	
	@Autowired
	private MachineDao machineDao;
	
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private EngineerDao engineerDao;

// Create record to machine table: 
	
	@Transactional(readOnly = false)
	public MachineData saveMachine(MachineData machineData) {
		Long machineId = machineData.getMachineId();
		Machine machine = findOrCreateMachine(machineId);
		
		copyMachineFields(machine, machineData);
		return new MachineData(machineDao.save(machine));
	}

	
	private Machine findOrCreateMachine(Long machineId) {
		if(Objects.isNull(machineId)) {
			return new Machine();
		}
		else {
			return findMachineById(machineId);
		}	
	}
	
	private Machine findMachineById(Long machineId) {
		return machineDao.findById(machineId)
				.orElseThrow( () -> new NoSuchElementException
						("Machine with ID=" + machineId + " was not found"));
	}


	private void copyMachineFields(Machine machine, MachineData machineData) {
		machine.setMachineId(machineData.getMachineId());
		machine.setRoomNumber(machineData.getRoomNumber());
		machine.setMachinePower(machineData.getMachinePower());
		machine.setRunTime(machineData.getRunTime());
		machine.setDownTime(machineData.getDownTime());
	}

// Retrive records from Machine Table: 
	
	@Transactional 
	public List<MachineData> retriveAllMachines() {
		List<Machine> machines = machineDao.findAll();
		List<MachineData> result = new LinkedList<>();
		
		for (Machine machine : machines) { 
			MachineData machineData = new MachineData(machine);
			machineData.getOperators().clear();
			machineData.getEngineers().clear();
			result.add(machineData);
		}
		return result;
	}
	
	
	public MachineData retriveMachineById(Long machineId) {
		return new MachineData(findMachineById(machineId));
		
	}
	
// Delete record in Machine table: 
	
	@Transactional(readOnly = false)
	public void deleteMachineById(Long machineId) {
		Machine machine = findMachineById(machineId);
		machineDao.delete(machine);	
	}
	
	
	
// Create record to Operator table: 
	
	@Transactional(readOnly = false)
	public MachineOperator saveOperator(Long machineId, MachineOperator machineOperator) {
		Machine machine = findMachineById(machineId);
		Long OperatorId = machineOperator.getOperatorId();
		
		Operator operator = findOrCreateOperator(machineId, OperatorId);
		copyOperatorFields(operator, machineOperator);
		
		operator.setMachine(machine);
		machine.getOperators().add(operator);
		
		Operator dbOperator = operatorDao.save(operator);
		
		return new MachineOperator(dbOperator);

	}

	private Operator findOrCreateOperator(Long machineId, Long operatorId) {
		if(Objects.isNull(operatorId)) {
			return new Operator();
		}
		
		return findOperatorById(machineId, operatorId);
	}

	private Operator findOperatorById(Long machineId, Long operatorId) {
		Operator operator = operatorDao.findById(operatorId)
				.orElseThrow( () -> new NoSuchElementException(
						"Operator with ID=" + operatorId + " was not found."));
		
		if(operator.getMachine().getMachineId() != machineId) {
			throw  new IllegalArgumentException("The operator with ID=" 
		      + operatorId + " is not employed for machine with ID=" + ".");	
		}
		return operator;
	}
	
	private void copyOperatorFields(Operator operator, MachineOperator machineOperator) {
		operator.setOperatorId(machineOperator.getOperatorId());
		operator.setOperatorFirstName(machineOperator.getOperatorFirstName());
		operator.setOperatorLastName(machineOperator.getOperatorLastName());
		
	}


	// Create record to Engineer Table:
	
	@Transactional(readOnly = false)
	public MachineEngineer saveEngineer(Long machineId, MachineEngineer machineEngineer) {
		Machine machine = findMachineById(machineId);
		Long engineerId = machineEngineer.getEngineerId();
		Engineer engineer = findOrCreateEngineer(machineId, engineerId);
		
		copyEngineerFields(engineer, machineEngineer);
		
		engineer.getMachines().add(machine);
		machine.getEngineers().add(engineer);
		
		Engineer dbEngineer = engineerDao.save(engineer);
		
		return new MachineEngineer(dbEngineer);		
	}

	private Engineer findOrCreateEngineer(Long machineId, Long engineerId) {
		if(Objects.isNull(engineerId)) {
			return new Engineer();
		}
		return findEngineerById(machineId, engineerId);
	}

	private Engineer findEngineerById(Long machineId, Long engineerId) {
		Engineer engineer = engineerDao.findById(engineerId)
				.orElseThrow( () -> new NoSuchElementException
						("Engineer with ID=" + engineerId + " was not found."));
		
		boolean found = false;
		
		for (Machine machine : engineer.getMachines()) {
			if(machine.getMachineId() == machineId) {
				found = true;
				break;
			}
		}
		
		if(! found) { 
			throw new IllegalArgumentException("The engineer with ID="
					+ engineerId + "is not associated with machine with ID=" + machineId);
		}

		return engineer;
	}
	
	private void copyEngineerFields(Engineer engineer, MachineEngineer machineEngineer) {
		engineer.setEngineerId(machineEngineer.getEngineerId());
		engineer.setEngineerFirstName(machineEngineer.getEngineerFirstName());
		engineer.setEngineerLastName(machineEngineer.getEngineerLastName());
		
	}

}





