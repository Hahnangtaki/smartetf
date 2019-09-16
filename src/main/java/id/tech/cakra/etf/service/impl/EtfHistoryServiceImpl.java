package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfHistoryService;
import id.tech.cakra.etf.domain.EtfHistory;
import id.tech.cakra.etf.repository.EtfHistoryRepository;
import id.tech.cakra.etf.service.dto.EtfHistoryDTO;
import id.tech.cakra.etf.service.mapper.EtfHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfHistory}.
 */
@Service
@Transactional
public class EtfHistoryServiceImpl implements EtfHistoryService {

    private final Logger log = LoggerFactory.getLogger(EtfHistoryServiceImpl.class);

    private final EtfHistoryRepository etfHistoryRepository;

    private final EtfHistoryMapper etfHistoryMapper;

    public EtfHistoryServiceImpl(EtfHistoryRepository etfHistoryRepository, EtfHistoryMapper etfHistoryMapper) {
        this.etfHistoryRepository = etfHistoryRepository;
        this.etfHistoryMapper = etfHistoryMapper;
    }

    /**
     * Save a etfHistory.
     *
     * @param etfHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfHistoryDTO save(EtfHistoryDTO etfHistoryDTO) {
        log.debug("Request to save EtfHistory : {}", etfHistoryDTO);
        EtfHistory etfHistory = etfHistoryMapper.toEntity(etfHistoryDTO);
        etfHistory = etfHistoryRepository.save(etfHistory);
        return etfHistoryMapper.toDto(etfHistory);
    }

    /**
     * Get all the etfHistories.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfHistoryDTO> findAll() {
        log.debug("Request to get all EtfHistories");
        return etfHistoryRepository.findAll().stream()
            .map(etfHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etfHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfHistoryDTO> findOne(Long id) {
        log.debug("Request to get EtfHistory : {}", id);
        return etfHistoryRepository.findById(id)
            .map(etfHistoryMapper::toDto);
    }

    /**
     * Delete the etfHistory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfHistory : {}", id);
        etfHistoryRepository.deleteById(id);
    }
}
