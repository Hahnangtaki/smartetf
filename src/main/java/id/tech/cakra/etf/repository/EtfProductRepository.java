package id.tech.cakra.etf.repository;
import id.tech.cakra.etf.domain.EtfProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EtfProduct entity.
 */
@Repository
public interface EtfProductRepository extends JpaRepository<EtfProduct, Long> {

    @Query(value = "select distinct etfProduct from EtfProduct etfProduct left join fetch etfProduct.dealerParticipants",
        countQuery = "select count(distinct etfProduct) from EtfProduct etfProduct")
    Page<EtfProduct> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct etfProduct from EtfProduct etfProduct left join fetch etfProduct.dealerParticipants")
    List<EtfProduct> findAllWithEagerRelationships();

    @Query("select etfProduct from EtfProduct etfProduct left join fetch etfProduct.dealerParticipants where etfProduct.id =:id")
    Optional<EtfProduct> findOneWithEagerRelationships(@Param("id") Long id);

}
