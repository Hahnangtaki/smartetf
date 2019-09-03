package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfExecutionService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfExecutionDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfExecution}.
 */
@RestController
@RequestMapping("/api")
public class EtfExecutionResource {

    private final Logger log = LoggerFactory.getLogger(EtfExecutionResource.class);

    private static final String ENTITY_NAME = "etfExecution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfExecutionService etfExecutionService;

    public EtfExecutionResource(EtfExecutionService etfExecutionService) {
        this.etfExecutionService = etfExecutionService;
    }

    /**
     * {@code POST  /etf-executions} : Create a new etfExecution.
     *
     * @param etfExecutionDTO the etfExecutionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfExecutionDTO, or with status {@code 400 (Bad Request)} if the etfExecution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-executions")
    public ResponseEntity<EtfExecutionDTO> createEtfExecution(@Valid @RequestBody EtfExecutionDTO etfExecutionDTO) throws URISyntaxException {
        log.debug("REST request to save EtfExecution : {}", etfExecutionDTO);
        if (etfExecutionDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfExecution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfExecutionDTO result = etfExecutionService.save(etfExecutionDTO);
        return ResponseEntity.created(new URI("/api/etf-executions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-executions} : Updates an existing etfExecution.
     *
     * @param etfExecutionDTO the etfExecutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfExecutionDTO,
     * or with status {@code 400 (Bad Request)} if the etfExecutionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfExecutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-executions")
    public ResponseEntity<EtfExecutionDTO> updateEtfExecution(@Valid @RequestBody EtfExecutionDTO etfExecutionDTO) throws URISyntaxException {
        log.debug("REST request to update EtfExecution : {}", etfExecutionDTO);
        if (etfExecutionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfExecutionDTO result = etfExecutionService.save(etfExecutionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfExecutionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-executions} : get all the etfExecutions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfExecutions in body.
     */
    @GetMapping("/etf-executions")
    public List<EtfExecutionDTO> getAllEtfExecutions() {
        log.debug("REST request to get all EtfExecutions");
        return etfExecutionService.findAll();
    }

    /**
     * {@code GET  /etf-executions/:id} : get the "id" etfExecution.
     *
     * @param id the id of the etfExecutionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfExecutionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-executions/{id}")
    public ResponseEntity<EtfExecutionDTO> getEtfExecution(@PathVariable Long id) {
        log.debug("REST request to get EtfExecution : {}", id);
        Optional<EtfExecutionDTO> etfExecutionDTO = etfExecutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfExecutionDTO);
    }

    /**
     * {@code DELETE  /etf-executions/:id} : delete the "id" etfExecution.
     *
     * @param id the id of the etfExecutionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-executions/{id}")
    public ResponseEntity<Void> deleteEtfExecution(@PathVariable Long id) {
        log.debug("REST request to delete EtfExecution : {}", id);
        etfExecutionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
