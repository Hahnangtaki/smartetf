package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.MiService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.MiDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.Mi}.
 */
@RestController
@RequestMapping("/api")
public class MiResource {

    private final Logger log = LoggerFactory.getLogger(MiResource.class);

    private static final String ENTITY_NAME = "mi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MiService miService;

    public MiResource(MiService miService) {
        this.miService = miService;
    }

    /**
     * {@code POST  /mis} : Create a new mi.
     *
     * @param miDTO the miDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new miDTO, or with status {@code 400 (Bad Request)} if the mi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mis")
    public ResponseEntity<MiDTO> createMi(@Valid @RequestBody MiDTO miDTO) throws URISyntaxException {
        log.debug("REST request to save Mi : {}", miDTO);
        if (miDTO.getId() != null) {
            throw new BadRequestAlertException("A new mi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MiDTO result = miService.save(miDTO);
        return ResponseEntity.created(new URI("/api/mis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mis} : Updates an existing mi.
     *
     * @param miDTO the miDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated miDTO,
     * or with status {@code 400 (Bad Request)} if the miDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the miDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mis")
    public ResponseEntity<MiDTO> updateMi(@Valid @RequestBody MiDTO miDTO) throws URISyntaxException {
        log.debug("REST request to update Mi : {}", miDTO);
        if (miDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MiDTO result = miService.save(miDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, miDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mis} : get all the mis.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mis in body.
     */
    @GetMapping("/mis")
    public List<MiDTO> getAllMis() {
        log.debug("REST request to get all Mis");
        return miService.findAll();
    }

    /**
     * {@code GET  /mis/:id} : get the "id" mi.
     *
     * @param id the id of the miDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the miDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mis/{id}")
    public ResponseEntity<MiDTO> getMi(@PathVariable Long id) {
        log.debug("REST request to get Mi : {}", id);
        Optional<MiDTO> miDTO = miService.findOne(id);
        return ResponseUtil.wrapOrNotFound(miDTO);
    }

    /**
     * {@code DELETE  /mis/:id} : delete the "id" mi.
     *
     * @param id the id of the miDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mis/{id}")
    public ResponseEntity<Void> deleteMi(@PathVariable Long id) {
        log.debug("REST request to delete Mi : {}", id);
        miService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
