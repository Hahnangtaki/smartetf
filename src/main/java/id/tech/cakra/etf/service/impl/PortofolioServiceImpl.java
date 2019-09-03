package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.PortofolioService;
import id.tech.cakra.etf.domain.Portofolio;
import id.tech.cakra.etf.repository.PortofolioRepository;
import id.tech.cakra.etf.service.dto.PortofolioDTO;
import id.tech.cakra.etf.service.mapper.PortofolioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Portofolio}.
 */
@Service
@Transactional
public class PortofolioServiceImpl implements PortofolioService {

    private final Logger log = LoggerFactory.getLogger(PortofolioServiceImpl.class);

    private final PortofolioRepository portofolioRepository;

    private final PortofolioMapper portofolioMapper;

    public PortofolioServiceImpl(PortofolioRepository portofolioRepository, PortofolioMapper portofolioMapper) {
        this.portofolioRepository = portofolioRepository;
        this.portofolioMapper = portofolioMapper;
    }

    /**
     * Save a portofolio.
     *
     * @param portofolioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PortofolioDTO save(PortofolioDTO portofolioDTO) {
        log.debug("Request to save Portofolio : {}", portofolioDTO);
        Portofolio portofolio = portofolioMapper.toEntity(portofolioDTO);
        portofolio = portofolioRepository.save(portofolio);
        return portofolioMapper.toDto(portofolio);
    }

    /**
     * Get all the portofolios.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PortofolioDTO> findAll() {
        log.debug("Request to get all Portofolios");
        return portofolioRepository.findAll().stream()
            .map(portofolioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one portofolio by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PortofolioDTO> findOne(Long id) {
        log.debug("Request to get Portofolio : {}", id);
        return portofolioRepository.findById(id)
            .map(portofolioMapper::toDto);
    }

    /**
     * Delete the portofolio by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Portofolio : {}", id);
        portofolioRepository.deleteById(id);
    }
}
