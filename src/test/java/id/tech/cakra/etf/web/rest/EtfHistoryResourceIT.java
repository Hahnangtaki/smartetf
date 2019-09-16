package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.EtfHistory;
import id.tech.cakra.etf.repository.EtfHistoryRepository;
import id.tech.cakra.etf.service.EtfHistoryService;
import id.tech.cakra.etf.service.dto.EtfHistoryDTO;
import id.tech.cakra.etf.service.mapper.EtfHistoryMapper;
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
 * Integration tests for the {@link EtfHistoryResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class EtfHistoryResourceIT {

    private static final LocalDate DEFAULT_VALUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALUE_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_VALUE_DATE = LocalDate.ofEpochDay(-1L);

    private static final Float DEFAULT_UNIT_PRICE = 1F;
    private static final Float UPDATED_UNIT_PRICE = 2F;
    private static final Float SMALLER_UNIT_PRICE = 1F - 1F;

    private static final Float DEFAULT_UNIT_QTY = 1F;
    private static final Float UPDATED_UNIT_QTY = 2F;
    private static final Float SMALLER_UNIT_QTY = 1F - 1F;

    private static final Float DEFAULT_AUM = 1F;
    private static final Float UPDATED_AUM = 2F;
    private static final Float SMALLER_AUM = 1F - 1F;

    @Autowired
    private EtfHistoryRepository etfHistoryRepository;

    @Autowired
    private EtfHistoryMapper etfHistoryMapper;

    @Autowired
    private EtfHistoryService etfHistoryService;

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

    private MockMvc restEtfHistoryMockMvc;

    private EtfHistory etfHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtfHistoryResource etfHistoryResource = new EtfHistoryResource(etfHistoryService);
        this.restEtfHistoryMockMvc = MockMvcBuilders.standaloneSetup(etfHistoryResource)
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
    public static EtfHistory createEntity(EntityManager em) {
        EtfHistory etfHistory = new EtfHistory()
            .valueDate(DEFAULT_VALUE_DATE)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .unitQty(DEFAULT_UNIT_QTY)
            .aum(DEFAULT_AUM);
        return etfHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EtfHistory createUpdatedEntity(EntityManager em) {
        EtfHistory etfHistory = new EtfHistory()
            .valueDate(UPDATED_VALUE_DATE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .unitQty(UPDATED_UNIT_QTY)
            .aum(UPDATED_AUM);
        return etfHistory;
    }

    @BeforeEach
    public void initTest() {
        etfHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtfHistory() throws Exception {
        int databaseSizeBeforeCreate = etfHistoryRepository.findAll().size();

        // Create the EtfHistory
        EtfHistoryDTO etfHistoryDTO = etfHistoryMapper.toDto(etfHistory);
        restEtfHistoryMockMvc.perform(post("/api/etf-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the EtfHistory in the database
        List<EtfHistory> etfHistoryList = etfHistoryRepository.findAll();
        assertThat(etfHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        EtfHistory testEtfHistory = etfHistoryList.get(etfHistoryList.size() - 1);
        assertThat(testEtfHistory.getValueDate()).isEqualTo(DEFAULT_VALUE_DATE);
        assertThat(testEtfHistory.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testEtfHistory.getUnitQty()).isEqualTo(DEFAULT_UNIT_QTY);
        assertThat(testEtfHistory.getAum()).isEqualTo(DEFAULT_AUM);
    }

    @Test
    @Transactional
    public void createEtfHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etfHistoryRepository.findAll().size();

        // Create the EtfHistory with an existing ID
        etfHistory.setId(1L);
        EtfHistoryDTO etfHistoryDTO = etfHistoryMapper.toDto(etfHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtfHistoryMockMvc.perform(post("/api/etf-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfHistory in the database
        List<EtfHistory> etfHistoryList = etfHistoryRepository.findAll();
        assertThat(etfHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEtfHistories() throws Exception {
        // Initialize the database
        etfHistoryRepository.saveAndFlush(etfHistory);

        // Get all the etfHistoryList
        restEtfHistoryMockMvc.perform(get("/api/etf-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etfHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].valueDate").value(hasItem(DEFAULT_VALUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].unitQty").value(hasItem(DEFAULT_UNIT_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].aum").value(hasItem(DEFAULT_AUM.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getEtfHistory() throws Exception {
        // Initialize the database
        etfHistoryRepository.saveAndFlush(etfHistory);

        // Get the etfHistory
        restEtfHistoryMockMvc.perform(get("/api/etf-histories/{id}", etfHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etfHistory.getId().intValue()))
            .andExpect(jsonPath("$.valueDate").value(DEFAULT_VALUE_DATE.toString()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.unitQty").value(DEFAULT_UNIT_QTY.doubleValue()))
            .andExpect(jsonPath("$.aum").value(DEFAULT_AUM.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEtfHistory() throws Exception {
        // Get the etfHistory
        restEtfHistoryMockMvc.perform(get("/api/etf-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtfHistory() throws Exception {
        // Initialize the database
        etfHistoryRepository.saveAndFlush(etfHistory);

        int databaseSizeBeforeUpdate = etfHistoryRepository.findAll().size();

        // Update the etfHistory
        EtfHistory updatedEtfHistory = etfHistoryRepository.findById(etfHistory.getId()).get();
        // Disconnect from session so that the updates on updatedEtfHistory are not directly saved in db
        em.detach(updatedEtfHistory);
        updatedEtfHistory
            .valueDate(UPDATED_VALUE_DATE)
            .unitPrice(UPDATED_UNIT_PRICE)
            .unitQty(UPDATED_UNIT_QTY)
            .aum(UPDATED_AUM);
        EtfHistoryDTO etfHistoryDTO = etfHistoryMapper.toDto(updatedEtfHistory);

        restEtfHistoryMockMvc.perform(put("/api/etf-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the EtfHistory in the database
        List<EtfHistory> etfHistoryList = etfHistoryRepository.findAll();
        assertThat(etfHistoryList).hasSize(databaseSizeBeforeUpdate);
        EtfHistory testEtfHistory = etfHistoryList.get(etfHistoryList.size() - 1);
        assertThat(testEtfHistory.getValueDate()).isEqualTo(UPDATED_VALUE_DATE);
        assertThat(testEtfHistory.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testEtfHistory.getUnitQty()).isEqualTo(UPDATED_UNIT_QTY);
        assertThat(testEtfHistory.getAum()).isEqualTo(UPDATED_AUM);
    }

    @Test
    @Transactional
    public void updateNonExistingEtfHistory() throws Exception {
        int databaseSizeBeforeUpdate = etfHistoryRepository.findAll().size();

        // Create the EtfHistory
        EtfHistoryDTO etfHistoryDTO = etfHistoryMapper.toDto(etfHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtfHistoryMockMvc.perform(put("/api/etf-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etfHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EtfHistory in the database
        List<EtfHistory> etfHistoryList = etfHistoryRepository.findAll();
        assertThat(etfHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtfHistory() throws Exception {
        // Initialize the database
        etfHistoryRepository.saveAndFlush(etfHistory);

        int databaseSizeBeforeDelete = etfHistoryRepository.findAll().size();

        // Delete the etfHistory
        restEtfHistoryMockMvc.perform(delete("/api/etf-histories/{id}", etfHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EtfHistory> etfHistoryList = etfHistoryRepository.findAll();
        assertThat(etfHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfHistory.class);
        EtfHistory etfHistory1 = new EtfHistory();
        etfHistory1.setId(1L);
        EtfHistory etfHistory2 = new EtfHistory();
        etfHistory2.setId(etfHistory1.getId());
        assertThat(etfHistory1).isEqualTo(etfHistory2);
        etfHistory2.setId(2L);
        assertThat(etfHistory1).isNotEqualTo(etfHistory2);
        etfHistory1.setId(null);
        assertThat(etfHistory1).isNotEqualTo(etfHistory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtfHistoryDTO.class);
        EtfHistoryDTO etfHistoryDTO1 = new EtfHistoryDTO();
        etfHistoryDTO1.setId(1L);
        EtfHistoryDTO etfHistoryDTO2 = new EtfHistoryDTO();
        assertThat(etfHistoryDTO1).isNotEqualTo(etfHistoryDTO2);
        etfHistoryDTO2.setId(etfHistoryDTO1.getId());
        assertThat(etfHistoryDTO1).isEqualTo(etfHistoryDTO2);
        etfHistoryDTO2.setId(2L);
        assertThat(etfHistoryDTO1).isNotEqualTo(etfHistoryDTO2);
        etfHistoryDTO1.setId(null);
        assertThat(etfHistoryDTO1).isNotEqualTo(etfHistoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etfHistoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etfHistoryMapper.fromId(null)).isNull();
    }
}
