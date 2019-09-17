package id.tech.cakra.etf.service;

import id.tech.cakra.etf.service.dto.BankCustodyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.etf.domain.BankCustody}.
 */
public interface BankCustodyService {

    /**
     * Save a bankCustody.
     *
     * @param bankCustodyDTO the entity to save.
     * @return the persisted entity.
     */
    BankCustodyDTO save(BankCustodyDTO bankCustodyDTO);

    /**
     * Get all the bankCustodies.
     *
     * @return the list of entities.
     */
    List<BankCustodyDTO> findAll();


    /**
     * Get the "id" bankCustody.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankCustodyDTO> findOne(Long id);

    /**
     * Delete the "id" bankCustody.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
