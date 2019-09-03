package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfProductDealerService;
import id.tech.cakra.etf.domain.EtfProductDealer;
import id.tech.cakra.etf.repository.EtfProductDealerRepository;
import id.tech.cakra.etf.service.dto.EtfProductDealerDTO;
import id.tech.cakra.etf.service.mapper.EtfProductDealerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfProductDealer}.
 */
@Service
@Transactional
public class EtfProductDealerServiceImpl implements EtfProductDealerService {

    private final Logger log = LoggerFactory.getLogger(EtfProductDealerServiceImpl.class);

    private final EtfProductDealerRepository etfProductDealerRepository;

    private final EtfProductDealerMapper etfProductDealerMapper;

    public EtfProductDealerServiceImpl(EtfProductDealerRepository etfProductDealerRepository, EtfProductDealerMapper etfProductDealerMapper) {
        this.etfProductDealerRepository = etfProductDealerRepository;
        this.etfProductDealerMapper = etfProductDealerMapper;
    }

    /**
     * Save a etfProductDealer.
     *
     * @param etfProductDealerDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfProductDealerDTO save(EtfProductDealerDTO etfProductDealerDTO) {
        log.debug("Request to save EtfProductDealer : {}", etfProductDealerDTO);
        EtfProductDealer etfProductDealer = etfProductDealerMapper.toEntity(etfProductDealerDTO);
        etfProductDealer = etfProductDealerRepository.save(etfProductDealer);
        return etfProductDealerMapper.toDto(etfProductDealer);
    }

    /**
     * Get all the etfProductDealers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfProductDealerDTO> findAll() {
        log.debug("Request to get all EtfProductDealers");
        return etfProductDealerRepository.findAll().stream()
            .map(etfProductDealerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one etfProductDealer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfProductDealerDTO> findOne(Long id) {
        log.debug("Request to get EtfProductDealer : {}", id);
        return etfProductDealerRepository.findById(id)
            .map(etfProductDealerMapper::toDto);
    }

    /**
     * Delete the etfProductDealer by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfProductDealer : {}", id);
        etfProductDealerRepository.deleteById(id);
    }
}
