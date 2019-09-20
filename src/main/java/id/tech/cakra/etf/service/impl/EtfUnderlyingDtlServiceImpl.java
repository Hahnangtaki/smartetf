package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfUnderlyingDtlService;
import id.tech.cakra.etf.domain.EtfUnderlyingDtl;
import id.tech.cakra.etf.repository.EtfUnderlyingDtlRepository;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDtlDTO;
import id.tech.cakra.etf.service.mapper.EtfUnderlyingDtlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfUnderlyingDtl}.
 */
@Service
@Transactional
public class EtfUnderlyingDtlServiceImpl implements EtfUnderlyingDtlService {

    private final Logger log = LoggerFactory.getLogger(EtfUnderlyingDtlServiceImpl.class);

    private final EtfUnderlyingDtlRepository etfUnderlyingDtlRepository;

    private final EtfUnderlyingDtlMapper etfUnderlyingDtlMapper;

    public EtfUnderlyingDtlServiceImpl(EtfUnderlyingDtlRepository etfUnderlyingDtlRepository, EtfUnderlyingDtlMapper etfUnderlyingDtlMapper) {
        this.etfUnderlyingDtlRepository = etfUnderlyingDtlRepository;
        this.etfUnderlyingDtlMapper = etfUnderlyingDtlMapper;
    }

    /**
     * Save a etfUnderlyingDtl.
     *
     * @param etfUnderlyingDtlDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfUnderlyingDtlDTO save(EtfUnderlyingDtlDTO etfUnderlyingDtlDTO) {
        log.debug("Request to save EtfUnderlyingDtl : {}", etfUnderlyingDtlDTO);
        EtfUnderlyingDtl etfUnderlyingDtl = etfUnderlyingDtlMapper.toEntity(etfUnderlyingDtlDTO);
        etfUnderlyingDtl = etfUnderlyingDtlRepository.save(etfUnderlyingDtl);
        return etfUnderlyingDtlMapper.toDto(etfUnderlyingDtl);
    }

    /**
     * Get all the etfUnderlyingDtls.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfUnderlyingDtlDTO> findAll() {
        log.debug("Request to get all EtfUnderlyingDtls");
        return etfUnderlyingDtlRepository.findAll().stream()
            .map(etfUnderlyingDtlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etfUnderlyingDtl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfUnderlyingDtlDTO> findOne(Long id) {
        log.debug("Request to get EtfUnderlyingDtl : {}", id);
        return etfUnderlyingDtlRepository.findById(id)
            .map(etfUnderlyingDtlMapper::toDto);
    }

    /**
     * Delete the etfUnderlyingDtl by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfUnderlyingDtl : {}", id);
        etfUnderlyingDtlRepository.deleteById(id);
    }
}
