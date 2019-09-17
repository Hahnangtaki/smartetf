package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.GlobalParameter;
import id.tech.cakra.etf.repository.GlobalParameterRepository;
import id.tech.cakra.etf.service.GlobalParameterService;
import id.tech.cakra.etf.service.dto.GlobalParameterDTO;
import id.tech.cakra.etf.service.mapper.GlobalParameterMapper;
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
 * Integration tests for the {@link GlobalParameterResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class GlobalParameterResourceIT {

    private static final String DEFAULT_PRM_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRM_TYPE = "A";
    private static final String UPDATED_PRM_TYPE = "B";

    private static final String DEFAULT_APP_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_APP_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_INT_VAL = 1;
    private static final Integer UPDATED_INT_VAL = 2;
    private static final Integer SMALLER_INT_VAL = 1 - 1;

    private static final Float DEFAULT_FLOAT_VAL = 1F;
    private static final Float UPDATED_FLOAT_VAL = 2F;
    private static final Float SMALLER_FLOAT_VAL = 1F - 1F;

    private static final String DEFAULT_STR_VAL = "AAAAAAAAAA";
    private static final String UPDATED_STR_VAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_VAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VAL = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_VAL = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_SHOW = false;
    private static final Boolean UPDATED_SHOW = true;

    private static final Boolean DEFAULT_EDIT = false;
    private static final Boolean UPDATED_EDIT = true;

    @Autowired
    private GlobalParameterRepository globalParameterRepository;

    @Autowired
    private GlobalParameterMapper globalParameterMapper;

    @Autowired
    private GlobalParameterService globalParameterService;

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

    private MockMvc restGlobalParameterMockMvc;

    private GlobalParameter globalParameter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GlobalParameterResource globalParameterResource = new GlobalParameterResource(globalParameterService);
        this.restGlobalParameterMockMvc = MockMvcBuilders.standaloneSetup(globalParameterResource)
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
    public static GlobalParameter createEntity(EntityManager em) {
        GlobalParameter globalParameter = new GlobalParameter()
            .prmId(DEFAULT_PRM_ID)
            .prmName(DEFAULT_PRM_NAME)
            .prmType(DEFAULT_PRM_TYPE)
            .appType(DEFAULT_APP_TYPE)
            .intVal(DEFAULT_INT_VAL)
            .floatVal(DEFAULT_FLOAT_VAL)
            .strVal(DEFAULT_STR_VAL)
            .dateVal(DEFAULT_DATE_VAL)
            .show(DEFAULT_SHOW)
            .edit(DEFAULT_EDIT);
        return globalParameter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GlobalParameter createUpdatedEntity(EntityManager em) {
        GlobalParameter globalParameter = new GlobalParameter()
            .prmId(UPDATED_PRM_ID)
            .prmName(UPDATED_PRM_NAME)
            .prmType(UPDATED_PRM_TYPE)
            .appType(UPDATED_APP_TYPE)
            .intVal(UPDATED_INT_VAL)
            .floatVal(UPDATED_FLOAT_VAL)
            .strVal(UPDATED_STR_VAL)
            .dateVal(UPDATED_DATE_VAL)
            .show(UPDATED_SHOW)
            .edit(UPDATED_EDIT);
        return globalParameter;
    }

    @BeforeEach
    public void initTest() {
        globalParameter = createEntity(em);
    }

    @Test
    @Transactional
    public void createGlobalParameter() throws Exception {
        int databaseSizeBeforeCreate = globalParameterRepository.findAll().size();

        // Create the GlobalParameter
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(globalParameter);
        restGlobalParameterMockMvc.perform(post("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isCreated());

        // Validate the GlobalParameter in the database
        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeCreate + 1);
        GlobalParameter testGlobalParameter = globalParameterList.get(globalParameterList.size() - 1);
        assertThat(testGlobalParameter.getPrmId()).isEqualTo(DEFAULT_PRM_ID);
        assertThat(testGlobalParameter.getPrmName()).isEqualTo(DEFAULT_PRM_NAME);
        assertThat(testGlobalParameter.getPrmType()).isEqualTo(DEFAULT_PRM_TYPE);
        assertThat(testGlobalParameter.getAppType()).isEqualTo(DEFAULT_APP_TYPE);
        assertThat(testGlobalParameter.getIntVal()).isEqualTo(DEFAULT_INT_VAL);
        assertThat(testGlobalParameter.getFloatVal()).isEqualTo(DEFAULT_FLOAT_VAL);
        assertThat(testGlobalParameter.getStrVal()).isEqualTo(DEFAULT_STR_VAL);
        assertThat(testGlobalParameter.getDateVal()).isEqualTo(DEFAULT_DATE_VAL);
        assertThat(testGlobalParameter.isShow()).isEqualTo(DEFAULT_SHOW);
        assertThat(testGlobalParameter.isEdit()).isEqualTo(DEFAULT_EDIT);
    }

    @Test
    @Transactional
    public void createGlobalParameterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = globalParameterRepository.findAll().size();

        // Create the GlobalParameter with an existing ID
        globalParameter.setId(1L);
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(globalParameter);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGlobalParameterMockMvc.perform(post("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GlobalParameter in the database
        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPrmIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = globalParameterRepository.findAll().size();
        // set the field null
        globalParameter.setPrmId(null);

        // Create the GlobalParameter, which fails.
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(globalParameter);

        restGlobalParameterMockMvc.perform(post("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isBadRequest());

        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrmNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = globalParameterRepository.findAll().size();
        // set the field null
        globalParameter.setPrmName(null);

        // Create the GlobalParameter, which fails.
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(globalParameter);

        restGlobalParameterMockMvc.perform(post("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isBadRequest());

        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrmTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = globalParameterRepository.findAll().size();
        // set the field null
        globalParameter.setPrmType(null);

        // Create the GlobalParameter, which fails.
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(globalParameter);

        restGlobalParameterMockMvc.perform(post("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isBadRequest());

        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGlobalParameters() throws Exception {
        // Initialize the database
        globalParameterRepository.saveAndFlush(globalParameter);

        // Get all the globalParameterList
        restGlobalParameterMockMvc.perform(get("/api/global-parameters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(globalParameter.getId().intValue())))
            .andExpect(jsonPath("$.[*].prmId").value(hasItem(DEFAULT_PRM_ID.toString())))
            .andExpect(jsonPath("$.[*].prmName").value(hasItem(DEFAULT_PRM_NAME.toString())))
            .andExpect(jsonPath("$.[*].prmType").value(hasItem(DEFAULT_PRM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].appType").value(hasItem(DEFAULT_APP_TYPE.toString())))
            .andExpect(jsonPath("$.[*].intVal").value(hasItem(DEFAULT_INT_VAL)))
            .andExpect(jsonPath("$.[*].floatVal").value(hasItem(DEFAULT_FLOAT_VAL.doubleValue())))
            .andExpect(jsonPath("$.[*].strVal").value(hasItem(DEFAULT_STR_VAL.toString())))
            .andExpect(jsonPath("$.[*].dateVal").value(hasItem(DEFAULT_DATE_VAL.toString())))
            .andExpect(jsonPath("$.[*].show").value(hasItem(DEFAULT_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].edit").value(hasItem(DEFAULT_EDIT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getGlobalParameter() throws Exception {
        // Initialize the database
        globalParameterRepository.saveAndFlush(globalParameter);

        // Get the globalParameter
        restGlobalParameterMockMvc.perform(get("/api/global-parameters/{id}", globalParameter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(globalParameter.getId().intValue()))
            .andExpect(jsonPath("$.prmId").value(DEFAULT_PRM_ID.toString()))
            .andExpect(jsonPath("$.prmName").value(DEFAULT_PRM_NAME.toString()))
            .andExpect(jsonPath("$.prmType").value(DEFAULT_PRM_TYPE.toString()))
            .andExpect(jsonPath("$.appType").value(DEFAULT_APP_TYPE.toString()))
            .andExpect(jsonPath("$.intVal").value(DEFAULT_INT_VAL))
            .andExpect(jsonPath("$.floatVal").value(DEFAULT_FLOAT_VAL.doubleValue()))
            .andExpect(jsonPath("$.strVal").value(DEFAULT_STR_VAL.toString()))
            .andExpect(jsonPath("$.dateVal").value(DEFAULT_DATE_VAL.toString()))
            .andExpect(jsonPath("$.show").value(DEFAULT_SHOW.booleanValue()))
            .andExpect(jsonPath("$.edit").value(DEFAULT_EDIT.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGlobalParameter() throws Exception {
        // Get the globalParameter
        restGlobalParameterMockMvc.perform(get("/api/global-parameters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGlobalParameter() throws Exception {
        // Initialize the database
        globalParameterRepository.saveAndFlush(globalParameter);

        int databaseSizeBeforeUpdate = globalParameterRepository.findAll().size();

        // Update the globalParameter
        GlobalParameter updatedGlobalParameter = globalParameterRepository.findById(globalParameter.getId()).get();
        // Disconnect from session so that the updates on updatedGlobalParameter are not directly saved in db
        em.detach(updatedGlobalParameter);
        updatedGlobalParameter
            .prmId(UPDATED_PRM_ID)
            .prmName(UPDATED_PRM_NAME)
            .prmType(UPDATED_PRM_TYPE)
            .appType(UPDATED_APP_TYPE)
            .intVal(UPDATED_INT_VAL)
            .floatVal(UPDATED_FLOAT_VAL)
            .strVal(UPDATED_STR_VAL)
            .dateVal(UPDATED_DATE_VAL)
            .show(UPDATED_SHOW)
            .edit(UPDATED_EDIT);
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(updatedGlobalParameter);

        restGlobalParameterMockMvc.perform(put("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isOk());

        // Validate the GlobalParameter in the database
        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeUpdate);
        GlobalParameter testGlobalParameter = globalParameterList.get(globalParameterList.size() - 1);
        assertThat(testGlobalParameter.getPrmId()).isEqualTo(UPDATED_PRM_ID);
        assertThat(testGlobalParameter.getPrmName()).isEqualTo(UPDATED_PRM_NAME);
        assertThat(testGlobalParameter.getPrmType()).isEqualTo(UPDATED_PRM_TYPE);
        assertThat(testGlobalParameter.getAppType()).isEqualTo(UPDATED_APP_TYPE);
        assertThat(testGlobalParameter.getIntVal()).isEqualTo(UPDATED_INT_VAL);
        assertThat(testGlobalParameter.getFloatVal()).isEqualTo(UPDATED_FLOAT_VAL);
        assertThat(testGlobalParameter.getStrVal()).isEqualTo(UPDATED_STR_VAL);
        assertThat(testGlobalParameter.getDateVal()).isEqualTo(UPDATED_DATE_VAL);
        assertThat(testGlobalParameter.isShow()).isEqualTo(UPDATED_SHOW);
        assertThat(testGlobalParameter.isEdit()).isEqualTo(UPDATED_EDIT);
    }

    @Test
    @Transactional
    public void updateNonExistingGlobalParameter() throws Exception {
        int databaseSizeBeforeUpdate = globalParameterRepository.findAll().size();

        // Create the GlobalParameter
        GlobalParameterDTO globalParameterDTO = globalParameterMapper.toDto(globalParameter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGlobalParameterMockMvc.perform(put("/api/global-parameters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(globalParameterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GlobalParameter in the database
        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGlobalParameter() throws Exception {
        // Initialize the database
        globalParameterRepository.saveAndFlush(globalParameter);

        int databaseSizeBeforeDelete = globalParameterRepository.findAll().size();

        // Delete the globalParameter
        restGlobalParameterMockMvc.perform(delete("/api/global-parameters/{id}", globalParameter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();
        assertThat(globalParameterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GlobalParameter.class);
        GlobalParameter globalParameter1 = new GlobalParameter();
        globalParameter1.setId(1L);
        GlobalParameter globalParameter2 = new GlobalParameter();
        globalParameter2.setId(globalParameter1.getId());
        assertThat(globalParameter1).isEqualTo(globalParameter2);
        globalParameter2.setId(2L);
        assertThat(globalParameter1).isNotEqualTo(globalParameter2);
        globalParameter1.setId(null);
        assertThat(globalParameter1).isNotEqualTo(globalParameter2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GlobalParameterDTO.class);
        GlobalParameterDTO globalParameterDTO1 = new GlobalParameterDTO();
        globalParameterDTO1.setId(1L);
        GlobalParameterDTO globalParameterDTO2 = new GlobalParameterDTO();
        assertThat(globalParameterDTO1).isNotEqualTo(globalParameterDTO2);
        globalParameterDTO2.setId(globalParameterDTO1.getId());
        assertThat(globalParameterDTO1).isEqualTo(globalParameterDTO2);
        globalParameterDTO2.setId(2L);
        assertThat(globalParameterDTO1).isNotEqualTo(globalParameterDTO2);
        globalParameterDTO1.setId(null);
        assertThat(globalParameterDTO1).isNotEqualTo(globalParameterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(globalParameterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(globalParameterMapper.fromId(null)).isNull();
    }
}
