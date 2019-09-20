package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfExecutionDtlService;
import id.tech.cakra.etf.domain.EtfExecutionDtl;
import id.tech.cakra.etf.repository.EtfExecutionDtlRepository;
import id.tech.cakra.etf.service.dto.EtfExecutionDtlDTO;
import id.tech.cakra.etf.service.mapper.EtfExecutionDtlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfExecutionDtl}.
 */
@Service
@Transactional
public class EtfExecutionDtlServiceImpl implements EtfExecutionDtlService {

    private final Logger log = LoggerFactory.getLogger(EtfExecutionDtlServiceImpl.class);

    private final EtfExecutionDtlRepository etfExecutionDtlRepository;

    private final EtfExecutionDtlMapper etfExecutionDtlMapper;

    public EtfExecutionDtlServiceImpl(EtfExecutionDtlRepository etfExecutionDtlRepository, EtfExecutionDtlMapper etfExecutionDtlMapper) {
        this.etfExecutionDtlRepository = etfExecutionDtlRepository;
        this.etfExecutionDtlMapper = etfExecutionDtlMapper;
    }

    /**
     * Save a etfExecutionDtl.
     *
     * @param etfExecutionDtlDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfExecutionDtlDTO save(EtfExecutionDtlDTO etfExecutionDtlDTO) {
        log.debug("Request to save EtfExecutionDtl : {}", etfExecutionDtlDTO);
        EtfExecutionDtl etfExecutionDtl = etfExecutionDtlMapper.toEntity(etfExecutionDtlDTO);
        etfExecutionDtl = etfExecutionDtlRepository.save(etfExecutionDtl);
        return etfExecutionDtlMapper.toDto(etfExecutionDtl);
    }

    /**
     * Get all the etfExecutionDtls.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfExecutionDtlDTO> findAll() {
        log.debug("Request to get all EtfExecutionDtls");
        return etfExecutionDtlRepository.findAll().stream()
            .map(etfExecutionDtlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etfExecutionDtl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfExecutionDtlDTO> findOne(Long id) {
        log.debug("Request to get EtfExecutionDtl : {}", id);
        return etfExecutionDtlRepository.findById(id)
            .map(etfExecutionDtlMapper::toDto);
    }

    /**
     * Delete the etfExecutionDtl by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfExecutionDtl : {}", id);
        etfExecutionDtlRepository.deleteById(id);
    }
}
