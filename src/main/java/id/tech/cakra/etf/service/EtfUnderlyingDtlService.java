package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.EtfUnderlyingDtlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.EtfUnderlyingDtl}.
 */
public interface EtfUnderlyingDtlService {

    /**
     * Save a etfUnderlyingDtl.
     *
     * @param etfUnderlyingDtlDTO the entity to save.
     * @return the persisted entity.
     */
    EtfUnderlyingDtlDTO save(EtfUnderlyingDtlDTO etfUnderlyingDtlDTO);

    /**
     * Get all the etfUnderlyingDtls.
     *
     * @return the list of entities.
     */
    List<EtfUnderlyingDtlDTO> findAll();


    /**
     * Get the "id" etfUnderlyingDtl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtfUnderlyingDtlDTO> findOne(Long id);

    /**
     * Delete the "id" etfUnderlyingDtl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
