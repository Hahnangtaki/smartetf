package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.EtfUnderlyingDtl;
import id.tech.cakra.etf.repository.EtfUnderlyingDtlRepository;
import id.tech.cakra.etf.service.EtfUnderlyingDtlService;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDtlDTO;
import id.tech.cakra.etf.service.mapper.EtfUnderlyingDtlMapper;
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
 * Integration tests for the {@link EtfUnderlyingDtlResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EtfUnderlyingDtlResourceIT {

    private static final Integer DEFAULT_UNDERLYING_ID = 1;
    private static final Integer UPDATED_UNDERLYING_ID = 2;
    private static final Integer SMALLER_UNDERLYING_ID = 1 - 1;

    private static final String DEFAULT_STOCK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STOCK_CODE = "BBBBBBBBBB";

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;
    private static final Float SMALLER_WEIGHT = 1F - 1F;

    @Autowired
    private EtfUnderlyingDtlRepository etfUnderlyingDtlRepository;

    @Autowired
    private EtfUnderlyingDtlMapper etfUnderlyingDtlMapper;

    @Autowired
    private EtfUnderlyingDtlService etfUnderlyingDtlService;

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

    private MockMvc restEtfUnderlyingDtlMockMvc;

    private EtfUnderlyingDtl etfUnderlyingDtl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtfUnderlyingDtlResource etfUnderlyingDtlResource = new EtfUnderlyingDtlResource(etfUnderlyingDtlService);
        this.restEtfUnderlyingDtlMockMvc = MockMvcBuilders.standaloneSetup(etfUnderlyingDtlResource)
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
    public static EtfUnderlyingDtl createEntity(EntityManager em) {
        EtfUnderlyingDtl etfUnderlyingDtl = new EtfUnderlyingDtl()
            .underlyingId(DEFAULT_UNDERLYING_ID)
            .stockCode(DEFAULT_STOCK_CODE)
            .weight(DEFAULT_WEIGHT);
        return etfUnderlyingDtl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtfUnderlyingDtl createUpdatedEntity(EntityManager em) {
        EtfUnderlyingDtl etfUnderlyingDtl = new EtfUnderlyingDtl()
            .underlyingId(UPDATED_UNDERLYING_ID)
            .stockCode(UPDATED_STOCK_CODE)
            .weight(UPDATED_WEIGHT);
        return etfUnderlyingDtl;
    }

    @BeforeEach
    public void initTest() {
        etfUnderlyingDtl = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtfUnderlyingDtl() throws Exception {
        int databaseSizeBeforeCreate = etfUnderlyingDtlRepository.findAll().size();

        // Create the EtfUnderlyingDtl
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO = etfUnderlyingDtlMapper.toDto(etfUnderlyingDtl);
        restEtfUnderlyingDtlMockMvc.perform(post("/api/etf-underlying-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfUnderlyingDtlDTO)))
            .andExpect(status().isCreated());

        // Validate the EtfUnderlyingDtl in the database
        List<EtfUnderlyingDtl> etfUnderlyingDtlList = etfUnderlyingDtlRepository.findAll();
        assertThat(etfUnderlyingDtlList).hasSize(databaseSizeBeforeCreate + 1);
        EtfUnderlyingDtl testEtfUnderlyingDtl = etfUnderlyingDtlList.get(etfUnderlyingDtlList.size() - 1);
        assertThat(testEtfUnderlyingDtl.getUnderlyingId()).isEqualTo(DEFAULT_UNDERLYING_ID);
        assertThat(testEtfUnderlyingDtl.getStockCode()).isEqualTo(DEFAULT_STOCK_CODE);
        assertThat(testEtfUnderlyingDtl.getWeight()).isEqualTo(DEFAULT_WEIGHT);
    }

    @Test
    @Transactional
    public void createEtfUnderlyingDtlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etfUnderlyingDtlRepository.findAll().size();

        // Create the EtfUnderlyingDtl with an existing ID
        etfUnderlyingDtl.setId(1L);
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO = etfUnderlyingDtlMapper.toDto(etfUnderlyingDtl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtfUnderlyingDtlMockMvc.perform(post("/api/etf-underlying-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfUnderlyingDtlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfUnderlyingDtl in the database
        List<EtfUnderlyingDtl> etfUnderlyingDtlList = etfUnderlyingDtlRepository.findAll();
        assertThat(etfUnderlyingDtlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUnderlyingIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = etfUnderlyingDtlRepository.findAll().size();
        // set the field null
        etfUnderlyingDtl.setUnderlyingId(null);

        // Create the EtfUnderlyingDtl, which fails.
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO = etfUnderlyingDtlMapper.toDto(etfUnderlyingDtl);

        restEtfUnderlyingDtlMockMvc.perform(post("/api/etf-underlying-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfUnderlyingDtlDTO)))
            .andExpect(status().isBadRequest());

        List<EtfUnderlyingDtl> etfUnderlyingDtlList = etfUnderlyingDtlRepository.findAll();
        assertThat(etfUnderlyingDtlList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtfUnderlyingDtls() throws Exception {
        // Initialize the database
        etfUnderlyingDtlRepository.saveAndFlush(etfUnderlyingDtl);

        // Get all the etfUnderlyingDtlList
        restEtfUnderlyingDtlMockMvc.perform(get("/api/etf-underlying-dtls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etfUnderlyingDtl.getId().intValue())))
            .andExpect(jsonPath("$.[*].underlyingId").value(hasItem(DEFAULT_UNDERLYING_ID)))
            .andExpect(jsonPath("$.[*].stockCode").value(hasItem(DEFAULT_STOCK_CODE.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEtfUnderlyingDtl() throws Exception {
        // Initialize the database
        etfUnderlyingDtlRepository.saveAndFlush(etfUnderlyingDtl);

        // Get the etfUnderlyingDtl
        restEtfUnderlyingDtlMockMvc.perform(get("/api/etf-underlying-dtls/{id}", etfUnderlyingDtl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etfUnderlyingDtl.getId().intValue()))
            .andExpect(jsonPath("$.underlyingId").value(DEFAULT_UNDERLYING_ID))
            .andExpect(jsonPath("$.stockCode").value(DEFAULT_STOCK_CODE.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEtfUnderlyingDtl() throws Exception {
        // Get the etfUnderlyingDtl
        restEtfUnderlyingDtlMockMvc.perform(get("/api/etf-underlying-dtls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtfUnderlyingDtl() throws Exception {
        // Initialize the database
        etfUnderlyingDtlRepository.saveAndFlush(etfUnderlyingDtl);

        int databaseSizeBeforeUpdate = etfUnderlyingDtlRepository.findAll().size();

        // Update the etfUnderlyingDtl
        EtfUnderlyingDtl updatedEtfUnderlyingDtl = etfUnderlyingDtlRepository.findById(etfUnderlyingDtl.getId()).get();
        // Disconnect from session so that the updates on updatedEtfUnderlyingDtl are not directly saved in db
        em.detach(updatedEtfUnderlyingDtl);
        updatedEtfUnderlyingDtl
            .underlyingId(UPDATED_UNDERLYING_ID)
            .stockCode(UPDATED_STOCK_CODE)
            .weight(UPDATED_WEIGHT);
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO = etfUnderlyingDtlMapper.toDto(updatedEtfUnderlyingDtl);

        restEtfUnderlyingDtlMockMvc.perform(put("/api/etf-underlying-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfUnderlyingDtlDTO)))
            .andExpect(status().isOk());

        // Validate the EtfUnderlyingDtl in the database
        List<EtfUnderlyingDtl> etfUnderlyingDtlList = etfUnderlyingDtlRepository.findAll();
        assertThat(etfUnderlyingDtlList).hasSize(databaseSizeBeforeUpdate);
        EtfUnderlyingDtl testEtfUnderlyingDtl = etfUnderlyingDtlList.get(etfUnderlyingDtlList.size() - 1);
        assertThat(testEtfUnderlyingDtl.getUnderlyingId()).isEqualTo(UPDATED_UNDERLYING_ID);
        assertThat(testEtfUnderlyingDtl.getStockCode()).isEqualTo(UPDATED_STOCK_CODE);
        assertThat(testEtfUnderlyingDtl.getWeight()).isEqualTo(UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingEtfUnderlyingDtl() throws Exception {
        int databaseSizeBeforeUpdate = etfUnderlyingDtlRepository.findAll().size();

        // Create the EtfUnderlyingDtl
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO = etfUnderlyingDtlMapper.toDto(etfUnderlyingDtl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtfUnderlyingDtlMockMvc.perform(put("/api/etf-underlying-dtls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfUnderlyingDtlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfUnderlyingDtl in the database
        List<EtfUnderlyingDtl> etfUnderlyingDtlList = etfUnderlyingDtlRepository.findAll();
        assertThat(etfUnderlyingDtlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtfUnderlyingDtl() throws Exception {
        // Initialize the database
        etfUnderlyingDtlRepository.saveAndFlush(etfUnderlyingDtl);

        int databaseSizeBeforeDelete = etfUnderlyingDtlRepository.findAll().size();

        // Delete the etfUnderlyingDtl
        restEtfUnderlyingDtlMockMvc.perform(delete("/api/etf-underlying-dtls/{id}", etfUnderlyingDtl.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtfUnderlyingDtl> etfUnderlyingDtlList = etfUnderlyingDtlRepository.findAll();
        assertThat(etfUnderlyingDtlList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfUnderlyingDtl.class);
        EtfUnderlyingDtl etfUnderlyingDtl1 = new EtfUnderlyingDtl();
        etfUnderlyingDtl1.setId(1L);
        EtfUnderlyingDtl etfUnderlyingDtl2 = new EtfUnderlyingDtl();
        etfUnderlyingDtl2.setId(etfUnderlyingDtl1.getId());
        assertThat(etfUnderlyingDtl1).isEqualTo(etfUnderlyingDtl2);
        etfUnderlyingDtl2.setId(2L);
        assertThat(etfUnderlyingDtl1).isNotEqualTo(etfUnderlyingDtl2);
        etfUnderlyingDtl1.setId(null);
        assertThat(etfUnderlyingDtl1).isNotEqualTo(etfUnderlyingDtl2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfUnderlyingDtlDTO.class);
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO1 = new EtfUnderlyingDtlDTO();
        etfUnderlyingDtlDTO1.setId(1L);
        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO2 = new EtfUnderlyingDtlDTO();
        assertThat(etfUnderlyingDtlDTO1).isNotEqualTo(etfUnderlyingDtlDTO2);
        etfUnderlyingDtlDTO2.setId(etfUnderlyingDtlDTO1.getId());
        assertThat(etfUnderlyingDtlDTO1).isEqualTo(etfUnderlyingDtlDTO2);
        etfUnderlyingDtlDTO2.setId(2L);
        assertThat(etfUnderlyingDtlDTO1).isNotEqualTo(etfUnderlyingDtlDTO2);
        etfUnderlyingDtlDTO1.setId(null);
        assertThat(etfUnderlyingDtlDTO1).isNotEqualTo(etfUnderlyingDtlDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etfUnderlyingDtlMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etfUnderlyingDtlMapper.fromId(null)).isNull();
    }
}
