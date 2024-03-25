package electrical.machine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import electrical.machine.entity.Engineer;

public interface EngineerDao extends JpaRepository<Engineer, Long> {

}
