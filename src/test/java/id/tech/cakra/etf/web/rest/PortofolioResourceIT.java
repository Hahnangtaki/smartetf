package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.Portofolio;
import id.tech.cakra.etf.repository.PortofolioRepository;
import id.tech.cakra.etf.service.PortofolioService;
import id.tech.cakra.etf.service.dto.PortofolioDTO;
import id.tech.cakra.etf.service.mapper.PortofolioMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.etf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PortofolioResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class PortofolioResourceIT {

    private static final Integer DEFAULT_CUSTOMER_ID = 1;
    private static final Integer UPDATED_CUSTOMER_ID = 2;
    private static final Integer SMALLER_CUSTOMER_ID = 1 - 1;

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;
    private static final Integer SMALLER_PRODUCT_ID = 1 - 1;

    private static final LocalDate DEFAULT_PORTOFOLIO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PORTOFOLIO_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_PORTOFOLIO_DATE = LocalDate.ofEpochDay(-1L);

    private static final Float DEFAULT_UNIT = 1F;
    private static final Float UPDATED_UNIT = 2F;
    private static final Float SMALLER_UNIT = 1F - 1F;

    private static final Float DEFAULT_AVG_PRICE = 1F;
    private static final Float UPDATED_AVG_PRICE = 2F;
    private static final Float SMALLER_AVG_PRICE = 1F - 1F;

    @Autowired
    private PortofolioRepository portofolioRepository;

    @Autowired
    private PortofolioMapper portofolioMapper;

    @Autowired
    private PortofolioService portofolioService;

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

    private MockMvc restPortofolioMockMvc;

    private Portofolio portofolio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PortofolioResource portofolioResource = new PortofolioResource(portofolioService);
        this.restPortofolioMockMvc = MockMvcBuilders.standaloneSetup(portofolioResource)
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
    public static Portofolio createEntity(EntityManager em) {
        Portofolio portofolio = new Portofolio()
            .customerId(DEFAULT_CUSTOMER_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .portofolioDate(DEFAULT_PORTOFOLIO_DATE)
            .unit(DEFAULT_UNIT)
            .avgPrice(DEFAULT_AVG_PRICE);
        return portofolio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Portofolio createUpdatedEntity(EntityManager em) {
        Portofolio portofolio = new Portofolio()
            .customerId(UPDATED_CUSTOMER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .portofolioDate(UPDATED_PORTOFOLIO_DATE)
            .unit(UPDATED_UNIT)
            .avgPrice(UPDATED_AVG_PRICE);
        return portofolio;
    }

    @BeforeEach
    public void initTest() {
        portofolio = createEntity(em);
    }

    @Test
    @Transactional
    public void createPortofolio() throws Exception {
        int databaseSizeBeforeCreate = portofolioRepository.findAll().size();

        // Create the Portofolio
        PortofolioDTO portofolioDTO = portofolioMapper.toDto(portofolio);
        restPortofolioMockMvc.perform(post("/api/portofolios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portofolioDTO)))
            .andExpect(status().isCreated());

        // Validate the Portofolio in the database
        List<Portofolio> portofolioList = portofolioRepository.findAll();
        assertThat(portofolioList).hasSize(databaseSizeBeforeCreate + 1);
        Portofolio testPortofolio = portofolioList.get(portofolioList.size() - 1);
        assertThat(testPortofolio.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testPortofolio.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testPortofolio.getPortofolioDate()).isEqualTo(DEFAULT_PORTOFOLIO_DATE);
        assertThat(testPortofolio.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testPortofolio.getAvgPrice()).isEqualTo(DEFAULT_AVG_PRICE);
    }

    @Test
    @Transactional
    public void createPortofolioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = portofolioRepository.findAll().size();

        // Create the Portofolio with an existing ID
        portofolio.setId(1L);
        PortofolioDTO portofolioDTO = portofolioMapper.toDto(portofolio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPortofolioMockMvc.perform(post("/api/portofolios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portofolioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Portofolio in the database
        List<Portofolio> portofolioList = portofolioRepository.findAll();
        assertThat(portofolioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPortofolios() throws Exception {
        // Initialize the database
        portofolioRepository.saveAndFlush(portofolio);

        // Get all the portofolioList
        restPortofolioMockMvc.perform(get("/api/portofolios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(portofolio.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].portofolioDate").value(hasItem(DEFAULT_PORTOFOLIO_DATE.toString())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.doubleValue())))
            .andExpect(jsonPath("$.[*].avgPrice").value(hasItem(DEFAULT_AVG_PRICE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPortofolio() throws Exception {
        // Initialize the database
        portofolioRepository.saveAndFlush(portofolio);

        // Get the portofolio
        restPortofolioMockMvc.perform(get("/api/portofolios/{id}", portofolio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(portofolio.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.portofolioDate").value(DEFAULT_PORTOFOLIO_DATE.toString()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.doubleValue()))
            .andExpect(jsonPath("$.avgPrice").value(DEFAULT_AVG_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPortofolio() throws Exception {
        // Get the portofolio
        restPortofolioMockMvc.perform(get("/api/portofolios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePortofolio() throws Exception {
        // Initialize the database
        portofolioRepository.saveAndFlush(portofolio);

        int databaseSizeBeforeUpdate = portofolioRepository.findAll().size();

        // Update the portofolio
        Portofolio updatedPortofolio = portofolioRepository.findById(portofolio.getId()).get();
        // Disconnect from session so that the updates on updatedPortofolio are not directly saved in db
        em.detach(updatedPortofolio);
        updatedPortofolio
            .customerId(UPDATED_CUSTOMER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .portofolioDate(UPDATED_PORTOFOLIO_DATE)
            .unit(UPDATED_UNIT)
            .avgPrice(UPDATED_AVG_PRICE);
        PortofolioDTO portofolioDTO = portofolioMapper.toDto(updatedPortofolio);

        restPortofolioMockMvc.perform(put("/api/portofolios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portofolioDTO)))
            .andExpect(status().isOk());

        // Validate the Portofolio in the database
        List<Portofolio> portofolioList = portofolioRepository.findAll();
        assertThat(portofolioList).hasSize(databaseSizeBeforeUpdate);
        Portofolio testPortofolio = portofolioList.get(portofolioList.size() - 1);
        assertThat(testPortofolio.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testPortofolio.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testPortofolio.getPortofolioDate()).isEqualTo(UPDATED_PORTOFOLIO_DATE);
        assertThat(testPortofolio.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testPortofolio.getAvgPrice()).isEqualTo(UPDATED_AVG_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingPortofolio() throws Exception {
        int databaseSizeBeforeUpdate = portofolioRepository.findAll().size();

        // Create the Portofolio
        PortofolioDTO portofolioDTO = portofolioMapper.toDto(portofolio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPortofolioMockMvc.perform(put("/api/portofolios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(portofolioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Portofolio in the database
        List<Portofolio> portofolioList = portofolioRepository.findAll();
        assertThat(portofolioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePortofolio() throws Exception {
        // Initialize the database
        portofolioRepository.saveAndFlush(portofolio);

        int databaseSizeBeforeDelete = portofolioRepository.findAll().size();

        // Delete the portofolio
        restPortofolioMockMvc.perform(delete("/api/portofolios/{id}", portofolio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Portofolio> portofolioList = portofolioRepository.findAll();
        assertThat(portofolioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Portofolio.class);
        Portofolio portofolio1 = new Portofolio();
        portofolio1.setId(1L);
        Portofolio portofolio2 = new Portofolio();
        portofolio2.setId(portofolio1.getId());
        assertThat(portofolio1).isEqualTo(portofolio2);
        portofolio2.setId(2L);
        assertThat(portofolio1).isNotEqualTo(portofolio2);
        portofolio1.setId(null);
        assertThat(portofolio1).isNotEqualTo(portofolio2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PortofolioDTO.class);
        PortofolioDTO portofolioDTO1 = new PortofolioDTO();
        portofolioDTO1.setId(1L);
        PortofolioDTO portofolioDTO2 = new PortofolioDTO();
        assertThat(portofolioDTO1).isNotEqualTo(portofolioDTO2);
        portofolioDTO2.setId(portofolioDTO1.getId());
        assertThat(portofolioDTO1).isEqualTo(portofolioDTO2);
        portofolioDTO2.setId(2L);
        assertThat(portofolioDTO1).isNotEqualTo(portofolioDTO2);
        portofolioDTO1.setId(null);
        assertThat(portofolioDTO1).isNotEqualTo(portofolioDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(portofolioMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(portofolioMapper.fromId(null)).isNull();
    }
}
