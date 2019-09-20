package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.SubscriptDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.Subscript}.
 */
public interface SubscriptService {

    /**
     * Save a subscript.
     *
     * @param subscriptDTO the entity to save.
     * @return the persisted entity.
     */
    SubscriptDTO save(SubscriptDTO subscriptDTO);

    /**
     * Get all the subscripts.
     *
     * @return the list of entities.
     */
    List<SubscriptDTO> findAll();


    /**
     * Get the "id" subscript.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubscriptDTO> findOne(Long id);

    /**
     * Delete the "id" subscript.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
