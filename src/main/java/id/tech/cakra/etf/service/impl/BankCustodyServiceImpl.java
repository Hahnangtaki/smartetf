package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.BankCustodyService;
import id.tech.cakra.etf.domain.BankCustody;
import id.tech.cakra.etf.repository.BankCustodyRepository;
import id.tech.cakra.etf.service.dto.BankCustodyDTO;
import id.tech.cakra.etf.service.mapper.BankCustodyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BankCustody}.
 */
@Service
@Transactional
public class BankCustodyServiceImpl implements BankCustodyService {

    private final Logger log = LoggerFactory.getLogger(BankCustodyServiceImpl.class);

    private final BankCustodyRepository bankCustodyRepository;

    private final BankCustodyMapper bankCustodyMapper;

    public BankCustodyServiceImpl(BankCustodyRepository bankCustodyRepository, BankCustodyMapper bankCustodyMapper) {
        this.bankCustodyRepository = bankCustodyRepository;
        this.bankCustodyMapper = bankCustodyMapper;
    }

    /**
     * Save a bankCustody.
     *
     * @param bankCustodyDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BankCustodyDTO save(BankCustodyDTO bankCustodyDTO) {
        log.debug("Request to save BankCustody : {}", bankCustodyDTO);
        BankCustody bankCustody = bankCustodyMapper.toEntity(bankCustodyDTO);
        bankCustody = bankCustodyRepository.save(bankCustody);
        return bankCustodyMapper.toDto(bankCustody);
    }

    /**
     * Get all the bankCustodies.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankCustodyDTO> findAll() {
        log.debug("Request to get all BankCustodies");
        return bankCustodyRepository.findAll().stream()
            .map(bankCustodyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one bankCustody by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankCustodyDTO> findOne(Long id) {
        log.debug("Request to get BankCustody : {}", id);
        return bankCustodyRepository.findById(id)
            .map(bankCustodyMapper::toDto);
    }

    /**
     * Delete the bankCustody by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankCustody : {}", id);
        bankCustodyRepository.deleteById(id);
    }
}
