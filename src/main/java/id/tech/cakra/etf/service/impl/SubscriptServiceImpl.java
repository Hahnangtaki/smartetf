package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.SubscriptService;
import id.tech.cakra.etf.domain.Subscript;
import id.tech.cakra.etf.repository.SubscriptRepository;
import id.tech.cakra.etf.service.dto.SubscriptDTO;
import id.tech.cakra.etf.service.mapper.SubscriptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Subscript}.
 */
@Service
@Transactional
public class SubscriptServiceImpl implements SubscriptService {

    private final Logger log = LoggerFactory.getLogger(SubscriptServiceImpl.class);

    private final SubscriptRepository subscriptRepository;

    private final SubscriptMapper subscriptMapper;

    public SubscriptServiceImpl(SubscriptRepository subscriptRepository, SubscriptMapper subscriptMapper) {
        this.subscriptRepository = subscriptRepository;
        this.subscriptMapper = subscriptMapper;
    }

    /**
     * Save a subscript.
     *
     * @param subscriptDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SubscriptDTO save(SubscriptDTO subscriptDTO) {
        log.debug("Request to save Subscript : {}", subscriptDTO);
        Subscript subscript = subscriptMapper.toEntity(subscriptDTO);
        subscript = subscriptRepository.save(subscript);
        return subscriptMapper.toDto(subscript);
    }

    /**
     * Get all the subscripts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubscriptDTO> findAll() {
        log.debug("Request to get all Subscripts");
        return subscriptRepository.findAll().stream()
            .map(subscriptMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one subscript by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SubscriptDTO> findOne(Long id) {
        log.debug("Request to get Subscript : {}", id);
        return subscriptRepository.findById(id)
            .map(subscriptMapper::toDto);
    }

    /**
     * Delete the subscript by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Subscript : {}", id);
        subscriptRepository.deleteById(id);
    }
}
