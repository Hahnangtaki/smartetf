package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.EtfUnderlyingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.EtfUnderlying}.
 */
public interface EtfUnderlyingService {

    /**
     * Save a etfUnderlying.
     *
     * @param etfUnderlyingDTO the entity to save.
     * @return the persisted entity.
     */
    EtfUnderlyingDTO save(EtfUnderlyingDTO etfUnderlyingDTO);

    /**
     * Get all the etfUnderlyings.
     *
     * @return the list of entities.
     */
    List<EtfUnderlyingDTO> findAll();


    /**
     * Get the "id" etfUnderlying.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtfUnderlyingDTO> findOne(Long id);

    /**
     * Delete the "id" etfUnderlying.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
