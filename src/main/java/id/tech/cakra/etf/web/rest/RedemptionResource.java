package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.RedemptionService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.RedemptionDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.Redemption}.
 */
@RestController
@RequestMapping("/api")
public class RedemptionResource {

    private final Logger log = LoggerFactory.getLogger(RedemptionResource.class);

    private static final String ENTITY_NAME = "redemption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RedemptionService redemptionService;

    public RedemptionResource(RedemptionService redemptionService) {
        this.redemptionService = redemptionService;
    }

    /**
     * {@code POST  /redemptions} : Create a new redemption.
     *
     * @param redemptionDTO the redemptionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new redemptionDTO, or with status {@code 400 (Bad Request)} if the redemption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/redemptions")
    public ResponseEntity<RedemptionDTO> createRedemption(@Valid @RequestBody RedemptionDTO redemptionDTO) throws URISyntaxException {
        log.debug("REST request to save Redemption : {}", redemptionDTO);
        if (redemptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new redemption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RedemptionDTO result = redemptionService.save(redemptionDTO);
        return ResponseEntity.created(new URI("/api/redemptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /redemptions} : Updates an existing redemption.
     *
     * @param redemptionDTO the redemptionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated redemptionDTO,
     * or with status {@code 400 (Bad Request)} if the redemptionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the redemptionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/redemptions")
    public ResponseEntity<RedemptionDTO> updateRedemption(@Valid @RequestBody RedemptionDTO redemptionDTO) throws URISyntaxException {
        log.debug("REST request to update Redemption : {}", redemptionDTO);
        if (redemptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RedemptionDTO result = redemptionService.save(redemptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, redemptionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /redemptions} : get all the redemptions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of redemptions in body.
     */
    @GetMapping("/redemptions")
    public List<RedemptionDTO> getAllRedemptions() {
        log.debug("REST request to get all Redemptions");
        return redemptionService.findAll();
    }

    /**
     * {@code GET  /redemptions/:id} : get the "id" redemption.
     *
     * @param id the id of the redemptionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the redemptionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/redemptions/{id}")
    public ResponseEntity<RedemptionDTO> getRedemption(@PathVariable Long id) {
        log.debug("REST request to get Redemption : {}", id);
        Optional<RedemptionDTO> redemptionDTO = redemptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(redemptionDTO);
    }

    /**
     * {@code DELETE  /redemptions/:id} : delete the "id" redemption.
     *
     * @param id the id of the redemptionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/redemptions/{id}")
    public ResponseEntity<Void> deleteRedemption(@PathVariable Long id) {
        log.debug("REST request to delete Redemption : {}", id);
        redemptionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
