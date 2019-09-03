package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.EtfExecutionDtl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtfExecutionDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtfExecutionDtlRepository extends JpaRepository<EtfExecutionDtl, Long> {

}
