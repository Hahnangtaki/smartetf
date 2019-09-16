package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.DealerParticipantService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.DealerParticipantDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.etf.domain.DealerParticipant}.
 */
@RestController
@RequestMapping("/api")
public class DealerParticipantResource {

    private final Logger log = LoggerFactory.getLogger(DealerParticipantResource.class);

    private static final String ENTITY_NAME = "dealerParticipant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DealerParticipantService dealerParticipantService;

    public DealerParticipantResource(DealerParticipantService dealerParticipantService) {
        this.dealerParticipantService = dealerParticipantService;
    }

    /**
     * {@code POST  /dealer-participants} : Create a new dealerParticipant.
     *
     * @param dealerParticipantDTO the dealerParticipantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dealerParticipantDTO, or with status {@code 400 (Bad Request)} if the dealerParticipant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dealer-participants")
    public ResponseEntity<DealerParticipantDTO> createDealerParticipant(@Valid @RequestBody DealerParticipantDTO dealerParticipantDTO) throws URISyntaxException {
        log.debug("REST request to save DealerParticipant : {}", dealerParticipantDTO);
        if (dealerParticipantDTO.getId() != null) {
            throw new BadRequestAlertException("A new dealerParticipant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DealerParticipantDTO result = dealerParticipantService.save(dealerParticipantDTO);
        return ResponseEntity.created(new URI("/api/dealer-participants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dealer-participants} : Updates an existing dealerParticipant.
     *
     * @param dealerParticipantDTO the dealerParticipantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dealerParticipantDTO,
     * or with status {@code 400 (Bad Request)} if the dealerParticipantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dealerParticipantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dealer-participants")
    public ResponseEntity<DealerParticipantDTO> updateDealerParticipant(@Valid @RequestBody DealerParticipantDTO dealerParticipantDTO) throws URISyntaxException {
        log.debug("REST request to update DealerParticipant : {}", dealerParticipantDTO);
        if (dealerParticipantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DealerParticipantDTO result = dealerParticipantService.save(dealerParticipantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dealerParticipantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dealer-participants} : get all the dealerParticipants.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dealerParticipants in body.
     */
    @GetMapping("/dealer-participants")
    public List<DealerParticipantDTO> getAllDealerParticipants() {
        log.debug("REST request to get all DealerParticipants");
        return dealerParticipantService.findAll();
    }

    /**
     * {@code GET  /dealer-participants/:id} : get the "id" dealerParticipant.
     *
     * @param id the id of the dealerParticipantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dealerParticipantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dealer-participants/{id}")
    public ResponseEntity<DealerParticipantDTO> getDealerParticipant(@PathVariable Long id) {
        log.debug("REST request to get DealerParticipant : {}", id);
        Optional<DealerParticipantDTO> dealerParticipantDTO = dealerParticipantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dealerParticipantDTO);
    }

    /**
     * {@code DELETE  /dealer-participants/:id} : delete the "id" dealerParticipant.
     *
     * @param id the id of the dealerParticipantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dealer-participants/{id}")
    public ResponseEntity<Void> deleteDealerParticipant(@PathVariable Long id) {
        log.debug("REST request to delete DealerParticipant : {}", id);
        dealerParticipantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
