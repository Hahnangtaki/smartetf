package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.EtfExecutionDtlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.EtfExecutionDtl}.
 */
public interface EtfExecutionDtlService {

    /**
     * Save a etfExecutionDtl.
     *
     * @param etfExecutionDtlDTO the entity to save.
     * @return the persisted entity.
     */
    EtfExecutionDtlDTO save(EtfExecutionDtlDTO etfExecutionDtlDTO);

    /**
     * Get all the etfExecutionDtls.
     *
     * @return the list of entities.
     */
    List<EtfExecutionDtlDTO> findAll();


    /**
     * Get the "id" etfExecutionDtl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtfExecutionDtlDTO> findOne(Long id);

    /**
     * Delete the "id" etfExecutionDtl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
