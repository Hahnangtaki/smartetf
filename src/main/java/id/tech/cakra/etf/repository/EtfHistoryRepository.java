package id.tech.cakra.etf.repository;
import id.tech.cakra.etf.domain.EtfHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EtfHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtfHistoryRepository extends JpaRepository<EtfHistory, Long> {

}
