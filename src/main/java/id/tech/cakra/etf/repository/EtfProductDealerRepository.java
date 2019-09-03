package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.EtfProductDealer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtfProductDealer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtfProductDealerRepository extends JpaRepository<EtfProductDealer, Long> {

}
