package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.EtfExecutionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.EtfExecution}.
 */
public interface EtfExecutionService {

    /**
     * Save a etfExecution.
     *
     * @param etfExecutionDTO the entity to save.
     * @return the persisted entity.
     */
    EtfExecutionDTO save(EtfExecutionDTO etfExecutionDTO);

    /**
     * Get all the etfExecutions.
     *
     * @return the list of entities.
     */
    List<EtfExecutionDTO> findAll();


    /**
     * Get the "id" etfExecution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtfExecutionDTO> findOne(Long id);

    /**
     * Delete the "id" etfExecution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
