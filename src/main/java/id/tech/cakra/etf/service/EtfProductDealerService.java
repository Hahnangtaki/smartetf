package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.EtfProductDealerDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.EtfProductDealer}.
 */
public interface EtfProductDealerService {

    /**
     * Save a etfProductDealer.
     *
     * @param etfProductDealerDTO the entity to save.
     * @return the persisted entity.
     */
    EtfProductDealerDTO save(EtfProductDealerDTO etfProductDealerDTO);

    /**
     * Get all the etfProductDealers.
     *
     * @return the list of entities.
     */
    List<EtfProductDealerDTO> findAll();


    /**
     * Get the "id" etfProductDealer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtfProductDealerDTO> findOne(Long id);

    /**
     * Delete the "id" etfProductDealer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
