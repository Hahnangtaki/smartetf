package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.EtfProductDealerService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.EtfProductDealerDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.EtfProductDealer}.
 */
@RestController
@RequestMapping("/api")
public class EtfProductDealerResource {

    private final Logger log = LoggerFactory.getLogger(EtfProductDealerResource.class);

    private static final String ENTITY_NAME = "etfProductDealer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtfProductDealerService etfProductDealerService;

    public EtfProductDealerResource(EtfProductDealerService etfProductDealerService) {
        this.etfProductDealerService = etfProductDealerService;
    }

    /**
     * {@code POST  /etf-product-dealers} : Create a new etfProductDealer.
     *
     * @param etfProductDealerDTO the etfProductDealerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etfProductDealerDTO, or with status {@code 400 (Bad Request)} if the etfProductDealer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etf-product-dealers")
    public ResponseEntity<EtfProductDealerDTO> createEtfProductDealer(@Valid @RequestBody EtfProductDealerDTO etfProductDealerDTO) throws URISyntaxException {
        log.debug("REST request to save EtfProductDealer : {}", etfProductDealerDTO);
        if (etfProductDealerDTO.getId() != null) {
            throw new BadRequestAlertException("A new etfProductDealer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtfProductDealerDTO result = etfProductDealerService.save(etfProductDealerDTO);
        return ResponseEntity.created(new URI("/api/etf-product-dealers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etf-product-dealers} : Updates an existing etfProductDealer.
     *
     * @param etfProductDealerDTO the etfProductDealerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etfProductDealerDTO,
     * or with status {@code 400 (Bad Request)} if the etfProductDealerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etfProductDealerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etf-product-dealers")
    public ResponseEntity<EtfProductDealerDTO> updateEtfProductDealer(@Valid @RequestBody EtfProductDealerDTO etfProductDealerDTO) throws URISyntaxException {
        log.debug("REST request to update EtfProductDealer : {}", etfProductDealerDTO);
        if (etfProductDealerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtfProductDealerDTO result = etfProductDealerService.save(etfProductDealerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, etfProductDealerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etf-product-dealers} : get all the etfProductDealers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etfProductDealers in body.
     */
    @GetMapping("/etf-product-dealers")
    public List<EtfProductDealerDTO> getAllEtfProductDealers() {
        log.debug("REST request to get all EtfProductDealers");
        return etfProductDealerService.findAll();
    }

    /**
     * {@code GET  /etf-product-dealers/:id} : get the "id" etfProductDealer.
     *
     * @param id the id of the etfProductDealerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etfProductDealerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etf-product-dealers/{id}")
    public ResponseEntity<EtfProductDealerDTO> getEtfProductDealer(@PathVariable Long id) {
        log.debug("REST request to get EtfProductDealer : {}", id);
        Optional<EtfProductDealerDTO> etfProductDealerDTO = etfProductDealerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etfProductDealerDTO);
    }

    /**
     * {@code DELETE  /etf-product-dealers/:id} : delete the "id" etfProductDealer.
     *
     * @param id the id of the etfProductDealerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etf-product-dealers/{id}")
    public ResponseEntity<Void> deleteEtfProductDealer(@PathVariable Long id) {
        log.debug("REST request to delete EtfProductDealer : {}", id);
        etfProductDealerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
