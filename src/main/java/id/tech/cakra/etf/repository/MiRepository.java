package id.tech.cakra.etf.repository;
import id.tech.cakra.etf.domain.Mi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Mi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MiRepository extends JpaRepository<Mi, Long> {

}
