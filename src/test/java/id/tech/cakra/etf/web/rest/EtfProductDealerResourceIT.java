package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.EtfProductDealer;
import id.tech.cakra.etf.repository.EtfProductDealerRepository;
import id.tech.cakra.etf.service.EtfProductDealerService;
import id.tech.cakra.etf.service.dto.EtfProductDealerDTO;
import id.tech.cakra.etf.service.mapper.EtfProductDealerMapper;
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
 * Integration tests for the {@link EtfProductDealerResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EtfProductDealerResourceIT {

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;
    private static final Integer SMALLER_PRODUCT_ID = 1 - 1;

    private static final Integer DEFAULT_DEALER_ID = 1;
    private static final Integer UPDATED_DEALER_ID = 2;
    private static final Integer SMALLER_DEALER_ID = 1 - 1;

    @Autowired
    private EtfProductDealerRepository etfProductDealerRepository;

    @Autowired
    private EtfProductDealerMapper etfProductDealerMapper;

    @Autowired
    private EtfProductDealerService etfProductDealerService;

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

    private MockMvc restEtfProductDealerMockMvc;

    private EtfProductDealer etfProductDealer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtfProductDealerResource etfProductDealerResource = new EtfProductDealerResource(etfProductDealerService);
        this.restEtfProductDealerMockMvc = MockMvcBuilders.standaloneSetup(etfProductDealerResource)
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
    public static EtfProductDealer createEntity(EntityManager em) {
        EtfProductDealer etfProductDealer = new EtfProductDealer()
            .productId(DEFAULT_PRODUCT_ID)
            .dealerId(DEFAULT_DEALER_ID);
        return etfProductDealer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtfProductDealer createUpdatedEntity(EntityManager em) {
        EtfProductDealer etfProductDealer = new EtfProductDealer()
            .productId(UPDATED_PRODUCT_ID)
            .dealerId(UPDATED_DEALER_ID);
        return etfProductDealer;
    }

    @BeforeEach
    public void initTest() {
        etfProductDealer = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtfProductDealer() throws Exception {
        int databaseSizeBeforeCreate = etfProductDealerRepository.findAll().size();

        // Create the EtfProductDealer
        EtfProductDealerDTO etfProductDealerDTO = etfProductDealerMapper.toDto(etfProductDealer);
        restEtfProductDealerMockMvc.perform(post("/api/etf-product-dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfProductDealerDTO)))
            .andExpect(status().isCreated());

        // Validate the EtfProductDealer in the database
        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeCreate + 1);
        EtfProductDealer testEtfProductDealer = etfProductDealerList.get(etfProductDealerList.size() - 1);
        assertThat(testEtfProductDealer.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testEtfProductDealer.getDealerId()).isEqualTo(DEFAULT_DEALER_ID);
    }

    @Test
    @Transactional
    public void createEtfProductDealerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etfProductDealerRepository.findAll().size();

        // Create the EtfProductDealer with an existing ID
        etfProductDealer.setId(1L);
        EtfProductDealerDTO etfProductDealerDTO = etfProductDealerMapper.toDto(etfProductDealer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtfProductDealerMockMvc.perform(post("/api/etf-product-dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfProductDealerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfProductDealer in the database
        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProductIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = etfProductDealerRepository.findAll().size();
        // set the field null
        etfProductDealer.setProductId(null);

        // Create the EtfProductDealer, which fails.
        EtfProductDealerDTO etfProductDealerDTO = etfProductDealerMapper.toDto(etfProductDealer);

        restEtfProductDealerMockMvc.perform(post("/api/etf-product-dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfProductDealerDTO)))
            .andExpect(status().isBadRequest());

        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDealerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = etfProductDealerRepository.findAll().size();
        // set the field null
        etfProductDealer.setDealerId(null);

        // Create the EtfProductDealer, which fails.
        EtfProductDealerDTO etfProductDealerDTO = etfProductDealerMapper.toDto(etfProductDealer);

        restEtfProductDealerMockMvc.perform(post("/api/etf-product-dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfProductDealerDTO)))
            .andExpect(status().isBadRequest());

        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEtfProductDealers() throws Exception {
        // Initialize the database
        etfProductDealerRepository.saveAndFlush(etfProductDealer);

        // Get all the etfProductDealerList
        restEtfProductDealerMockMvc.perform(get("/api/etf-product-dealers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etfProductDealer.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].dealerId").value(hasItem(DEFAULT_DEALER_ID)));
    }
    
    @Test
    @Transactional
    public void getEtfProductDealer() throws Exception {
        // Initialize the database
        etfProductDealerRepository.saveAndFlush(etfProductDealer);

        // Get the etfProductDealer
        restEtfProductDealerMockMvc.perform(get("/api/etf-product-dealers/{id}", etfProductDealer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etfProductDealer.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.dealerId").value(DEFAULT_DEALER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingEtfProductDealer() throws Exception {
        // Get the etfProductDealer
        restEtfProductDealerMockMvc.perform(get("/api/etf-product-dealers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtfProductDealer() throws Exception {
        // Initialize the database
        etfProductDealerRepository.saveAndFlush(etfProductDealer);

        int databaseSizeBeforeUpdate = etfProductDealerRepository.findAll().size();

        // Update the etfProductDealer
        EtfProductDealer updatedEtfProductDealer = etfProductDealerRepository.findById(etfProductDealer.getId()).get();
        // Disconnect from session so that the updates on updatedEtfProductDealer are not directly saved in db
        em.detach(updatedEtfProductDealer);
        updatedEtfProductDealer
            .productId(UPDATED_PRODUCT_ID)
            .dealerId(UPDATED_DEALER_ID);
        EtfProductDealerDTO etfProductDealerDTO = etfProductDealerMapper.toDto(updatedEtfProductDealer);

        restEtfProductDealerMockMvc.perform(put("/api/etf-product-dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfProductDealerDTO)))
            .andExpect(status().isOk());

        // Validate the EtfProductDealer in the database
        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeUpdate);
        EtfProductDealer testEtfProductDealer = etfProductDealerList.get(etfProductDealerList.size() - 1);
        assertThat(testEtfProductDealer.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testEtfProductDealer.getDealerId()).isEqualTo(UPDATED_DEALER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingEtfProductDealer() throws Exception {
        int databaseSizeBeforeUpdate = etfProductDealerRepository.findAll().size();

        // Create the EtfProductDealer
        EtfProductDealerDTO etfProductDealerDTO = etfProductDealerMapper.toDto(etfProductDealer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtfProductDealerMockMvc.perform(put("/api/etf-product-dealers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfProductDealerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfProductDealer in the database
        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtfProductDealer() throws Exception {
        // Initialize the database
        etfProductDealerRepository.saveAndFlush(etfProductDealer);

        int databaseSizeBeforeDelete = etfProductDealerRepository.findAll().size();

        // Delete the etfProductDealer
        restEtfProductDealerMockMvc.perform(delete("/api/etf-product-dealers/{id}", etfProductDealer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtfProductDealer> etfProductDealerList = etfProductDealerRepository.findAll();
        assertThat(etfProductDealerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfProductDealer.class);
        EtfProductDealer etfProductDealer1 = new EtfProductDealer();
        etfProductDealer1.setId(1L);
        EtfProductDealer etfProductDealer2 = new EtfProductDealer();
        etfProductDealer2.setId(etfProductDealer1.getId());
        assertThat(etfProductDealer1).isEqualTo(etfProductDealer2);
        etfProductDealer2.setId(2L);
        assertThat(etfProductDealer1).isNotEqualTo(etfProductDealer2);
        etfProductDealer1.setId(null);
        assertThat(etfProductDealer1).isNotEqualTo(etfProductDealer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfProductDealerDTO.class);
        EtfProductDealerDTO etfProductDealerDTO1 = new EtfProductDealerDTO();
        etfProductDealerDTO1.setId(1L);
        EtfProductDealerDTO etfProductDealerDTO2 = new EtfProductDealerDTO();
        assertThat(etfProductDealerDTO1).isNotEqualTo(etfProductDealerDTO2);
        etfProductDealerDTO2.setId(etfProductDealerDTO1.getId());
        assertThat(etfProductDealerDTO1).isEqualTo(etfProductDealerDTO2);
        etfProductDealerDTO2.setId(2L);
        assertThat(etfProductDealerDTO1).isNotEqualTo(etfProductDealerDTO2);
        etfProductDealerDTO1.setId(null);
        assertThat(etfProductDealerDTO1).isNotEqualTo(etfProductDealerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etfProductDealerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etfProductDealerMapper.fromId(null)).isNull();
    }
}
