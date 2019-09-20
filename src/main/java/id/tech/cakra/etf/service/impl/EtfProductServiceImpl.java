package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.EtfProductService;
import id.tech.cakra.etf.domain.EtfProduct;
import id.tech.cakra.etf.repository.EtfProductRepository;
import id.tech.cakra.etf.service.dto.EtfProductDTO;
import id.tech.cakra.etf.service.mapper.EtfProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtfProduct}.
 */
@Service
@Transactional
public class EtfProductServiceImpl implements EtfProductService {

    private final Logger log = LoggerFactory.getLogger(EtfProductServiceImpl.class);

    private final EtfProductRepository etfProductRepository;

    private final EtfProductMapper etfProductMapper;

    public EtfProductServiceImpl(EtfProductRepository etfProductRepository, EtfProductMapper etfProductMapper) {
        this.etfProductRepository = etfProductRepository;
        this.etfProductMapper = etfProductMapper;
    }

    /**
     * Save a etfProduct.
     *
     * @param etfProductDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EtfProductDTO save(EtfProductDTO etfProductDTO) {
        log.debug("Request to save EtfProduct : {}", etfProductDTO);
        EtfProduct etfProduct = etfProductMapper.toEntity(etfProductDTO);
        etfProduct = etfProductRepository.save(etfProduct);
        return etfProductMapper.toDto(etfProduct);
    }

    /**
     * Get all the etfProducts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EtfProductDTO> findAll() {
        log.debug("Request to get all EtfProducts");
        return etfProductRepository.findAllWithEagerRelationships().stream()
            .map(etfProductMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the etfProducts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EtfProductDTO> findAllWithEagerRelationships(Pageable pageable) {
        return etfProductRepository.findAllWithEagerRelationships(pageable).map(etfProductMapper::toDto);
    }
    

    /**
     * Get one etfProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtfProductDTO> findOne(Long id) {
        log.debug("Request to get EtfProduct : {}", id);
        return etfProductRepository.findOneWithEagerRelationships(id)
            .map(etfProductMapper::toDto);
    }

    /**
     * Delete the etfProduct by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EtfProduct : {}", id);
        etfProductRepository.deleteById(id);
    }
}
