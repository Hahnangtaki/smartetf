package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.MiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.Mi}.
 */
public interface MiService {

    /**
     * Save a mi.
     *
     * @param miDTO the entity to save.
     * @return the persisted entity.
     */
    MiDTO save(MiDTO miDTO);

    /**
     * Get all the mis.
     *
     * @return the list of entities.
     */
    List<MiDTO> findAll();


    /**
     * Get the "id" mi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MiDTO> findOne(Long id);

    /**
     * Delete the "id" mi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
