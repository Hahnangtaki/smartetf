package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.EtfUnderlying;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtfUnderlying entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtfUnderlyingRepository extends JpaRepository<EtfUnderlying, Long> {

}
