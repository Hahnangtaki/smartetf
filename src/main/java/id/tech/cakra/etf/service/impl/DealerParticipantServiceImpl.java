package id.tech.cakra.etf.service.impl;

import id.tech.cakra.etf.service.DealerParticipantService;
import id.tech.cakra.etf.domain.DealerParticipant;
import id.tech.cakra.etf.repository.DealerParticipantRepository;
import id.tech.cakra.etf.service.dto.DealerParticipantDTO;
import id.tech.cakra.etf.service.mapper.DealerParticipantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DealerParticipant}.
 */
@Service
@Transactional
public class DealerParticipantServiceImpl implements DealerParticipantService {

    private final Logger log = LoggerFactory.getLogger(DealerParticipantServiceImpl.class);

    private final DealerParticipantRepository dealerParticipantRepository;

    private final DealerParticipantMapper dealerParticipantMapper;

    public DealerParticipantServiceImpl(DealerParticipantRepository dealerParticipantRepository, DealerParticipantMapper dealerParticipantMapper) {
        this.dealerParticipantRepository = dealerParticipantRepository;
        this.dealerParticipantMapper = dealerParticipantMapper;
    }

    /**
     * Save a dealerParticipant.
     *
     * @param dealerParticipantDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DealerParticipantDTO save(DealerParticipantDTO dealerParticipantDTO) {
        log.debug("Request to save DealerParticipant : {}", dealerParticipantDTO);
        DealerParticipant dealerParticipant = dealerParticipantMapper.toEntity(dealerParticipantDTO);
        dealerParticipant = dealerParticipantRepository.save(dealerParticipant);
        return dealerParticipantMapper.toDto(dealerParticipant);
    }

    /**
     * Get all the dealerParticipants.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DealerParticipantDTO> findAll() {
        log.debug("Request to get all DealerParticipants");
        return dealerParticipantRepository.findAll().stream()
            .map(dealerParticipantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dealerParticipant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DealerParticipantDTO> findOne(Long id) {
        log.debug("Request to get DealerParticipant : {}", id);
        return dealerParticipantRepository.findById(id)
            .map(dealerParticipantMapper::toDto);
    }

    /**
     * Delete the dealerParticipant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DealerParticipant : {}", id);
        dealerParticipantRepository.deleteById(id);
    }
}
