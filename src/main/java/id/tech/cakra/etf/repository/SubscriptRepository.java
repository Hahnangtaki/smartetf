package id.tech.cakra.etf.repository;
import id.tech.cakra.etf.domain.Subscript;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Subscript entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubscriptRepository extends JpaRepository<Subscript, Long> {

}
