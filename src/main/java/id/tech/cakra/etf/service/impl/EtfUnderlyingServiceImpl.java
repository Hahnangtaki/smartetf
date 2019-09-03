package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfUnderlyingService;
import id.tech.cakra.etf.domain.EtfUnderlying;
import id.tech.cakra.etf.repository.EtfUnderlyingRepository;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDTO;
import id.tech.cakra.etf.service.mapper.EtfUnderlyingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfUnderlying}.
 */
@Service
@Transactional
public class EtfUnderlyingServiceImpl implements EtfUnderlyingService {

    private final Logger log = LoggerFactory.getLogger(EtfUnderlyingServiceImpl.class);

    private final EtfUnderlyingRepository etfUnderlyingRepository;

    private final EtfUnderlyingMapper etfUnderlyingMapper;

    public EtfUnderlyingServiceImpl(EtfUnderlyingRepository etfUnderlyingRepository, EtfUnderlyingMapper etfUnderlyingMapper) {
        this.etfUnderlyingRepository = etfUnderlyingRepository;
        this.etfUnderlyingMapper = etfUnderlyingMapper;
    }

    /**
     * Save a etfUnderlying.
     *
     * @param etfUnderlyingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfUnderlyingDTO save(EtfUnderlyingDTO etfUnderlyingDTO) {
        log.debug("Request to save EtfUnderlying : {}", etfUnderlyingDTO);
        EtfUnderlying etfUnderlying = etfUnderlyingMapper.toEntity(etfUnderlyingDTO);
        etfUnderlying = etfUnderlyingRepository.save(etfUnderlying);
        return etfUnderlyingMapper.toDto(etfUnderlying);
    }

    /**
     * Get all the etfUnderlyings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfUnderlyingDTO> findAll() {
        log.debug("Request to get all EtfUnderlyings");
        return etfUnderlyingRepository.findAll().stream()
            .map(etfUnderlyingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etfUnderlying by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfUnderlyingDTO> findOne(Long id) {
        log.debug("Request to get EtfUnderlying : {}", id);
        return etfUnderlyingRepository.findById(id)
            .map(etfUnderlyingMapper::toDto);
    }

    /**
     * Delete the etfUnderlying by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfUnderlying : {}", id);
        etfUnderlyingRepository.deleteById(id);
    }
}
