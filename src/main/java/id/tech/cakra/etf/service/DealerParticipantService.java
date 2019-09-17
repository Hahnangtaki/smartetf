package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.DealerParticipantDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.DealerParticipant}.
 */
public interface DealerParticipantService {

    /**
     * Save a dealerParticipant.
     *
     * @param dealerParticipantDTO the entity to save.
     * @return the persisted entity.
     */
    DealerParticipantDTO save(DealerParticipantDTO dealerParticipantDTO);

    /**
     * Get all the dealerParticipants.
     *
     * @return the list of entities.
     */
    List<DealerParticipantDTO> findAll();


    /**
     * Get the "id" dealerParticipant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DealerParticipantDTO> findOne(Long id);

    /**
     * Delete the "id" dealerParticipant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
