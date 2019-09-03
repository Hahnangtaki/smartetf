package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.RedemptionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.Redemption}.
 */
public interface RedemptionService {

    /**
     * Save a redemption.
     *
     * @param redemptionDTO the entity to save.
     * @return the persisted entity.
     */
    RedemptionDTO save(RedemptionDTO redemptionDTO);

    /**
     * Get all the redemptions.
     *
     * @return the list of entities.
     */
    List<RedemptionDTO> findAll();


    /**
     * Get the "id" redemption.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RedemptionDTO> findOne(Long id);

    /**
     * Delete the "id" redemption.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
