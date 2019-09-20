package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.RedemptionService;
import id.tech.cakra.etf.domain.Redemption;
import id.tech.cakra.etf.repository.RedemptionRepository;
import id.tech.cakra.etf.service.dto.RedemptionDTO;
import id.tech.cakra.etf.service.mapper.RedemptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Redemption}.
 */
@Service
@Transactional
public class RedemptionServiceImpl implements RedemptionService {

    private final Logger log = LoggerFactory.getLogger(RedemptionServiceImpl.class);

    private final RedemptionRepository redemptionRepository;

    private final RedemptionMapper redemptionMapper;

    public RedemptionServiceImpl(RedemptionRepository redemptionRepository, RedemptionMapper redemptionMapper) {
        this.redemptionRepository = redemptionRepository;
        this.redemptionMapper = redemptionMapper;
    }

    /**
     * Save a redemption.
     *
     * @param redemptionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RedemptionDTO save(RedemptionDTO redemptionDTO) {
        log.debug("Request to save Redemption : {}", redemptionDTO);
        Redemption redemption = redemptionMapper.toEntity(redemptionDTO);
        redemption = redemptionRepository.save(redemption);
        return redemptionMapper.toDto(redemption);
    }

    /**
     * Get all the redemptions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<RedemptionDTO> findAll() {
        log.debug("Request to get all Redemptions");
        return redemptionRepository.findAll().stream()
            .map(redemptionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one redemption by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RedemptionDTO> findOne(Long id) {
        log.debug("Request to get Redemption : {}", id);
        return redemptionRepository.findById(id)
            .map(redemptionMapper::toDto);
    }

    /**
     * Delete the redemption by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Redemption : {}", id);
        redemptionRepository.deleteById(id);
    }
}
