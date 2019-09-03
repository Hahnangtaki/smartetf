package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfUnderlyingService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfUnderlying}.
 */
@RestController
@RequestMapping("/api")
public class EtfUnderlyingResource {

    private final Logger log = LoggerFactory.getLogger(EtfUnderlyingResource.class);

    private static final String ENTITY_NAME = "etfUnderlying";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfUnderlyingService etfUnderlyingService;

    public EtfUnderlyingResource(EtfUnderlyingService etfUnderlyingService) {
        this.etfUnderlyingService = etfUnderlyingService;
    }

    /**
     * {@code POST  /etf-underlyings} : Create a new etfUnderlying.
     *
     * @param etfUnderlyingDTO the etfUnderlyingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfUnderlyingDTO, or with status {@code 400 (Bad Request)} if the etfUnderlying has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-underlyings")
    public ResponseEntity<EtfUnderlyingDTO> createEtfUnderlying(@Valid @RequestBody EtfUnderlyingDTO etfUnderlyingDTO) throws URISyntaxException {
        log.debug("REST request to save EtfUnderlying : {}", etfUnderlyingDTO);
        if (etfUnderlyingDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfUnderlying cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfUnderlyingDTO result = etfUnderlyingService.save(etfUnderlyingDTO);
        return ResponseEntity.created(new URI("/api/etf-underlyings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-underlyings} : Updates an existing etfUnderlying.
     *
     * @param etfUnderlyingDTO the etfUnderlyingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfUnderlyingDTO,
     * or with status {@code 400 (Bad Request)} if the etfUnderlyingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfUnderlyingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-underlyings")
    public ResponseEntity<EtfUnderlyingDTO> updateEtfUnderlying(@Valid @RequestBody EtfUnderlyingDTO etfUnderlyingDTO) throws URISyntaxException {
        log.debug("REST request to update EtfUnderlying : {}", etfUnderlyingDTO);
        if (etfUnderlyingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfUnderlyingDTO result = etfUnderlyingService.save(etfUnderlyingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfUnderlyingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-underlyings} : get all the etfUnderlyings.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfUnderlyings in body.
     */
    @GetMapping("/etf-underlyings")
    public List<EtfUnderlyingDTO> getAllEtfUnderlyings() {
        log.debug("REST request to get all EtfUnderlyings");
        return etfUnderlyingService.findAll();
    }

    /**
     * {@code GET  /etf-underlyings/:id} : get the "id" etfUnderlying.
     *
     * @param id the id of the etfUnderlyingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfUnderlyingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-underlyings/{id}")
    public ResponseEntity<EtfUnderlyingDTO> getEtfUnderlying(@PathVariable Long id) {
        log.debug("REST request to get EtfUnderlying : {}", id);
        Optional<EtfUnderlyingDTO> etfUnderlyingDTO = etfUnderlyingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfUnderlyingDTO);
    }

    /**
     * {@code DELETE  /etf-underlyings/:id} : delete the "id" etfUnderlying.
     *
     * @param id the id of the etfUnderlyingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-underlyings/{id}")
    public ResponseEntity<Void> deleteEtfUnderlying(@PathVariable Long id) {
        log.debug("REST request to delete EtfUnderlying : {}", id);
        etfUnderlyingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
