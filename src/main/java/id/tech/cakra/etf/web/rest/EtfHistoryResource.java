package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfHistoryService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfHistoryDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfHistory}.
 */
@RestController
@RequestMapping("/api")
public class EtfHistoryResource {

    private final Logger log = LoggerFactory.getLogger(EtfHistoryResource.class);

    private static final String ENTITY_NAME = "etfHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfHistoryService etfHistoryService;

    public EtfHistoryResource(EtfHistoryService etfHistoryService) {
        this.etfHistoryService = etfHistoryService;
    }

    /**
     * {@code POST  /etf-histories} : Create a new etfHistory.
     *
     * @param etfHistoryDTO the etfHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfHistoryDTO, or with status {@code 400 (Bad Request)} if the etfHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-histories")
    public ResponseEntity<EtfHistoryDTO> createEtfHistory(@Valid @RequestBody EtfHistoryDTO etfHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save EtfHistory : {}", etfHistoryDTO);
        if (etfHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfHistoryDTO result = etfHistoryService.save(etfHistoryDTO);
        return ResponseEntity.created(new URI("/api/etf-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-histories} : Updates an existing etfHistory.
     *
     * @param etfHistoryDTO the etfHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the etfHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-histories")
    public ResponseEntity<EtfHistoryDTO> updateEtfHistory(@Valid @RequestBody EtfHistoryDTO etfHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update EtfHistory : {}", etfHistoryDTO);
        if (etfHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfHistoryDTO result = etfHistoryService.save(etfHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-histories} : get all the etfHistories.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfHistories in body.
     */
    @GetMapping("/etf-histories")
    public List<EtfHistoryDTO> getAllEtfHistories() {
        log.debug("REST request to get all EtfHistories");
        return etfHistoryService.findAll();
    }

    /**
     * {@code GET  /etf-histories/:id} : get the "id" etfHistory.
     *
     * @param id the id of the etfHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-histories/{id}")
    public ResponseEntity<EtfHistoryDTO> getEtfHistory(@PathVariable Long id) {
        log.debug("REST request to get EtfHistory : {}", id);
        Optional<EtfHistoryDTO> etfHistoryDTO = etfHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfHistoryDTO);
    }

    /**
     * {@code DELETE  /etf-histories/:id} : delete the "id" etfHistory.
     *
     * @param id the id of the etfHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-histories/{id}")
    public ResponseEntity<Void> deleteEtfHistory(@PathVariable Long id) {
        log.debug("REST request to delete EtfHistory : {}", id);
        etfHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
