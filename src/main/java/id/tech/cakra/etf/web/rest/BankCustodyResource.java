package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.service.BankCustodyService;
import id.tech.cakra.etf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.etf.service.dto.BankCustodyDTO;

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
 * REST controller for managing {@link id.tech.cakra.etf.domain.BankCustody}.
 */
@RestController
@RequestMapping("/api")
public class BankCustodyResource {

    private final Logger log = LoggerFactory.getLogger(BankCustodyResource.class);

    private static final String ENTITY_NAME = "bankCustody";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankCustodyService bankCustodyService;

    public BankCustodyResource(BankCustodyService bankCustodyService) {
        this.bankCustodyService = bankCustodyService;
    }

    /**
     * {@code POST  /bank-custodies} : Create a new bankCustody.
     *
     * @param bankCustodyDTO the bankCustodyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankCustodyDTO, or with status {@code 400 (Bad Request)} if the bankCustody has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bank-custodies")
    public ResponseEntity<BankCustodyDTO> createBankCustody(@Valid @RequestBody BankCustodyDTO bankCustodyDTO) throws URISyntaxException {
        log.debug("REST request to save BankCustody : {}", bankCustodyDTO);
        if (bankCustodyDTO.getId() != null) {
            throw new BadRequestAlertException("A new bankCustody cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankCustodyDTO result = bankCustodyService.save(bankCustodyDTO);
        return ResponseEntity.created(new URI("/api/bank-custodies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bank-custodies} : Updates an existing bankCustody.
     *
     * @param bankCustodyDTO the bankCustodyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankCustodyDTO,
     * or with status {@code 400 (Bad Request)} if the bankCustodyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankCustodyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bank-custodies")
    public ResponseEntity<BankCustodyDTO> updateBankCustody(@Valid @RequestBody BankCustodyDTO bankCustodyDTO) throws URISyntaxException {
        log.debug("REST request to update BankCustody : {}", bankCustodyDTO);
        if (bankCustodyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankCustodyDTO result = bankCustodyService.save(bankCustodyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankCustodyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bank-custodies} : get all the bankCustodies.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankCustodies in body.
     */
    @GetMapping("/bank-custodies")
    public List<BankCustodyDTO> getAllBankCustodies() {
        log.debug("REST request to get all BankCustodies");
        return bankCustodyService.findAll();
    }

    /**
     * {@code GET  /bank-custodies/:id} : get the "id" bankCustody.
     *
     * @param id the id of the bankCustodyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankCustodyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-custodies/{id}")
    public ResponseEntity<BankCustodyDTO> getBankCustody(@PathVariable Long id) {
        log.debug("REST request to get BankCustody : {}", id);
        Optional<BankCustodyDTO> bankCustodyDTO = bankCustodyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankCustodyDTO);
    }

    /**
     * {@code DELETE  /bank-custodies/:id} : delete the "id" bankCustody.
     *
     * @param id the id of the bankCustodyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bank-custodies/{id}")
    public ResponseEntity<Void> deleteBankCustody(@PathVariable Long id) {
        log.debug("REST request to delete BankCustody : {}", id);
        bankCustodyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
