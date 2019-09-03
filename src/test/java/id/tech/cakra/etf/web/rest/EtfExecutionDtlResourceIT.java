package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.EtfExecutionDtl;
import id.tech.cakra.etf.repository.EtfExecutionDtlRepository;
import id.tech.cakra.etf.service.EtfExecutionDtlService;
import id.tech.cakra.etf.service.dto.EtfExecutionDtlDTO;
import id.tech.cakra.etf.service.mapper.EtfExecutionDtlMapper;
import id.tech.cakra.etf.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static id.tech.cakra.etf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EtfExecutionDtlResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EtfExecutionDtlResourceIT {

    private static final Integer DEFAULT_BASKET_ORDER_ID = 1;
    private static final Integer UPDATED_BASKET_ORDER_ID = 2;
    private static final Integer SMALLER_BASKET_ORDER_ID = 1 - 1;

    private static final Integer DEFAULT_TRANSACTION_ID = 1;
    private static final Integer UPDATED_TRANSACTION_ID = 2;
    private static final Integer SMALLER_TRANSACTION_ID = 1 - 1;

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    @Autowired
    private EtfExecutionDtlRepository etfExecutionDtlRepository;

    @Autowired
    private EtfExecutionDtlMapper etfExecutionDtlMapper;

    @Autowired
    private EtfExecutionDtlService etfExecutionDtlService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restEtfExecutionDtlMockMvc;

    private EtfExecutionDtl etfExecutionDtl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtfExecutionDtlResource etfExecutionDtlResource = new EtfExecutionDtlResource(etfExecutionDtlService);
        this.restEtfExecutionDtlMockMvc = MockMvcBuilders.standaloneSetup(etfExecutionDtlResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtfExecutionDtl createEntity(EntityManager em) {
        EtfExecutionDtl etfExecutionDtl = new EtfExecutionDtl()
            .basketOrderId(DEFAULT_BASKET_ORDER_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .transactionType(DEFAULT_TRANSACTION_TYPE);
        return etfExecutionDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtfExecutionDtl createUpdatedEntity(EntityManager em) {
        EtfExecutionDtl etfExecutionDtl = new EtfExecutionDtl()
            .basketOrderId(UPDATED_BASKET_ORDER_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE);
        return etfExecutionDtl;
    }

    @BeforeEach
    public void initTest() {
        etfExecutionDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtfExecutionDtl() throws Exception {
        int databaseSizeBeforeCreate = etfExecutionDtlRepository.findAll().size();

        // Create the EtfExecutionDtl
        EtfExecutionDtlDTO etfExecutionDtlDTO = etfExecutionDtlMapper.toDto(etfExecutionDtl);
        restEtfExecutionDtlMockMvc.perform(post("/api/etf-execution-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDtlDTO)))
            .andExpect(status().isCreated());

        // Validate the EtfExecutionDtl in the database
        List<EtfExecutionDtl> etfExecutionDtlList = etfExecutionDtlRepository.findAll();
        assertThat(etfExecutionDtlList).hasSize(databaseSizeBeforeCreate + 1);
        EtfExecutionDtl testEtfExecutionDtl = etfExecutionDtlList.get(etfExecutionDtlList.size() - 1);
        assertThat(testEtfExecutionDtl.getBasketOrderId()).isEqualTo(DEFAULT_BASKET_ORDER_ID);
        assertThat(testEtfExecutionDtl.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testEtfExecutionDtl.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void createEtfExecutionDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etfExecutionDtlRepository.findAll().size();

        // Create the EtfExecutionDtl with an existing ID
        etfExecutionDtl.setId(1L);
        EtfExecutionDtlDTO etfExecutionDtlDTO = etfExecutionDtlMapper.toDto(etfExecutionDtl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtfExecutionDtlMockMvc.perform(post("/api/etf-execution-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDtlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfExecutionDtl in the database
        List<EtfExecutionDtl> etfExecutionDtlList = etfExecutionDtlRepository.findAll();
        assertThat(etfExecutionDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtfExecutionDtls() throws Exception {
        // Initialize the database
        etfExecutionDtlRepository.saveAndFlush(etfExecutionDtl);

        // Get all the etfExecutionDtlList
        restEtfExecutionDtlMockMvc.perform(get("/api/etf-execution-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etfExecutionDtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].basketOrderId").value(hasItem(DEFAULT_BASKET_ORDER_ID)))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getEtfExecutionDtl() throws Exception {
        // Initialize the database
        etfExecutionDtlRepository.saveAndFlush(etfExecutionDtl);

        // Get the etfExecutionDtl
        restEtfExecutionDtlMockMvc.perform(get("/api/etf-execution-dtls/{id}", etfExecutionDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etfExecutionDtl.getId().intValue()))
            .andExpect(jsonPath("$.basketOrderId").value(DEFAULT_BASKET_ORDER_ID))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEtfExecutionDtl() throws Exception {
        // Get the etfExecutionDtl
        restEtfExecutionDtlMockMvc.perform(get("/api/etf-execution-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtfExecutionDtl() throws Exception {
        // Initialize the database
        etfExecutionDtlRepository.saveAndFlush(etfExecutionDtl);

        int databaseSizeBeforeUpdate = etfExecutionDtlRepository.findAll().size();

        // Update the etfExecutionDtl
        EtfExecutionDtl updatedEtfExecutionDtl = etfExecutionDtlRepository.findById(etfExecutionDtl.getId()).get();
        // Disconnect from session so that the updates on updatedEtfExecutionDtl are not directly saved in db
        em.detach(updatedEtfExecutionDtl);
        updatedEtfExecutionDtl
            .basketOrderId(UPDATED_BASKET_ORDER_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionType(UPDATED_TRANSACTION_TYPE);
        EtfExecutionDtlDTO etfExecutionDtlDTO = etfExecutionDtlMapper.toDto(updatedEtfExecutionDtl);

        restEtfExecutionDtlMockMvc.perform(put("/api/etf-execution-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDtlDTO)))
            .andExpect(status().isOk());

        // Validate the EtfExecutionDtl in the database
        List<EtfExecutionDtl> etfExecutionDtlList = etfExecutionDtlRepository.findAll();
        assertThat(etfExecutionDtlList).hasSize(databaseSizeBeforeUpdate);
        EtfExecutionDtl testEtfExecutionDtl = etfExecutionDtlList.get(etfExecutionDtlList.size() - 1);
        assertThat(testEtfExecutionDtl.getBasketOrderId()).isEqualTo(UPDATED_BASKET_ORDER_ID);
        assertThat(testEtfExecutionDtl.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testEtfExecutionDtl.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingEtfExecutionDtl() throws Exception {
        int databaseSizeBeforeUpdate = etfExecutionDtlRepository.findAll().size();

        // Create the EtfExecutionDtl
        EtfExecutionDtlDTO etfExecutionDtlDTO = etfExecutionDtlMapper.toDto(etfExecutionDtl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtfExecutionDtlMockMvc.perform(put("/api/etf-execution-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDtlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfExecutionDtl in the database
        List<EtfExecutionDtl> etfExecutionDtlList = etfExecutionDtlRepository.findAll();
        assertThat(etfExecutionDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtfExecutionDtl() throws Exception {
        // Initialize the database
        etfExecutionDtlRepository.saveAndFlush(etfExecutionDtl);

        int databaseSizeBeforeDelete = etfExecutionDtlRepository.findAll().size();

        // Delete the etfExecutionDtl
        restEtfExecutionDtlMockMvc.perform(delete("/api/etf-execution-dtls/{id}", etfExecutionDtl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtfExecutionDtl> etfExecutionDtlList = etfExecutionDtlRepository.findAll();
        assertThat(etfExecutionDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfExecutionDtl.class);
        EtfExecutionDtl etfExecutionDtl1 = new EtfExecutionDtl();
        etfExecutionDtl1.setId(1L);
        EtfExecutionDtl etfExecutionDtl2 = new EtfExecutionDtl();
        etfExecutionDtl2.setId(etfExecutionDtl1.getId());
        assertThat(etfExecutionDtl1).isEqualTo(etfExecutionDtl2);
        etfExecutionDtl2.setId(2L);
        assertThat(etfExecutionDtl1).isNotEqualTo(etfExecutionDtl2);
        etfExecutionDtl1.setId(null);
        assertThat(etfExecutionDtl1).isNotEqualTo(etfExecutionDtl2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfExecutionDtlDTO.class);
        EtfExecutionDtlDTO etfExecutionDtlDTO1 = new EtfExecutionDtlDTO();
        etfExecutionDtlDTO1.setId(1L);
        EtfExecutionDtlDTO etfExecutionDtlDTO2 = new EtfExecutionDtlDTO();
        assertThat(etfExecutionDtlDTO1).isNotEqualTo(etfExecutionDtlDTO2);
        etfExecutionDtlDTO2.setId(etfExecutionDtlDTO1.getId());
        assertThat(etfExecutionDtlDTO1).isEqualTo(etfExecutionDtlDTO2);
        etfExecutionDtlDTO2.setId(2L);
        assertThat(etfExecutionDtlDTO1).isNotEqualTo(etfExecutionDtlDTO2);
        etfExecutionDtlDTO1.setId(null);
        assertThat(etfExecutionDtlDTO1).isNotEqualTo(etfExecutionDtlDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etfExecutionDtlMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etfExecutionDtlMapper.fromId(null)).isNull();
    }
}
