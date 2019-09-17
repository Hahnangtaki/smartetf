package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfUnderlyingDtlService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDtlDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfUnderlyingDtl}.
 */
@RestController
@RequestMapping("/api")
public class EtfUnderlyingDtlResource {

    private final Logger log = LoggerFactory.getLogger(EtfUnderlyingDtlResource.class);

    private static final String ENTITY_NAME = "etfUnderlyingDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfUnderlyingDtlService etfUnderlyingDtlService;

    public EtfUnderlyingDtlResource(EtfUnderlyingDtlService etfUnderlyingDtlService) {
        this.etfUnderlyingDtlService = etfUnderlyingDtlService;
    }

    /**
     * {@code POST  /etf-underlying-dtls} : Create a new etfUnderlyingDtl.
     *
     * @param etfUnderlyingDtlDTO the etfUnderlyingDtlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfUnderlyingDtlDTO, or with status {@code 400 (Bad Request)} if the etfUnderlyingDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-underlying-dtls")
    public ResponseEntity<EtfUnderlyingDtlDTO> createEtfUnderlyingDtl(@Valid @RequestBody EtfUnderlyingDtlDTO etfUnderlyingDtlDTO) throws URISyntaxException {
        log.debug("REST request to save EtfUnderlyingDtl : {}", etfUnderlyingDtlDTO);
        if (etfUnderlyingDtlDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfUnderlyingDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfUnderlyingDtlDTO result = etfUnderlyingDtlService.save(etfUnderlyingDtlDTO);
        return ResponseEntity.created(new URI("/api/etf-underlying-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-underlying-dtls} : Updates an existing etfUnderlyingDtl.
     *
     * @param etfUnderlyingDtlDTO the etfUnderlyingDtlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfUnderlyingDtlDTO,
     * or with status {@code 400 (Bad Request)} if the etfUnderlyingDtlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfUnderlyingDtlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-underlying-dtls")
    public ResponseEntity<EtfUnderlyingDtlDTO> updateEtfUnderlyingDtl(@Valid @RequestBody EtfUnderlyingDtlDTO etfUnderlyingDtlDTO) throws URISyntaxException {
        log.debug("REST request to update EtfUnderlyingDtl : {}", etfUnderlyingDtlDTO);
        if (etfUnderlyingDtlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfUnderlyingDtlDTO result = etfUnderlyingDtlService.save(etfUnderlyingDtlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfUnderlyingDtlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-underlying-dtls} : get all the etfUnderlyingDtls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfUnderlyingDtls in body.
     */
    @GetMapping("/etf-underlying-dtls")
    public List<EtfUnderlyingDtlDTO> getAllEtfUnderlyingDtls() {
        log.debug("REST request to get all EtfUnderlyingDtls");
        return etfUnderlyingDtlService.findAll();
    }

    /**
     * {@code GET  /etf-underlying-dtls/:id} : get the "id" etfUnderlyingDtl.
     *
     * @param id the id of the etfUnderlyingDtlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfUnderlyingDtlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-underlying-dtls/{id}")
    public ResponseEntity<EtfUnderlyingDtlDTO> getEtfUnderlyingDtl(@PathVariable Long id) {
        log.debug("REST request to get EtfUnderlyingDtl : {}", id);
        Optional<EtfUnderlyingDtlDTO> etfUnderlyingDtlDTO = etfUnderlyingDtlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfUnderlyingDtlDTO);
    }

    /**
     * {@code DELETE  /etf-underlying-dtls/:id} : delete the "id" etfUnderlyingDtl.
     *
     * @param id the id of the etfUnderlyingDtlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-underlying-dtls/{id}")
    public ResponseEntity<Void> deleteEtfUnderlyingDtl(@PathVariable Long id) {
        log.debug("REST request to delete EtfUnderlyingDtl : {}", id);
        etfUnderlyingDtlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
