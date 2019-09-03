package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.EtfProductDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.EtfProduct}.
 */
public interface EtfProductService {

    /**
     * Save a etfProduct.
     *
     * @param etfProductDTO the entity to save.
     * @return the persisted entity.
     */
    EtfProductDTO save(EtfProductDTO etfProductDTO);

    /**
     * Get all the etfProducts.
     *
     * @return the list of entities.
     */
    List<EtfProductDTO> findAll();


    /**
     * Get the "id" etfProduct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtfProductDTO> findOne(Long id);

    /**
     * Delete the "id" etfProduct.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
