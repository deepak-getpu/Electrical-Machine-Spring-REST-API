package electrical.machine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import electrical.machine.entity.Machine;

public interface MachineDao extends JpaRepository<Machine, Long> {

}
