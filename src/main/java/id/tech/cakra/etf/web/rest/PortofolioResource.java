package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.PortofolioService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.PortofolioDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.etf.domain.Portofolio}.
 */
@RestController
@RequestMapping("/api")
public class PortofolioResource {

    private final Logger log = LoggerFactory.getLogger(PortofolioResource.class);

    private static final String ENTITY_NAME = "portofolio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PortofolioService portofolioService;

    public PortofolioResource(PortofolioService portofolioService) {
        this.portofolioService = portofolioService;
    }

    /**
     * {@code POST  /portofolios} : Create a new portofolio.
     *
     * @param portofolioDTO the portofolioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new portofolioDTO, or with status {@code 400 (Bad Request)} if the portofolio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/portofolios")
    public ResponseEntity<PortofolioDTO> createPortofolio(@RequestBody PortofolioDTO portofolioDTO) throws URISyntaxException {
        log.debug("REST request to save Portofolio : {}", portofolioDTO);
        if (portofolioDTO.getId() != null) {
            throw new BadRequestAlertException("A new portofolio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PortofolioDTO result = portofolioService.save(portofolioDTO);
        return ResponseEntity.created(new URI("/api/portofolios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /portofolios} : Updates an existing portofolio.
     *
     * @param portofolioDTO the portofolioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated portofolioDTO,
     * or with status {@code 400 (Bad Request)} if the portofolioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the portofolioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/portofolios")
    public ResponseEntity<PortofolioDTO> updatePortofolio(@RequestBody PortofolioDTO portofolioDTO) throws URISyntaxException {
        log.debug("REST request to update Portofolio : {}", portofolioDTO);
        if (portofolioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PortofolioDTO result = portofolioService.save(portofolioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, portofolioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /portofolios} : get all the portofolios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of portofolios in body.
     */
    @GetMapping("/portofolios")
    public List<PortofolioDTO> getAllPortofolios() {
        log.debug("REST request to get all Portofolios");
        return portofolioService.findAll();
    }

    /**
     * {@code GET  /portofolios/:id} : get the "id" portofolio.
     *
     * @param id the id of the portofolioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the portofolioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/portofolios/{id}")
    public ResponseEntity<PortofolioDTO> getPortofolio(@PathVariable Long id) {
        log.debug("REST request to get Portofolio : {}", id);
        Optional<PortofolioDTO> portofolioDTO = portofolioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(portofolioDTO);
    }

    /**
     * {@code DELETE  /portofolios/:id} : delete the "id" portofolio.
     *
     * @param id the id of the portofolioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/portofolios/{id}")
    public ResponseEntity<Void> deletePortofolio(@PathVariable Long id) {
        log.debug("REST request to delete Portofolio : {}", id);
        portofolioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
