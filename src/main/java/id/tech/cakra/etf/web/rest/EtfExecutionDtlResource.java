package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfExecutionDtlService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfExecutionDtlDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfExecutionDtl}.
 */
@RestController
@RequestMapping("/api")
public class EtfExecutionDtlResource {

    private final Logger log = LoggerFactory.getLogger(EtfExecutionDtlResource.class);

    private static final String ENTITY_NAME = "etfExecutionDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfExecutionDtlService etfExecutionDtlService;

    public EtfExecutionDtlResource(EtfExecutionDtlService etfExecutionDtlService) {
        this.etfExecutionDtlService = etfExecutionDtlService;
    }

    /**
     * {@code POST  /etf-execution-dtls} : Create a new etfExecutionDtl.
     *
     * @param etfExecutionDtlDTO the etfExecutionDtlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfExecutionDtlDTO, or with status {@code 400 (Bad Request)} if the etfExecutionDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-execution-dtls")
    public ResponseEntity<EtfExecutionDtlDTO> createEtfExecutionDtl(@Valid @RequestBody EtfExecutionDtlDTO etfExecutionDtlDTO) throws URISyntaxException {
        log.debug("REST request to save EtfExecutionDtl : {}", etfExecutionDtlDTO);
        if (etfExecutionDtlDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfExecutionDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfExecutionDtlDTO result = etfExecutionDtlService.save(etfExecutionDtlDTO);
        return ResponseEntity.created(new URI("/api/etf-execution-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-execution-dtls} : Updates an existing etfExecutionDtl.
     *
     * @param etfExecutionDtlDTO the etfExecutionDtlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfExecutionDtlDTO,
     * or with status {@code 400 (Bad Request)} if the etfExecutionDtlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfExecutionDtlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-execution-dtls")
    public ResponseEntity<EtfExecutionDtlDTO> updateEtfExecutionDtl(@Valid @RequestBody EtfExecutionDtlDTO etfExecutionDtlDTO) throws URISyntaxException {
        log.debug("REST request to update EtfExecutionDtl : {}", etfExecutionDtlDTO);
        if (etfExecutionDtlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfExecutionDtlDTO result = etfExecutionDtlService.save(etfExecutionDtlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfExecutionDtlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-execution-dtls} : get all the etfExecutionDtls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfExecutionDtls in body.
     */
    @GetMapping("/etf-execution-dtls")
    public List<EtfExecutionDtlDTO> getAllEtfExecutionDtls() {
        log.debug("REST request to get all EtfExecutionDtls");
        return etfExecutionDtlService.findAll();
    }

    /**
     * {@code GET  /etf-execution-dtls/:id} : get the "id" etfExecutionDtl.
     *
     * @param id the id of the etfExecutionDtlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfExecutionDtlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-execution-dtls/{id}")
    public ResponseEntity<EtfExecutionDtlDTO> getEtfExecutionDtl(@PathVariable Long id) {
        log.debug("REST request to get EtfExecutionDtl : {}", id);
        Optional<EtfExecutionDtlDTO> etfExecutionDtlDTO = etfExecutionDtlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfExecutionDtlDTO);
    }

    /**
     * {@code DELETE  /etf-execution-dtls/:id} : delete the "id" etfExecutionDtl.
     *
     * @param id the id of the etfExecutionDtlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-execution-dtls/{id}")
    public ResponseEntity<Void> deleteEtfExecutionDtl(@PathVariable Long id) {
        log.debug("REST request to delete EtfExecutionDtl : {}", id);
        etfExecutionDtlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
