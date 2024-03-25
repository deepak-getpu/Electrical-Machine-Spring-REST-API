package electrical.machine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import electrical.machine.entity.Operator;

public interface OperatorDao extends JpaRepository<Operator, Long> {

}
