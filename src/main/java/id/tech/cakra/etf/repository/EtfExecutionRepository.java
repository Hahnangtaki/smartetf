package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.EtfExecution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtfExecution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtfExecutionRepository extends JpaRepository<EtfExecution, Long> {

}
