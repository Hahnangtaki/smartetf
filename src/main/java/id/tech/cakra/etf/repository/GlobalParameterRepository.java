package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.GlobalParameter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GlobalParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GlobalParameterRepository extends JpaRepository<GlobalParameter, Long> {

}
