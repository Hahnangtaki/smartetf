package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.SubscriptService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.SubscriptDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.Subscript}.
 */
@RestController
@RequestMapping("/api")
public class SubscriptResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptResource.class);

    private static final String ENTITY_NAME = "subscript";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriptService subscriptService;

    public SubscriptResource(SubscriptService subscriptService) {
        this.subscriptService = subscriptService;
    }

    /**
     * {@code POST  /subscripts} : Create a new subscript.
     *
     * @param subscriptDTO the subscriptDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriptDTO, or with status {@code 400 (Bad Request)} if the subscript has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subscripts")
    public ResponseEntity<SubscriptDTO> createSubscript(@Valid @RequestBody SubscriptDTO subscriptDTO) throws URISyntaxException {
        log.debug("REST request to save Subscript : {}", subscriptDTO);
        if (subscriptDTO.getId() != null) {
            throw new BadRequestAlertException("A new subscript cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubscriptDTO result = subscriptService.save(subscriptDTO);
        return ResponseEntity.created(new URI("/api/subscripts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subscripts} : Updates an existing subscript.
     *
     * @param subscriptDTO the subscriptDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptDTO,
     * or with status {@code 400 (Bad Request)} if the subscriptDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriptDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subscripts")
    public ResponseEntity<SubscriptDTO> updateSubscript(@Valid @RequestBody SubscriptDTO subscriptDTO) throws URISyntaxException {
        log.debug("REST request to update Subscript : {}", subscriptDTO);
        if (subscriptDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubscriptDTO result = subscriptService.save(subscriptDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, subscriptDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subscripts} : get all the subscripts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscripts in body.
     */
    @GetMapping("/subscripts")
    public List<SubscriptDTO> getAllSubscripts() {
        log.debug("REST request to get all Subscripts");
        return subscriptService.findAll();
    }

    /**
     * {@code GET  /subscripts/:id} : get the "id" subscript.
     *
     * @param id the id of the subscriptDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriptDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscripts/{id}")
    public ResponseEntity<SubscriptDTO> getSubscript(@PathVariable Long id) {
        log.debug("REST request to get Subscript : {}", id);
        Optional<SubscriptDTO> subscriptDTO = subscriptService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subscriptDTO);
    }

    /**
     * {@code DELETE  /subscripts/:id} : delete the "id" subscript.
     *
     * @param id the id of the subscriptDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subscripts/{id}")
    public ResponseEntity<Void> deleteSubscript(@PathVariable Long id) {
        log.debug("REST request to delete Subscript : {}", id);
        subscriptService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
