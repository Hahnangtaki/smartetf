package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.BankCustody;
import id.tech.cakra.etf.repository.BankCustodyRepository;
import id.tech.cakra.etf.service.BankCustodyService;
import id.tech.cakra.etf.service.dto.BankCustodyDTO;
import id.tech.cakra.etf.service.mapper.BankCustodyMapper;
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
 * Integration tests for the {@link BankCustodyResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class BankCustodyResourceIT {

    private static final Integer DEFAULT_CUSTODY_ID = 1;
    private static final Integer UPDATED_CUSTODY_ID = 2;
    private static final Integer SMALLER_CUSTODY_ID = 1 - 1;

    private static final String DEFAULT_CUSTODY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTODY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTODI_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTODI_NAME = "BBBBBBBBBB";

    @Autowired
    private BankCustodyRepository bankCustodyRepository;

    @Autowired
    private BankCustodyMapper bankCustodyMapper;

    @Autowired
    private BankCustodyService bankCustodyService;

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

    private MockMvc restBankCustodyMockMvc;

    private BankCustody bankCustody;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankCustodyResource bankCustodyResource = new BankCustodyResource(bankCustodyService);
        this.restBankCustodyMockMvc = MockMvcBuilders.standaloneSetup(bankCustodyResource)
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
    public static BankCustody createEntity(EntityManager em) {
        BankCustody bankCustody = new BankCustody()
            .custodyId(DEFAULT_CUSTODY_ID)
            .custodyCode(DEFAULT_CUSTODY_CODE)
            .custodiName(DEFAULT_CUSTODI_NAME);
        return bankCustody;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankCustody createUpdatedEntity(EntityManager em) {
        BankCustody bankCustody = new BankCustody()
            .custodyId(UPDATED_CUSTODY_ID)
            .custodyCode(UPDATED_CUSTODY_CODE)
            .custodiName(UPDATED_CUSTODI_NAME);
        return bankCustody;
    }

    @BeforeEach
    public void initTest() {
        bankCustody = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankCustody() throws Exception {
        int databaseSizeBeforeCreate = bankCustodyRepository.findAll().size();

        // Create the BankCustody
        BankCustodyDTO bankCustodyDTO = bankCustodyMapper.toDto(bankCustody);
        restBankCustodyMockMvc.perform(post("/api/bank-custodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodyDTO)))
            .andExpect(status().isCreated());

        // Validate the BankCustody in the database
        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeCreate + 1);
        BankCustody testBankCustody = bankCustodyList.get(bankCustodyList.size() - 1);
        assertThat(testBankCustody.getCustodyId()).isEqualTo(DEFAULT_CUSTODY_ID);
        assertThat(testBankCustody.getCustodyCode()).isEqualTo(DEFAULT_CUSTODY_CODE);
        assertThat(testBankCustody.getCustodiName()).isEqualTo(DEFAULT_CUSTODI_NAME);
    }

    @Test
    @Transactional
    public void createBankCustodyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankCustodyRepository.findAll().size();

        // Create the BankCustody with an existing ID
        bankCustody.setId(1L);
        BankCustodyDTO bankCustodyDTO = bankCustodyMapper.toDto(bankCustody);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankCustodyMockMvc.perform(post("/api/bank-custodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BankCustody in the database
        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCustodyIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankCustodyRepository.findAll().size();
        // set the field null
        bankCustody.setCustodyId(null);

        // Create the BankCustody, which fails.
        BankCustodyDTO bankCustodyDTO = bankCustodyMapper.toDto(bankCustody);

        restBankCustodyMockMvc.perform(post("/api/bank-custodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodyDTO)))
            .andExpect(status().isBadRequest());

        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCustodyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankCustodyRepository.findAll().size();
        // set the field null
        bankCustody.setCustodyCode(null);

        // Create the BankCustody, which fails.
        BankCustodyDTO bankCustodyDTO = bankCustodyMapper.toDto(bankCustody);

        restBankCustodyMockMvc.perform(post("/api/bank-custodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodyDTO)))
            .andExpect(status().isBadRequest());

        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBankCustodies() throws Exception {
        // Initialize the database
        bankCustodyRepository.saveAndFlush(bankCustody);

        // Get all the bankCustodyList
        restBankCustodyMockMvc.perform(get("/api/bank-custodies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankCustody.getId().intValue())))
            .andExpect(jsonPath("$.[*].custodyId").value(hasItem(DEFAULT_CUSTODY_ID)))
            .andExpect(jsonPath("$.[*].custodyCode").value(hasItem(DEFAULT_CUSTODY_CODE.toString())))
            .andExpect(jsonPath("$.[*].custodiName").value(hasItem(DEFAULT_CUSTODI_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getBankCustody() throws Exception {
        // Initialize the database
        bankCustodyRepository.saveAndFlush(bankCustody);

        // Get the bankCustody
        restBankCustodyMockMvc.perform(get("/api/bank-custodies/{id}", bankCustody.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankCustody.getId().intValue()))
            .andExpect(jsonPath("$.custodyId").value(DEFAULT_CUSTODY_ID))
            .andExpect(jsonPath("$.custodyCode").value(DEFAULT_CUSTODY_CODE.toString()))
            .andExpect(jsonPath("$.custodiName").value(DEFAULT_CUSTODI_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankCustody() throws Exception {
        // Get the bankCustody
        restBankCustodyMockMvc.perform(get("/api/bank-custodies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankCustody() throws Exception {
        // Initialize the database
        bankCustodyRepository.saveAndFlush(bankCustody);

        int databaseSizeBeforeUpdate = bankCustodyRepository.findAll().size();

        // Update the bankCustody
        BankCustody updatedBankCustody = bankCustodyRepository.findById(bankCustody.getId()).get();
        // Disconnect from session so that the updates on updatedBankCustody are not directly saved in db
        em.detach(updatedBankCustody);
        updatedBankCustody
            .custodyId(UPDATED_CUSTODY_ID)
            .custodyCode(UPDATED_CUSTODY_CODE)
            .custodiName(UPDATED_CUSTODI_NAME);
        BankCustodyDTO bankCustodyDTO = bankCustodyMapper.toDto(updatedBankCustody);

        restBankCustodyMockMvc.perform(put("/api/bank-custodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodyDTO)))
            .andExpect(status().isOk());

        // Validate the BankCustody in the database
        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeUpdate);
        BankCustody testBankCustody = bankCustodyList.get(bankCustodyList.size() - 1);
        assertThat(testBankCustody.getCustodyId()).isEqualTo(UPDATED_CUSTODY_ID);
        assertThat(testBankCustody.getCustodyCode()).isEqualTo(UPDATED_CUSTODY_CODE);
        assertThat(testBankCustody.getCustodiName()).isEqualTo(UPDATED_CUSTODI_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingBankCustody() throws Exception {
        int databaseSizeBeforeUpdate = bankCustodyRepository.findAll().size();

        // Create the BankCustody
        BankCustodyDTO bankCustodyDTO = bankCustodyMapper.toDto(bankCustody);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankCustodyMockMvc.perform(put("/api/bank-custodies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankCustodyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BankCustody in the database
        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankCustody() throws Exception {
        // Initialize the database
        bankCustodyRepository.saveAndFlush(bankCustody);

        int databaseSizeBeforeDelete = bankCustodyRepository.findAll().size();

        // Delete the bankCustody
        restBankCustodyMockMvc.perform(delete("/api/bank-custodies/{id}", bankCustody.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankCustody> bankCustodyList = bankCustodyRepository.findAll();
        assertThat(bankCustodyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankCustody.class);
        BankCustody bankCustody1 = new BankCustody();
        bankCustody1.setId(1L);
        BankCustody bankCustody2 = new BankCustody();
        bankCustody2.setId(bankCustody1.getId());
        assertThat(bankCustody1).isEqualTo(bankCustody2);
        bankCustody2.setId(2L);
        assertThat(bankCustody1).isNotEqualTo(bankCustody2);
        bankCustody1.setId(null);
        assertThat(bankCustody1).isNotEqualTo(bankCustody2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankCustodyDTO.class);
        BankCustodyDTO bankCustodyDTO1 = new BankCustodyDTO();
        bankCustodyDTO1.setId(1L);
        BankCustodyDTO bankCustodyDTO2 = new BankCustodyDTO();
        assertThat(bankCustodyDTO1).isNotEqualTo(bankCustodyDTO2);
        bankCustodyDTO2.setId(bankCustodyDTO1.getId());
        assertThat(bankCustodyDTO1).isEqualTo(bankCustodyDTO2);
        bankCustodyDTO2.setId(2L);
        assertThat(bankCustodyDTO1).isNotEqualTo(bankCustodyDTO2);
        bankCustodyDTO1.setId(null);
        assertThat(bankCustodyDTO1).isNotEqualTo(bankCustodyDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bankCustodyMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bankCustodyMapper.fromId(null)).isNull();
    }
}
