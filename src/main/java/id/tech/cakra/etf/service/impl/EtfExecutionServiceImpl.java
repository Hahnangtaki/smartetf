package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfExecutionService;
import id.tech.cakra.etf.domain.EtfExecution;
import id.tech.cakra.etf.repository.EtfExecutionRepository;
import id.tech.cakra.etf.service.dto.EtfExecutionDTO;
import id.tech.cakra.etf.service.mapper.EtfExecutionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfExecution}.
 */
@Service
@Transactional
public class EtfExecutionServiceImpl implements EtfExecutionService {

    private final Logger log = LoggerFactory.getLogger(EtfExecutionServiceImpl.class);

    private final EtfExecutionRepository etfExecutionRepository;

    private final EtfExecutionMapper etfExecutionMapper;

    public EtfExecutionServiceImpl(EtfExecutionRepository etfExecutionRepository, EtfExecutionMapper etfExecutionMapper) {
        this.etfExecutionRepository = etfExecutionRepository;
        this.etfExecutionMapper = etfExecutionMapper;
    }

    /**
     * Save a etfExecution.
     *
     * @param etfExecutionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfExecutionDTO save(EtfExecutionDTO etfExecutionDTO) {
        log.debug("Request to save EtfExecution : {}", etfExecutionDTO);
        EtfExecution etfExecution = etfExecutionMapper.toEntity(etfExecutionDTO);
        etfExecution = etfExecutionRepository.save(etfExecution);
        return etfExecutionMapper.toDto(etfExecution);
    }

    /**
     * Get all the etfExecutions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfExecutionDTO> findAll() {
        log.debug("Request to get all EtfExecutions");
        return etfExecutionRepository.findAll().stream()
            .map(etfExecutionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etfExecution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfExecutionDTO> findOne(Long id) {
        log.debug("Request to get EtfExecution : {}", id);
        return etfExecutionRepository.findById(id)
            .map(etfExecutionMapper::toDto);
    }

    /**
     * Delete the etfExecution by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfExecution : {}", id);
        etfExecutionRepository.deleteById(id);
    }
}
