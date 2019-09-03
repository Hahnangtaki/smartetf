package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfProductService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfProductDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfProduct}.
 */
@RestController
@RequestMapping("/api")
public class EtfProductResource {

    private final Logger log = LoggerFactory.getLogger(EtfProductResource.class);

    private static final String ENTITY_NAME = "etfProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfProductService etfProductService;

    public EtfProductResource(EtfProductService etfProductService) {
        this.etfProductService = etfProductService;
    }

    /**
     * {@code POST  /etf-products} : Create a new etfProduct.
     *
     * @param etfProductDTO the etfProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfProductDTO, or with status {@code 400 (Bad Request)} if the etfProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-products")
    public ResponseEntity<EtfProductDTO> createEtfProduct(@Valid @RequestBody EtfProductDTO etfProductDTO) throws URISyntaxException {
        log.debug("REST request to save EtfProduct : {}", etfProductDTO);
        if (etfProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfProductDTO result = etfProductService.save(etfProductDTO);
        return ResponseEntity.created(new URI("/api/etf-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-products} : Updates an existing etfProduct.
     *
     * @param etfProductDTO the etfProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfProductDTO,
     * or with status {@code 400 (Bad Request)} if the etfProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-products")
    public ResponseEntity<EtfProductDTO> updateEtfProduct(@Valid @RequestBody EtfProductDTO etfProductDTO) throws URISyntaxException {
        log.debug("REST request to update EtfProduct : {}", etfProductDTO);
        if (etfProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfProductDTO result = etfProductService.save(etfProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-products} : get all the etfProducts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfProducts in body.
     */
    @GetMapping("/etf-products")
    public List<EtfProductDTO> getAllEtfProducts() {
        log.debug("REST request to get all EtfProducts");
        return etfProductService.findAll();
    }

    /**
     * {@code GET  /etf-products/:id} : get the "id" etfProduct.
     *
     * @param id the id of the etfProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-products/{id}")
    public ResponseEntity<EtfProductDTO> getEtfProduct(@PathVariable Long id) {
        log.debug("REST request to get EtfProduct : {}", id);
        Optional<EtfProductDTO> etfProductDTO = etfProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfProductDTO);
    }

    /**
     * {@code DELETE  /etf-products/:id} : delete the "id" etfProduct.
     *
     * @param id the id of the etfProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-products/{id}")
    public ResponseEntity<Void> deleteEtfProduct(@PathVariable Long id) {
        log.debug("REST request to delete EtfProduct : {}", id);
        etfProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
