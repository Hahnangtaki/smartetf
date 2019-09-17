package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.MiService;
import id.tech.cakra.etf.domain.Mi;
import id.tech.cakra.etf.repository.MiRepository;
import id.tech.cakra.etf.service.dto.MiDTO;
import id.tech.cakra.etf.service.mapper.MiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Mi}.
 */
@Service
@Transactional
public class MiServiceImpl implements MiService {

    private final Logger log = LoggerFactory.getLogger(MiServiceImpl.class);

    private final MiRepository miRepository;

    private final MiMapper miMapper;

    public MiServiceImpl(MiRepository miRepository, MiMapper miMapper) {
        this.miRepository = miRepository;
        this.miMapper = miMapper;
    }

    /**
     * Save a mi.
     *
     * @param miDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MiDTO save(MiDTO miDTO) {
        log.debug("Request to save Mi : {}", miDTO);
        Mi mi = miMapper.toEntity(miDTO);
        mi = miRepository.save(mi);
        return miMapper.toDto(mi);
    }

    /**
     * Get all the mis.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MiDTO> findAll() {
        log.debug("Request to get all Mis");
        return miRepository.findAll().stream()
            .map(miMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MiDTO> findOne(Long id) {
        log.debug("Request to get Mi : {}", id);
        return miRepository.findById(id)
            .map(miMapper::toDto);
    }

    /**
     * Delete the mi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mi : {}", id);
        miRepository.deleteById(id);
    }
}
