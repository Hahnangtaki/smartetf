package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.Mi;
import id.tech.cakra.etf.repository.MiRepository;
import id.tech.cakra.etf.service.MiService;
import id.tech.cakra.etf.service.dto.MiDTO;
import id.tech.cakra.etf.service.mapper.MiMapper;
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
 * Integration tests for the {@link MiResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class MiResourceIT {

    private static final Integer DEFAULT_MI_ID = 1;
    private static final Integer UPDATED_MI_ID = 2;
    private static final Integer SMALLER_MI_ID = 1 - 1;

    private static final String DEFAULT_MI_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MI_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MI_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MI_NAME = "BBBBBBBBBB";

    @Autowired
    private MiRepository miRepository;

    @Autowired
    private MiMapper miMapper;

    @Autowired
    private MiService miService;

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

    private MockMvc restMiMockMvc;

    private Mi mi;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MiResource miResource = new MiResource(miService);
        this.restMiMockMvc = MockMvcBuilders.standaloneSetup(miResource)
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
    public static Mi createEntity(EntityManager em) {
        Mi mi = new Mi()
            .miId(DEFAULT_MI_ID)
            .miCode(DEFAULT_MI_CODE)
            .miName(DEFAULT_MI_NAME);
        return mi;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mi createUpdatedEntity(EntityManager em) {
        Mi mi = new Mi()
            .miId(UPDATED_MI_ID)
            .miCode(UPDATED_MI_CODE)
            .miName(UPDATED_MI_NAME);
        return mi;
    }

    @BeforeEach
    public void initTest() {
        mi = createEntity(em);
    }

    @Test
    @Transactional
    public void createMi() throws Exception {
        int databaseSizeBeforeCreate = miRepository.findAll().size();

        // Create the Mi
        MiDTO miDTO = miMapper.toDto(mi);
        restMiMockMvc.perform(post("/api/mis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miDTO)))
            .andExpect(status().isCreated());

        // Validate the Mi in the database
        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeCreate + 1);
        Mi testMi = miList.get(miList.size() - 1);
        assertThat(testMi.getMiId()).isEqualTo(DEFAULT_MI_ID);
        assertThat(testMi.getMiCode()).isEqualTo(DEFAULT_MI_CODE);
        assertThat(testMi.getMiName()).isEqualTo(DEFAULT_MI_NAME);
    }

    @Test
    @Transactional
    public void createMiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = miRepository.findAll().size();

        // Create the Mi with an existing ID
        mi.setId(1L);
        MiDTO miDTO = miMapper.toDto(mi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMiMockMvc.perform(post("/api/mis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mi in the database
        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMiIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = miRepository.findAll().size();
        // set the field null
        mi.setMiId(null);

        // Create the Mi, which fails.
        MiDTO miDTO = miMapper.toDto(mi);

        restMiMockMvc.perform(post("/api/mis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miDTO)))
            .andExpect(status().isBadRequest());

        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMiCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = miRepository.findAll().size();
        // set the field null
        mi.setMiCode(null);

        // Create the Mi, which fails.
        MiDTO miDTO = miMapper.toDto(mi);

        restMiMockMvc.perform(post("/api/mis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miDTO)))
            .andExpect(status().isBadRequest());

        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMis() throws Exception {
        // Initialize the database
        miRepository.saveAndFlush(mi);

        // Get all the miList
        restMiMockMvc.perform(get("/api/mis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mi.getId().intValue())))
            .andExpect(jsonPath("$.[*].miId").value(hasItem(DEFAULT_MI_ID)))
            .andExpect(jsonPath("$.[*].miCode").value(hasItem(DEFAULT_MI_CODE.toString())))
            .andExpect(jsonPath("$.[*].miName").value(hasItem(DEFAULT_MI_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getMi() throws Exception {
        // Initialize the database
        miRepository.saveAndFlush(mi);

        // Get the mi
        restMiMockMvc.perform(get("/api/mis/{id}", mi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mi.getId().intValue()))
            .andExpect(jsonPath("$.miId").value(DEFAULT_MI_ID))
            .andExpect(jsonPath("$.miCode").value(DEFAULT_MI_CODE.toString()))
            .andExpect(jsonPath("$.miName").value(DEFAULT_MI_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMi() throws Exception {
        // Get the mi
        restMiMockMvc.perform(get("/api/mis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMi() throws Exception {
        // Initialize the database
        miRepository.saveAndFlush(mi);

        int databaseSizeBeforeUpdate = miRepository.findAll().size();

        // Update the mi
        Mi updatedMi = miRepository.findById(mi.getId()).get();
        // Disconnect from session so that the updates on updatedMi are not directly saved in db
        em.detach(updatedMi);
        updatedMi
            .miId(UPDATED_MI_ID)
            .miCode(UPDATED_MI_CODE)
            .miName(UPDATED_MI_NAME);
        MiDTO miDTO = miMapper.toDto(updatedMi);

        restMiMockMvc.perform(put("/api/mis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miDTO)))
            .andExpect(status().isOk());

        // Validate the Mi in the database
        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeUpdate);
        Mi testMi = miList.get(miList.size() - 1);
        assertThat(testMi.getMiId()).isEqualTo(UPDATED_MI_ID);
        assertThat(testMi.getMiCode()).isEqualTo(UPDATED_MI_CODE);
        assertThat(testMi.getMiName()).isEqualTo(UPDATED_MI_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMi() throws Exception {
        int databaseSizeBeforeUpdate = miRepository.findAll().size();

        // Create the Mi
        MiDTO miDTO = miMapper.toDto(mi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMiMockMvc.perform(put("/api/mis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(miDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mi in the database
        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMi() throws Exception {
        // Initialize the database
        miRepository.saveAndFlush(mi);

        int databaseSizeBeforeDelete = miRepository.findAll().size();

        // Delete the mi
        restMiMockMvc.perform(delete("/api/mis/{id}", mi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mi> miList = miRepository.findAll();
        assertThat(miList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mi.class);
        Mi mi1 = new Mi();
        mi1.setId(1L);
        Mi mi2 = new Mi();
        mi2.setId(mi1.getId());
        assertThat(mi1).isEqualTo(mi2);
        mi2.setId(2L);
        assertThat(mi1).isNotEqualTo(mi2);
        mi1.setId(null);
        assertThat(mi1).isNotEqualTo(mi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MiDTO.class);
        MiDTO miDTO1 = new MiDTO();
        miDTO1.setId(1L);
        MiDTO miDTO2 = new MiDTO();
        assertThat(miDTO1).isNotEqualTo(miDTO2);
        miDTO2.setId(miDTO1.getId());
        assertThat(miDTO1).isEqualTo(miDTO2);
        miDTO2.setId(2L);
        assertThat(miDTO1).isNotEqualTo(miDTO2);
        miDTO1.setId(null);
        assertThat(miDTO1).isNotEqualTo(miDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(miMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(miMapper.fromId(null)).isNull();
    }
}
