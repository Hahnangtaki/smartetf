package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.EtfExecution;
import id.tech.cakra.etf.repository.EtfExecutionRepository;
import id.tech.cakra.etf.service.EtfExecutionService;
import id.tech.cakra.etf.service.dto.EtfExecutionDTO;
import id.tech.cakra.etf.service.mapper.EtfExecutionMapper;
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
 * Integration tests for the {@link EtfExecutionResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EtfExecutionResourceIT {

    private static final Integer DEFAULT_BASKET_ORDER_ID = 1;
    private static final Integer UPDATED_BASKET_ORDER_ID = 2;
    private static final Integer SMALLER_BASKET_ORDER_ID = 1 - 1;

    private static final String DEFAULT_STOCK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STOCK_CODE = "BBBBBBBBBB";

    private static final Float DEFAULT_QTY = 1F;
    private static final Float UPDATED_QTY = 2F;
    private static final Float SMALLER_QTY = 1F - 1F;

    private static final Float DEFAULT_QTY_ODD = 1F;
    private static final Float UPDATED_QTY_ODD = 2F;
    private static final Float SMALLER_QTY_ODD = 1F - 1F;

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;
    private static final Float SMALLER_PRICE = 1F - 1F;

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;
    private static final Float SMALLER_AMOUNT = 1F - 1F;

    @Autowired
    private EtfExecutionRepository etfExecutionRepository;

    @Autowired
    private EtfExecutionMapper etfExecutionMapper;

    @Autowired
    private EtfExecutionService etfExecutionService;

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

    private MockMvc restEtfExecutionMockMvc;

    private EtfExecution etfExecution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtfExecutionResource etfExecutionResource = new EtfExecutionResource(etfExecutionService);
        this.restEtfExecutionMockMvc = MockMvcBuilders.standaloneSetup(etfExecutionResource)
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
    public static EtfExecution createEntity(EntityManager em) {
        EtfExecution etfExecution = new EtfExecution()
            .basketOrderId(DEFAULT_BASKET_ORDER_ID)
            .stockCode(DEFAULT_STOCK_CODE)
            .qty(DEFAULT_QTY)
            .qtyOdd(DEFAULT_QTY_ODD)
            .price(DEFAULT_PRICE)
            .amount(DEFAULT_AMOUNT);
        return etfExecution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtfExecution createUpdatedEntity(EntityManager em) {
        EtfExecution etfExecution = new EtfExecution()
            .basketOrderId(UPDATED_BASKET_ORDER_ID)
            .stockCode(UPDATED_STOCK_CODE)
            .qty(UPDATED_QTY)
            .qtyOdd(UPDATED_QTY_ODD)
            .price(UPDATED_PRICE)
            .amount(UPDATED_AMOUNT);
        return etfExecution;
    }

    @BeforeEach
    public void initTest() {
        etfExecution = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtfExecution() throws Exception {
        int databaseSizeBeforeCreate = etfExecutionRepository.findAll().size();

        // Create the EtfExecution
        EtfExecutionDTO etfExecutionDTO = etfExecutionMapper.toDto(etfExecution);
        restEtfExecutionMockMvc.perform(post("/api/etf-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDTO)))
            .andExpect(status().isCreated());

        // Validate the EtfExecution in the database
        List<EtfExecution> etfExecutionList = etfExecutionRepository.findAll();
        assertThat(etfExecutionList).hasSize(databaseSizeBeforeCreate + 1);
        EtfExecution testEtfExecution = etfExecutionList.get(etfExecutionList.size() - 1);
        assertThat(testEtfExecution.getBasketOrderId()).isEqualTo(DEFAULT_BASKET_ORDER_ID);
        assertThat(testEtfExecution.getStockCode()).isEqualTo(DEFAULT_STOCK_CODE);
        assertThat(testEtfExecution.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testEtfExecution.getQtyOdd()).isEqualTo(DEFAULT_QTY_ODD);
        assertThat(testEtfExecution.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testEtfExecution.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createEtfExecutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etfExecutionRepository.findAll().size();

        // Create the EtfExecution with an existing ID
        etfExecution.setId(1L);
        EtfExecutionDTO etfExecutionDTO = etfExecutionMapper.toDto(etfExecution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtfExecutionMockMvc.perform(post("/api/etf-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfExecution in the database
        List<EtfExecution> etfExecutionList = etfExecutionRepository.findAll();
        assertThat(etfExecutionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtfExecutions() throws Exception {
        // Initialize the database
        etfExecutionRepository.saveAndFlush(etfExecution);

        // Get all the etfExecutionList
        restEtfExecutionMockMvc.perform(get("/api/etf-executions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etfExecution.getId().intValue())))
            .andExpect(jsonPath("$.[*].basketOrderId").value(hasItem(DEFAULT_BASKET_ORDER_ID)))
            .andExpect(jsonPath("$.[*].stockCode").value(hasItem(DEFAULT_STOCK_CODE.toString())))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].qtyOdd").value(hasItem(DEFAULT_QTY_ODD.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEtfExecution() throws Exception {
        // Initialize the database
        etfExecutionRepository.saveAndFlush(etfExecution);

        // Get the etfExecution
        restEtfExecutionMockMvc.perform(get("/api/etf-executions/{id}", etfExecution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etfExecution.getId().intValue()))
            .andExpect(jsonPath("$.basketOrderId").value(DEFAULT_BASKET_ORDER_ID))
            .andExpect(jsonPath("$.stockCode").value(DEFAULT_STOCK_CODE.toString()))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY.doubleValue()))
            .andExpect(jsonPath("$.qtyOdd").value(DEFAULT_QTY_ODD.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEtfExecution() throws Exception {
        // Get the etfExecution
        restEtfExecutionMockMvc.perform(get("/api/etf-executions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtfExecution() throws Exception {
        // Initialize the database
        etfExecutionRepository.saveAndFlush(etfExecution);

        int databaseSizeBeforeUpdate = etfExecutionRepository.findAll().size();

        // Update the etfExecution
        EtfExecution updatedEtfExecution = etfExecutionRepository.findById(etfExecution.getId()).get();
        // Disconnect from session so that the updates on updatedEtfExecution are not directly saved in db
        em.detach(updatedEtfExecution);
        updatedEtfExecution
            .basketOrderId(UPDATED_BASKET_ORDER_ID)
            .stockCode(UPDATED_STOCK_CODE)
            .qty(UPDATED_QTY)
            .qtyOdd(UPDATED_QTY_ODD)
            .price(UPDATED_PRICE)
            .amount(UPDATED_AMOUNT);
        EtfExecutionDTO etfExecutionDTO = etfExecutionMapper.toDto(updatedEtfExecution);

        restEtfExecutionMockMvc.perform(put("/api/etf-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDTO)))
            .andExpect(status().isOk());

        // Validate the EtfExecution in the database
        List<EtfExecution> etfExecutionList = etfExecutionRepository.findAll();
        assertThat(etfExecutionList).hasSize(databaseSizeBeforeUpdate);
        EtfExecution testEtfExecution = etfExecutionList.get(etfExecutionList.size() - 1);
        assertThat(testEtfExecution.getBasketOrderId()).isEqualTo(UPDATED_BASKET_ORDER_ID);
        assertThat(testEtfExecution.getStockCode()).isEqualTo(UPDATED_STOCK_CODE);
        assertThat(testEtfExecution.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testEtfExecution.getQtyOdd()).isEqualTo(UPDATED_QTY_ODD);
        assertThat(testEtfExecution.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testEtfExecution.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingEtfExecution() throws Exception {
        int databaseSizeBeforeUpdate = etfExecutionRepository.findAll().size();

        // Create the EtfExecution
        EtfExecutionDTO etfExecutionDTO = etfExecutionMapper.toDto(etfExecution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtfExecutionMockMvc.perform(put("/api/etf-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfExecutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfExecution in the database
        List<EtfExecution> etfExecutionList = etfExecutionRepository.findAll();
        assertThat(etfExecutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtfExecution() throws Exception {
        // Initialize the database
        etfExecutionRepository.saveAndFlush(etfExecution);

        int databaseSizeBeforeDelete = etfExecutionRepository.findAll().size();

        // Delete the etfExecution
        restEtfExecutionMockMvc.perform(delete("/api/etf-executions/{id}", etfExecution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtfExecution> etfExecutionList = etfExecutionRepository.findAll();
        assertThat(etfExecutionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfExecution.class);
        EtfExecution etfExecution1 = new EtfExecution();
        etfExecution1.setId(1L);
        EtfExecution etfExecution2 = new EtfExecution();
        etfExecution2.setId(etfExecution1.getId());
        assertThat(etfExecution1).isEqualTo(etfExecution2);
        etfExecution2.setId(2L);
        assertThat(etfExecution1).isNotEqualTo(etfExecution2);
        etfExecution1.setId(null);
        assertThat(etfExecution1).isNotEqualTo(etfExecution2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfExecutionDTO.class);
        EtfExecutionDTO etfExecutionDTO1 = new EtfExecutionDTO();
        etfExecutionDTO1.setId(1L);
        EtfExecutionDTO etfExecutionDTO2 = new EtfExecutionDTO();
        assertThat(etfExecutionDTO1).isNotEqualTo(etfExecutionDTO2);
        etfExecutionDTO2.setId(etfExecutionDTO1.getId());
        assertThat(etfExecutionDTO1).isEqualTo(etfExecutionDTO2);
        etfExecutionDTO2.setId(2L);
        assertThat(etfExecutionDTO1).isNotEqualTo(etfExecutionDTO2);
        etfExecutionDTO1.setId(null);
        assertThat(etfExecutionDTO1).isNotEqualTo(etfExecutionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etfExecutionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etfExecutionMapper.fromId(null)).isNull();
    }
}
