package id.tech.cakra.etf.repository;

import id.tech.cakra.etf.domain.DealerParticipant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DealerParticipant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DealerParticipantRepository extends JpaRepository<DealerParticipant, Long> {

}
