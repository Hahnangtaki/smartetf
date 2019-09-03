package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.DealerParticipant;
import id.tech.cakra.etf.repository.DealerParticipantRepository;
import id.tech.cakra.etf.service.DealerParticipantService;
import id.tech.cakra.etf.service.dto.DealerParticipantDTO;
import id.tech.cakra.etf.service.mapper.DealerParticipantMapper;
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
 * Integration tests for the {@link DealerParticipantResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class DealerParticipantResourceIT {

    private static final Integer DEFAULT_DEALER_ID = 1;
    private static final Integer UPDATED_DEALER_ID = 2;
    private static final Integer SMALLER_DEALER_ID = 1 - 1;

    private static final String DEFAULT_DEALER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEALER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DEALER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEALER_NAME = "BBBBBBBBBB";

    @Autowired
    private DealerParticipantRepository dealerParticipantRepository;

    @Autowired
    private DealerParticipantMapper dealerParticipantMapper;

    @Autowired
    private DealerParticipantService dealerParticipantService;

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

    private MockMvc restDealerParticipantMockMvc;

    private DealerParticipant dealerParticipant;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DealerParticipantResource dealerParticipantResource = new DealerParticipantResource(dealerParticipantService);
        this.restDealerParticipantMockMvc = MockMvcBuilders.standaloneSetup(dealerParticipantResource)
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
    public static DealerParticipant createEntity(EntityManager em) {
        DealerParticipant dealerParticipant = new DealerParticipant()
            .dealerId(DEFAULT_DEALER_ID)
            .dealerCode(DEFAULT_DEALER_CODE)
            .dealerName(DEFAULT_DEALER_NAME);
        return dealerParticipant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DealerParticipant createUpdatedEntity(EntityManager em) {
        DealerParticipant dealerParticipant = new DealerParticipant()
            .dealerId(UPDATED_DEALER_ID)
            .dealerCode(UPDATED_DEALER_CODE)
            .dealerName(UPDATED_DEALER_NAME);
        return dealerParticipant;
    }

    @BeforeEach
    public void initTest() {
        dealerParticipant = createEntity(em);
    }

    @Test
    @Transactional
    public void createDealerParticipant() throws Exception {
        int databaseSizeBeforeCreate = dealerParticipantRepository.findAll().size();

        // Create the DealerParticipant
        DealerParticipantDTO dealerParticipantDTO = dealerParticipantMapper.toDto(dealerParticipant);
        restDealerParticipantMockMvc.perform(post("/api/dealer-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerParticipantDTO)))
            .andExpect(status().isCreated());

        // Validate the DealerParticipant in the database
        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeCreate + 1);
        DealerParticipant testDealerParticipant = dealerParticipantList.get(dealerParticipantList.size() - 1);
        assertThat(testDealerParticipant.getDealerId()).isEqualTo(DEFAULT_DEALER_ID);
        assertThat(testDealerParticipant.getDealerCode()).isEqualTo(DEFAULT_DEALER_CODE);
        assertThat(testDealerParticipant.getDealerName()).isEqualTo(DEFAULT_DEALER_NAME);
    }

    @Test
    @Transactional
    public void createDealerParticipantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealerParticipantRepository.findAll().size();

        // Create the DealerParticipant with an existing ID
        dealerParticipant.setId(1L);
        DealerParticipantDTO dealerParticipantDTO = dealerParticipantMapper.toDto(dealerParticipant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealerParticipantMockMvc.perform(post("/api/dealer-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerParticipantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealerParticipant in the database
        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDealerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealerParticipantRepository.findAll().size();
        // set the field null
        dealerParticipant.setDealerId(null);

        // Create the DealerParticipant, which fails.
        DealerParticipantDTO dealerParticipantDTO = dealerParticipantMapper.toDto(dealerParticipant);

        restDealerParticipantMockMvc.perform(post("/api/dealer-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerParticipantDTO)))
            .andExpect(status().isBadRequest());

        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDealerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealerParticipantRepository.findAll().size();
        // set the field null
        dealerParticipant.setDealerCode(null);

        // Create the DealerParticipant, which fails.
        DealerParticipantDTO dealerParticipantDTO = dealerParticipantMapper.toDto(dealerParticipant);

        restDealerParticipantMockMvc.perform(post("/api/dealer-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerParticipantDTO)))
            .andExpect(status().isBadRequest());

        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDealerParticipants() throws Exception {
        // Initialize the database
        dealerParticipantRepository.saveAndFlush(dealerParticipant);

        // Get all the dealerParticipantList
        restDealerParticipantMockMvc.perform(get("/api/dealer-participants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dealerParticipant.getId().intValue())))
            .andExpect(jsonPath("$.[*].dealerId").value(hasItem(DEFAULT_DEALER_ID)))
            .andExpect(jsonPath("$.[*].dealerCode").value(hasItem(DEFAULT_DEALER_CODE.toString())))
            .andExpect(jsonPath("$.[*].dealerName").value(hasItem(DEFAULT_DEALER_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getDealerParticipant() throws Exception {
        // Initialize the database
        dealerParticipantRepository.saveAndFlush(dealerParticipant);

        // Get the dealerParticipant
        restDealerParticipantMockMvc.perform(get("/api/dealer-participants/{id}", dealerParticipant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dealerParticipant.getId().intValue()))
            .andExpect(jsonPath("$.dealerId").value(DEFAULT_DEALER_ID))
            .andExpect(jsonPath("$.dealerCode").value(DEFAULT_DEALER_CODE.toString()))
            .andExpect(jsonPath("$.dealerName").value(DEFAULT_DEALER_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDealerParticipant() throws Exception {
        // Get the dealerParticipant
        restDealerParticipantMockMvc.perform(get("/api/dealer-participants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDealerParticipant() throws Exception {
        // Initialize the database
        dealerParticipantRepository.saveAndFlush(dealerParticipant);

        int databaseSizeBeforeUpdate = dealerParticipantRepository.findAll().size();

        // Update the dealerParticipant
        DealerParticipant updatedDealerParticipant = dealerParticipantRepository.findById(dealerParticipant.getId()).get();
        // Disconnect from session so that the updates on updatedDealerParticipant are not directly saved in db
        em.detach(updatedDealerParticipant);
        updatedDealerParticipant
            .dealerId(UPDATED_DEALER_ID)
            .dealerCode(UPDATED_DEALER_CODE)
            .dealerName(UPDATED_DEALER_NAME);
        DealerParticipantDTO dealerParticipantDTO = dealerParticipantMapper.toDto(updatedDealerParticipant);

        restDealerParticipantMockMvc.perform(put("/api/dealer-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerParticipantDTO)))
            .andExpect(status().isOk());

        // Validate the DealerParticipant in the database
        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeUpdate);
        DealerParticipant testDealerParticipant = dealerParticipantList.get(dealerParticipantList.size() - 1);
        assertThat(testDealerParticipant.getDealerId()).isEqualTo(UPDATED_DEALER_ID);
        assertThat(testDealerParticipant.getDealerCode()).isEqualTo(UPDATED_DEALER_CODE);
        assertThat(testDealerParticipant.getDealerName()).isEqualTo(UPDATED_DEALER_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingDealerParticipant() throws Exception {
        int databaseSizeBeforeUpdate = dealerParticipantRepository.findAll().size();

        // Create the DealerParticipant
        DealerParticipantDTO dealerParticipantDTO = dealerParticipantMapper.toDto(dealerParticipant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealerParticipantMockMvc.perform(put("/api/dealer-participants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dealerParticipantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DealerParticipant in the database
        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDealerParticipant() throws Exception {
        // Initialize the database
        dealerParticipantRepository.saveAndFlush(dealerParticipant);

        int databaseSizeBeforeDelete = dealerParticipantRepository.findAll().size();

        // Delete the dealerParticipant
        restDealerParticipantMockMvc.perform(delete("/api/dealer-participants/{id}", dealerParticipant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DealerParticipant> dealerParticipantList = dealerParticipantRepository.findAll();
        assertThat(dealerParticipantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealerParticipant.class);
        DealerParticipant dealerParticipant1 = new DealerParticipant();
        dealerParticipant1.setId(1L);
        DealerParticipant dealerParticipant2 = new DealerParticipant();
        dealerParticipant2.setId(dealerParticipant1.getId());
        assertThat(dealerParticipant1).isEqualTo(dealerParticipant2);
        dealerParticipant2.setId(2L);
        assertThat(dealerParticipant1).isNotEqualTo(dealerParticipant2);
        dealerParticipant1.setId(null);
        assertThat(dealerParticipant1).isNotEqualTo(dealerParticipant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealerParticipantDTO.class);
        DealerParticipantDTO dealerParticipantDTO1 = new DealerParticipantDTO();
        dealerParticipantDTO1.setId(1L);
        DealerParticipantDTO dealerParticipantDTO2 = new DealerParticipantDTO();
        assertThat(dealerParticipantDTO1).isNotEqualTo(dealerParticipantDTO2);
        dealerParticipantDTO2.setId(dealerParticipantDTO1.getId());
        assertThat(dealerParticipantDTO1).isEqualTo(dealerParticipantDTO2);
        dealerParticipantDTO2.setId(2L);
        assertThat(dealerParticipantDTO1).isNotEqualTo(dealerParticipantDTO2);
        dealerParticipantDTO1.setId(null);
        assertThat(dealerParticipantDTO1).isNotEqualTo(dealerParticipantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(dealerParticipantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(dealerParticipantMapper.fromId(null)).isNull();
    }
}
