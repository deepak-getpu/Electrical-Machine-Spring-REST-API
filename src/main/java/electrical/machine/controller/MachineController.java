package electrical.machine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import electrical.machine.controller.model.MachineData;
import electrical.machine.controller.model.MachineEngineer;
import electrical.machine.controller.model.MachineOperator;
import electrical.machine.service.MachineService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/electrical-machine")
@Slf4j
public class MachineController {

	@Autowired
	private MachineService machineService;
	
	// Create Machine
	
	@PostMapping("/machine")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MachineData updateMachine(@RequestBody MachineData machineData) {
		log.info("Creating machine {}", machineData);
		return machineService.saveMachine(machineData);	
	}
	
	// Update Machine 
	
	@PutMapping("/{machineId}")
	public MachineData modifyMachine(@PathVariable Long machineId, @RequestBody MachineData machineData) {
		machineData.setMachineId(machineId);
		log.info("Updating a machine {}", machineData);
		return machineService.saveMachine(machineData);
	}
	
	// Retrive Machines
	
	@GetMapping("/machine")
	public List<MachineData> retriveAllMachines() {
		log.info("Retriving all machines.");
		return machineService.retriveAllMachines();
	}
	
	@GetMapping("/{machineId}")
	public MachineData retriveMachineById(@PathVariable Long machineId) {
		log.info("Finding machine by ID={}", machineId);
		return machineService.retriveMachineById(machineId);	
	}
	
	// Delete Machine
	
	@DeleteMapping("/{machineId}")
	public Map<String, String> deleteMachineById(@PathVariable Long machineId) {
		log.info("Deleting machine with ID={}" + machineId);
		machineService.deleteMachineById(machineId);
		
		return Map.of("message", "Machine with ID=" + machineId + " deleted.");
	}
	

	// Create Operator for Machine
	
	@PostMapping("/{machineId}/operator")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MachineOperator addOperatorToMachine (@PathVariable Long machineId, @RequestBody MachineOperator machineOperator) {
		log.info("Adding operator {} to machine with ID={}", machineOperator, machineId);
		return machineService.saveOperator(machineId, machineOperator);
	}
	
	// Create Engineer for Machine
	
	@PostMapping("/{machineId}/engineer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MachineEngineer addEngineerToMachine (@PathVariable Long machineId, @RequestBody MachineEngineer machineEngineer) {
		log.info("Adding engineer {} to machine with ID={}", machineEngineer, machineId);
		return machineService.saveEngineer(machineId, machineEngineer);
	}
	
	
}



