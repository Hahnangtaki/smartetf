package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.EtfProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtfProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtfProductRepository extends JpaRepository<EtfProduct, Long> {

}
