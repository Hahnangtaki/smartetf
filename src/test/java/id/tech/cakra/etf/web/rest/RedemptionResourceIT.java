package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.Redemption;
import id.tech.cakra.etf.repository.RedemptionRepository;
import id.tech.cakra.etf.service.RedemptionService;
import id.tech.cakra.etf.service.dto.RedemptionDTO;
import id.tech.cakra.etf.service.mapper.RedemptionMapper;
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
 * Integration tests for the {@link RedemptionResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class RedemptionResourceIT {

    private static final String DEFAULT_REDEMPTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REDEMPTION_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REDEMPTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REDEMPTION_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_REDEMPTION_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_UNIT_SELL_PRICE_IND = 1F;
    private static final Float UPDATED_UNIT_SELL_PRICE_IND = 2F;
    private static final Float SMALLER_UNIT_SELL_PRICE_IND = 1F - 1F;

    private static final Float DEFAULT_UNIT_SELL_PRICE = 1F;
    private static final Float UPDATED_UNIT_SELL_PRICE = 2F;
    private static final Float SMALLER_UNIT_SELL_PRICE = 1F - 1F;

    private static final Float DEFAULT_UNIT_SELL_UNIT = 1F;
    private static final Float UPDATED_UNIT_SELL_UNIT = 2F;
    private static final Float SMALLER_UNIT_SELL_UNIT = 1F - 1F;

    private static final Float DEFAULT_UNIT_SELL_LOT = 1F;
    private static final Float UPDATED_UNIT_SELL_LOT = 2F;
    private static final Float SMALLER_UNIT_SELL_LOT = 1F - 1F;

    private static final Float DEFAULT_UNIT_SELL_BASKET = 1F;
    private static final Float UPDATED_UNIT_SELL_BASKET = 2F;
    private static final Float SMALLER_UNIT_SELL_BASKET = 1F - 1F;

    private static final String DEFAULT_MARKET = "AAAAAAAAAA";
    private static final String UPDATED_MARKET = "BBBBBBBBBB";

    private static final Float DEFAULT_SELL_GROSS_AMOUNT = 1F;
    private static final Float UPDATED_SELL_GROSS_AMOUNT = 2F;
    private static final Float SMALLER_SELL_GROSS_AMOUNT = 1F - 1F;

    private static final Float DEFAULT_FEE = 1F;
    private static final Float UPDATED_FEE = 2F;
    private static final Float SMALLER_FEE = 1F - 1F;

    private static final Float DEFAULT_SELL_NET_AMOUNT = 1F;
    private static final Float UPDATED_SELL_NET_AMOUNT = 2F;
    private static final Float SMALLER_SELL_NET_AMOUNT = 1F - 1F;

    private static final String DEFAULT_STATUS_CASH = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CASH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_ETF = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_ETF = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHANNEL = 1;
    private static final Integer UPDATED_CHANNEL = 2;
    private static final Integer SMALLER_CHANNEL = 1 - 1;

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private RedemptionMapper redemptionMapper;

    @Autowired
    private RedemptionService redemptionService;

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

    private MockMvc restRedemptionMockMvc;

    private Redemption redemption;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RedemptionResource redemptionResource = new RedemptionResource(redemptionService);
        this.restRedemptionMockMvc = MockMvcBuilders.standaloneSetup(redemptionResource)
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
    public static Redemption createEntity(EntityManager em) {
        Redemption redemption = new Redemption()
            .redemptionCode(DEFAULT_REDEMPTION_CODE)
            .redemptionDate(DEFAULT_REDEMPTION_DATE)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .unitSellPriceInd(DEFAULT_UNIT_SELL_PRICE_IND)
            .unitSellPrice(DEFAULT_UNIT_SELL_PRICE)
            .unitSellUnit(DEFAULT_UNIT_SELL_UNIT)
            .unitSellLot(DEFAULT_UNIT_SELL_LOT)
            .unitSellBasket(DEFAULT_UNIT_SELL_BASKET)
            .market(DEFAULT_MARKET)
            .sellGrossAmount(DEFAULT_SELL_GROSS_AMOUNT)
            .fee(DEFAULT_FEE)
            .sellNetAmount(DEFAULT_SELL_NET_AMOUNT)
            .statusCash(DEFAULT_STATUS_CASH)
            .statusEtf(DEFAULT_STATUS_ETF)
            .status(DEFAULT_STATUS)
            .channel(DEFAULT_CHANNEL);
        return redemption;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Redemption createUpdatedEntity(EntityManager em) {
        Redemption redemption = new Redemption()
            .redemptionCode(UPDATED_REDEMPTION_CODE)
            .redemptionDate(UPDATED_REDEMPTION_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .unitSellPriceInd(UPDATED_UNIT_SELL_PRICE_IND)
            .unitSellPrice(UPDATED_UNIT_SELL_PRICE)
            .unitSellUnit(UPDATED_UNIT_SELL_UNIT)
            .unitSellLot(UPDATED_UNIT_SELL_LOT)
            .unitSellBasket(UPDATED_UNIT_SELL_BASKET)
            .market(UPDATED_MARKET)
            .sellGrossAmount(UPDATED_SELL_GROSS_AMOUNT)
            .fee(UPDATED_FEE)
            .sellNetAmount(UPDATED_SELL_NET_AMOUNT)
            .statusCash(UPDATED_STATUS_CASH)
            .statusEtf(UPDATED_STATUS_ETF)
            .status(UPDATED_STATUS)
            .channel(UPDATED_CHANNEL);
        return redemption;
    }

    @BeforeEach
    public void initTest() {
        redemption = createEntity(em);
    }

    @Test
    @Transactional
    public void createRedemption() throws Exception {
        int databaseSizeBeforeCreate = redemptionRepository.findAll().size();

        // Create the Redemption
        RedemptionDTO redemptionDTO = redemptionMapper.toDto(redemption);
        restRedemptionMockMvc.perform(post("/api/redemptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(redemptionDTO)))
            .andExpect(status().isCreated());

        // Validate the Redemption in the database
        List<Redemption> redemptionList = redemptionRepository.findAll();
        assertThat(redemptionList).hasSize(databaseSizeBeforeCreate + 1);
        Redemption testRedemption = redemptionList.get(redemptionList.size() - 1);
        assertThat(testRedemption.getRedemptionCode()).isEqualTo(DEFAULT_REDEMPTION_CODE);
        assertThat(testRedemption.getRedemptionDate()).isEqualTo(DEFAULT_REDEMPTION_DATE);
        assertThat(testRedemption.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testRedemption.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testRedemption.getUnitSellPriceInd()).isEqualTo(DEFAULT_UNIT_SELL_PRICE_IND);
        assertThat(testRedemption.getUnitSellPrice()).isEqualTo(DEFAULT_UNIT_SELL_PRICE);
        assertThat(testRedemption.getUnitSellUnit()).isEqualTo(DEFAULT_UNIT_SELL_UNIT);
        assertThat(testRedemption.getUnitSellLot()).isEqualTo(DEFAULT_UNIT_SELL_LOT);
        assertThat(testRedemption.getUnitSellBasket()).isEqualTo(DEFAULT_UNIT_SELL_BASKET);
        assertThat(testRedemption.getMarket()).isEqualTo(DEFAULT_MARKET);
        assertThat(testRedemption.getSellGrossAmount()).isEqualTo(DEFAULT_SELL_GROSS_AMOUNT);
        assertThat(testRedemption.getFee()).isEqualTo(DEFAULT_FEE);
        assertThat(testRedemption.getSellNetAmount()).isEqualTo(DEFAULT_SELL_NET_AMOUNT);
        assertThat(testRedemption.getStatusCash()).isEqualTo(DEFAULT_STATUS_CASH);
        assertThat(testRedemption.getStatusEtf()).isEqualTo(DEFAULT_STATUS_ETF);
        assertThat(testRedemption.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRedemption.getChannel()).isEqualTo(DEFAULT_CHANNEL);
    }

    @Test
    @Transactional
    public void createRedemptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = redemptionRepository.findAll().size();

        // Create the Redemption with an existing ID
        redemption.setId(1L);
        RedemptionDTO redemptionDTO = redemptionMapper.toDto(redemption);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRedemptionMockMvc.perform(post("/api/redemptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(redemptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Redemption in the database
        List<Redemption> redemptionList = redemptionRepository.findAll();
        assertThat(redemptionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRedemptions() throws Exception {
        // Initialize the database
        redemptionRepository.saveAndFlush(redemption);

        // Get all the redemptionList
        restRedemptionMockMvc.perform(get("/api/redemptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(redemption.getId().intValue())))
            .andExpect(jsonPath("$.[*].redemptionCode").value(hasItem(DEFAULT_REDEMPTION_CODE.toString())))
            .andExpect(jsonPath("$.[*].redemptionDate").value(hasItem(DEFAULT_REDEMPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].unitSellPriceInd").value(hasItem(DEFAULT_UNIT_SELL_PRICE_IND.doubleValue())))
            .andExpect(jsonPath("$.[*].unitSellPrice").value(hasItem(DEFAULT_UNIT_SELL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].unitSellUnit").value(hasItem(DEFAULT_UNIT_SELL_UNIT.doubleValue())))
            .andExpect(jsonPath("$.[*].unitSellLot").value(hasItem(DEFAULT_UNIT_SELL_LOT.doubleValue())))
            .andExpect(jsonPath("$.[*].unitSellBasket").value(hasItem(DEFAULT_UNIT_SELL_BASKET.doubleValue())))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET.toString())))
            .andExpect(jsonPath("$.[*].sellGrossAmount").value(hasItem(DEFAULT_SELL_GROSS_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].fee").value(hasItem(DEFAULT_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].sellNetAmount").value(hasItem(DEFAULT_SELL_NET_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].statusCash").value(hasItem(DEFAULT_STATUS_CASH.toString())))
            .andExpect(jsonPath("$.[*].statusEtf").value(hasItem(DEFAULT_STATUS_ETF.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)));
    }
    
    @Test
    @Transactional
    public void getRedemption() throws Exception {
        // Initialize the database
        redemptionRepository.saveAndFlush(redemption);

        // Get the redemption
        restRedemptionMockMvc.perform(get("/api/redemptions/{id}", redemption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(redemption.getId().intValue()))
            .andExpect(jsonPath("$.redemptionCode").value(DEFAULT_REDEMPTION_CODE.toString()))
            .andExpect(jsonPath("$.redemptionDate").value(DEFAULT_REDEMPTION_DATE.toString()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME.toString()))
            .andExpect(jsonPath("$.unitSellPriceInd").value(DEFAULT_UNIT_SELL_PRICE_IND.doubleValue()))
            .andExpect(jsonPath("$.unitSellPrice").value(DEFAULT_UNIT_SELL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.unitSellUnit").value(DEFAULT_UNIT_SELL_UNIT.doubleValue()))
            .andExpect(jsonPath("$.unitSellLot").value(DEFAULT_UNIT_SELL_LOT.doubleValue()))
            .andExpect(jsonPath("$.unitSellBasket").value(DEFAULT_UNIT_SELL_BASKET.doubleValue()))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET.toString()))
            .andExpect(jsonPath("$.sellGrossAmount").value(DEFAULT_SELL_GROSS_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.fee").value(DEFAULT_FEE.doubleValue()))
            .andExpect(jsonPath("$.sellNetAmount").value(DEFAULT_SELL_NET_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.statusCash").value(DEFAULT_STATUS_CASH.toString()))
            .andExpect(jsonPath("$.statusEtf").value(DEFAULT_STATUS_ETF.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL));
    }

    @Test
    @Transactional
    public void getNonExistingRedemption() throws Exception {
        // Get the redemption
        restRedemptionMockMvc.perform(get("/api/redemptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRedemption() throws Exception {
        // Initialize the database
        redemptionRepository.saveAndFlush(redemption);

        int databaseSizeBeforeUpdate = redemptionRepository.findAll().size();

        // Update the redemption
        Redemption updatedRedemption = redemptionRepository.findById(redemption.getId()).get();
        // Disconnect from session so that the updates on updatedRedemption are not directly saved in db
        em.detach(updatedRedemption);
        updatedRedemption
            .redemptionCode(UPDATED_REDEMPTION_CODE)
            .redemptionDate(UPDATED_REDEMPTION_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .unitSellPriceInd(UPDATED_UNIT_SELL_PRICE_IND)
            .unitSellPrice(UPDATED_UNIT_SELL_PRICE)
            .unitSellUnit(UPDATED_UNIT_SELL_UNIT)
            .unitSellLot(UPDATED_UNIT_SELL_LOT)
            .unitSellBasket(UPDATED_UNIT_SELL_BASKET)
            .market(UPDATED_MARKET)
            .sellGrossAmount(UPDATED_SELL_GROSS_AMOUNT)
            .fee(UPDATED_FEE)
            .sellNetAmount(UPDATED_SELL_NET_AMOUNT)
            .statusCash(UPDATED_STATUS_CASH)
            .statusEtf(UPDATED_STATUS_ETF)
            .status(UPDATED_STATUS)
            .channel(UPDATED_CHANNEL);
        RedemptionDTO redemptionDTO = redemptionMapper.toDto(updatedRedemption);

        restRedemptionMockMvc.perform(put("/api/redemptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(redemptionDTO)))
            .andExpect(status().isOk());

        // Validate the Redemption in the database
        List<Redemption> redemptionList = redemptionRepository.findAll();
        assertThat(redemptionList).hasSize(databaseSizeBeforeUpdate);
        Redemption testRedemption = redemptionList.get(redemptionList.size() - 1);
        assertThat(testRedemption.getRedemptionCode()).isEqualTo(UPDATED_REDEMPTION_CODE);
        assertThat(testRedemption.getRedemptionDate()).isEqualTo(UPDATED_REDEMPTION_DATE);
        assertThat(testRedemption.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testRedemption.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testRedemption.getUnitSellPriceInd()).isEqualTo(UPDATED_UNIT_SELL_PRICE_IND);
        assertThat(testRedemption.getUnitSellPrice()).isEqualTo(UPDATED_UNIT_SELL_PRICE);
        assertThat(testRedemption.getUnitSellUnit()).isEqualTo(UPDATED_UNIT_SELL_UNIT);
        assertThat(testRedemption.getUnitSellLot()).isEqualTo(UPDATED_UNIT_SELL_LOT);
        assertThat(testRedemption.getUnitSellBasket()).isEqualTo(UPDATED_UNIT_SELL_BASKET);
        assertThat(testRedemption.getMarket()).isEqualTo(UPDATED_MARKET);
        assertThat(testRedemption.getSellGrossAmount()).isEqualTo(UPDATED_SELL_GROSS_AMOUNT);
        assertThat(testRedemption.getFee()).isEqualTo(UPDATED_FEE);
        assertThat(testRedemption.getSellNetAmount()).isEqualTo(UPDATED_SELL_NET_AMOUNT);
        assertThat(testRedemption.getStatusCash()).isEqualTo(UPDATED_STATUS_CASH);
        assertThat(testRedemption.getStatusEtf()).isEqualTo(UPDATED_STATUS_ETF);
        assertThat(testRedemption.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRedemption.getChannel()).isEqualTo(UPDATED_CHANNEL);
    }

    @Test
    @Transactional
    public void updateNonExistingRedemption() throws Exception {
        int databaseSizeBeforeUpdate = redemptionRepository.findAll().size();

        // Create the Redemption
        RedemptionDTO redemptionDTO = redemptionMapper.toDto(redemption);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRedemptionMockMvc.perform(put("/api/redemptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(redemptionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Redemption in the database
        List<Redemption> redemptionList = redemptionRepository.findAll();
        assertThat(redemptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRedemption() throws Exception {
        // Initialize the database
        redemptionRepository.saveAndFlush(redemption);

        int databaseSizeBeforeDelete = redemptionRepository.findAll().size();

        // Delete the redemption
        restRedemptionMockMvc.perform(delete("/api/redemptions/{id}", redemption.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Redemption> redemptionList = redemptionRepository.findAll();
        assertThat(redemptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Redemption.class);
        Redemption redemption1 = new Redemption();
        redemption1.setId(1L);
        Redemption redemption2 = new Redemption();
        redemption2.setId(redemption1.getId());
        assertThat(redemption1).isEqualTo(redemption2);
        redemption2.setId(2L);
        assertThat(redemption1).isNotEqualTo(redemption2);
        redemption1.setId(null);
        assertThat(redemption1).isNotEqualTo(redemption2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RedemptionDTO.class);
        RedemptionDTO redemptionDTO1 = new RedemptionDTO();
        redemptionDTO1.setId(1L);
        RedemptionDTO redemptionDTO2 = new RedemptionDTO();
        assertThat(redemptionDTO1).isNotEqualTo(redemptionDTO2);
        redemptionDTO2.setId(redemptionDTO1.getId());
        assertThat(redemptionDTO1).isEqualTo(redemptionDTO2);
        redemptionDTO2.setId(2L);
        assertThat(redemptionDTO1).isNotEqualTo(redemptionDTO2);
        redemptionDTO1.setId(null);
        assertThat(redemptionDTO1).isNotEqualTo(redemptionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(redemptionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(redemptionMapper.fromId(null)).isNull();
    }
}
