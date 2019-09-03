package id.tech.cakra.etf.web.rest;

import id.tech.cakra.etf.SmartEtfApp;
import id.tech.cakra.etf.domain.Subscript;
import id.tech.cakra.etf.repository.SubscriptRepository;
import id.tech.cakra.etf.service.SubscriptService;
import id.tech.cakra.etf.service.dto.SubscriptDTO;
import id.tech.cakra.etf.service.mapper.SubscriptMapper;
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
 * Integration tests for the {@link SubscriptResource} REST controller.
 */
@SpringBootTest(classes = SmartEtfApp.class)
public class SubscriptResourceIT {

    private static final Integer DEFAULT_SUBSCRIPT_ID = 1;
    private static final Integer UPDATED_SUBSCRIPT_ID = 2;
    private static final Integer SMALLER_SUBSCRIPT_ID = 1 - 1;

    private static final String DEFAULT_SUBSCRIPT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPT_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SUBSCRIPT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBSCRIPT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_SUBSCRIPT_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_UNIT_BUY_PRICE_IND = 1F;
    private static final Float UPDATED_UNIT_BUY_PRICE_IND = 2F;
    private static final Float SMALLER_UNIT_BUY_PRICE_IND = 1F - 1F;

    private static final Float DEFAULT_UNIT_BUY_PRICE = 1F;
    private static final Float UPDATED_UNIT_BUY_PRICE = 2F;
    private static final Float SMALLER_UNIT_BUY_PRICE = 1F - 1F;

    private static final Float DEFAULT_UNIT_BUY_UNIT = 1F;
    private static final Float UPDATED_UNIT_BUY_UNIT = 2F;
    private static final Float SMALLER_UNIT_BUY_UNIT = 1F - 1F;

    private static final Float DEFAULT_UNIT_BUY_LOT = 1F;
    private static final Float UPDATED_UNIT_BUY_LOT = 2F;
    private static final Float SMALLER_UNIT_BUY_LOT = 1F - 1F;

    private static final Float DEFAULT_UNIT_BUY_BASKET = 1F;
    private static final Float UPDATED_UNIT_BUY_BASKET = 2F;
    private static final Float SMALLER_UNIT_BUY_BASKET = 1F - 1F;

    private static final String DEFAULT_MARKET = "AAAAAAAAAA";
    private static final String UPDATED_MARKET = "BBBBBBBBBB";

    private static final Float DEFAULT_BUY_GROSS_AMOUNT = 1F;
    private static final Float UPDATED_BUY_GROSS_AMOUNT = 2F;
    private static final Float SMALLER_BUY_GROSS_AMOUNT = 1F - 1F;

    private static final Float DEFAULT_FEE = 1F;
    private static final Float UPDATED_FEE = 2F;
    private static final Float SMALLER_FEE = 1F - 1F;

    private static final Float DEFAULT_BUY_NET_AMOUNT = 1F;
    private static final Float UPDATED_BUY_NET_AMOUNT = 2F;
    private static final Float SMALLER_BUY_NET_AMOUNT = 1F - 1F;

    private static final String DEFAULT_STATUS_CASH = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CASH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_ETF = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_ETF = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHANNEL = 1;
    private static final Integer UPDATED_CHANNEL = 2;
    private static final Integer SMALLER_CHANNEL = 1 - 1;

    private static final Integer DEFAULT_DEALER_ID = 1;
    private static final Integer UPDATED_DEALER_ID = 2;
    private static final Integer SMALLER_DEALER_ID = 1 - 1;

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;
    private static final Integer SMALLER_PRODUCT_ID = 1 - 1;

    private static final Integer DEFAULT_UNDERLYING_ID = 1;
    private static final Integer UPDATED_UNDERLYING_ID = 2;
    private static final Integer SMALLER_UNDERLYING_ID = 1 - 1;

    @Autowired
    private SubscriptRepository subscriptRepository;

    @Autowired
    private SubscriptMapper subscriptMapper;

    @Autowired
    private SubscriptService subscriptService;

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

    private MockMvc restSubscriptMockMvc;

    private Subscript subscript;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubscriptResource subscriptResource = new SubscriptResource(subscriptService);
        this.restSubscriptMockMvc = MockMvcBuilders.standaloneSetup(subscriptResource)
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
    public static Subscript createEntity(EntityManager em) {
        Subscript subscript = new Subscript()
            .subscriptId(DEFAULT_SUBSCRIPT_ID)
            .subscriptCode(DEFAULT_SUBSCRIPT_CODE)
            .subscriptDate(DEFAULT_SUBSCRIPT_DATE)
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .unitBuyPriceInd(DEFAULT_UNIT_BUY_PRICE_IND)
            .unitBuyPrice(DEFAULT_UNIT_BUY_PRICE)
            .unitBuyUnit(DEFAULT_UNIT_BUY_UNIT)
            .unitBuyLot(DEFAULT_UNIT_BUY_LOT)
            .unitBuyBasket(DEFAULT_UNIT_BUY_BASKET)
            .market(DEFAULT_MARKET)
            .buyGrossAmount(DEFAULT_BUY_GROSS_AMOUNT)
            .fee(DEFAULT_FEE)
            .buyNetAmount(DEFAULT_BUY_NET_AMOUNT)
            .statusCash(DEFAULT_STATUS_CASH)
            .statusEtf(DEFAULT_STATUS_ETF)
            .status(DEFAULT_STATUS)
            .channel(DEFAULT_CHANNEL)
            .dealerId(DEFAULT_DEALER_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .underlyingId(DEFAULT_UNDERLYING_ID);
        return subscript;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subscript createUpdatedEntity(EntityManager em) {
        Subscript subscript = new Subscript()
            .subscriptId(UPDATED_SUBSCRIPT_ID)
            .subscriptCode(UPDATED_SUBSCRIPT_CODE)
            .subscriptDate(UPDATED_SUBSCRIPT_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .unitBuyPriceInd(UPDATED_UNIT_BUY_PRICE_IND)
            .unitBuyPrice(UPDATED_UNIT_BUY_PRICE)
            .unitBuyUnit(UPDATED_UNIT_BUY_UNIT)
            .unitBuyLot(UPDATED_UNIT_BUY_LOT)
            .unitBuyBasket(UPDATED_UNIT_BUY_BASKET)
            .market(UPDATED_MARKET)
            .buyGrossAmount(UPDATED_BUY_GROSS_AMOUNT)
            .fee(UPDATED_FEE)
            .buyNetAmount(UPDATED_BUY_NET_AMOUNT)
            .statusCash(UPDATED_STATUS_CASH)
            .statusEtf(UPDATED_STATUS_ETF)
            .status(UPDATED_STATUS)
            .channel(UPDATED_CHANNEL)
            .dealerId(UPDATED_DEALER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .underlyingId(UPDATED_UNDERLYING_ID);
        return subscript;
    }

    @BeforeEach
    public void initTest() {
        subscript = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubscript() throws Exception {
        int databaseSizeBeforeCreate = subscriptRepository.findAll().size();

        // Create the Subscript
        SubscriptDTO subscriptDTO = subscriptMapper.toDto(subscript);
        restSubscriptMockMvc.perform(post("/api/subscripts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subscriptDTO)))
            .andExpect(status().isCreated());

        // Validate the Subscript in the database
        List<Subscript> subscriptList = subscriptRepository.findAll();
        assertThat(subscriptList).hasSize(databaseSizeBeforeCreate + 1);
        Subscript testSubscript = subscriptList.get(subscriptList.size() - 1);
        assertThat(testSubscript.getSubscriptId()).isEqualTo(DEFAULT_SUBSCRIPT_ID);
        assertThat(testSubscript.getSubscriptCode()).isEqualTo(DEFAULT_SUBSCRIPT_CODE);
        assertThat(testSubscript.getSubscriptDate()).isEqualTo(DEFAULT_SUBSCRIPT_DATE);
        assertThat(testSubscript.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testSubscript.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testSubscript.getUnitBuyPriceInd()).isEqualTo(DEFAULT_UNIT_BUY_PRICE_IND);
        assertThat(testSubscript.getUnitBuyPrice()).isEqualTo(DEFAULT_UNIT_BUY_PRICE);
        assertThat(testSubscript.getUnitBuyUnit()).isEqualTo(DEFAULT_UNIT_BUY_UNIT);
        assertThat(testSubscript.getUnitBuyLot()).isEqualTo(DEFAULT_UNIT_BUY_LOT);
        assertThat(testSubscript.getUnitBuyBasket()).isEqualTo(DEFAULT_UNIT_BUY_BASKET);
        assertThat(testSubscript.getMarket()).isEqualTo(DEFAULT_MARKET);
        assertThat(testSubscript.getBuyGrossAmount()).isEqualTo(DEFAULT_BUY_GROSS_AMOUNT);
        assertThat(testSubscript.getFee()).isEqualTo(DEFAULT_FEE);
        assertThat(testSubscript.getBuyNetAmount()).isEqualTo(DEFAULT_BUY_NET_AMOUNT);
        assertThat(testSubscript.getStatusCash()).isEqualTo(DEFAULT_STATUS_CASH);
        assertThat(testSubscript.getStatusEtf()).isEqualTo(DEFAULT_STATUS_ETF);
        assertThat(testSubscript.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSubscript.getChannel()).isEqualTo(DEFAULT_CHANNEL);
        assertThat(testSubscript.getDealerId()).isEqualTo(DEFAULT_DEALER_ID);
        assertThat(testSubscript.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testSubscript.getUnderlyingId()).isEqualTo(DEFAULT_UNDERLYING_ID);
    }

    @Test
    @Transactional
    public void createSubscriptWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriptRepository.findAll().size();

        // Create the Subscript with an existing ID
        subscript.setId(1L);
        SubscriptDTO subscriptDTO = subscriptMapper.toDto(subscript);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriptMockMvc.perform(post("/api/subscripts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subscriptDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Subscript in the database
        List<Subscript> subscriptList = subscriptRepository.findAll();
        assertThat(subscriptList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSubscriptIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = subscriptRepository.findAll().size();
        // set the field null
        subscript.setSubscriptId(null);

        // Create the Subscript, which fails.
        SubscriptDTO subscriptDTO = subscriptMapper.toDto(subscript);

        restSubscriptMockMvc.perform(post("/api/subscripts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subscriptDTO)))
            .andExpect(status().isBadRequest());

        List<Subscript> subscriptList = subscriptRepository.findAll();
        assertThat(subscriptList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubscripts() throws Exception {
        // Initialize the database
        subscriptRepository.saveAndFlush(subscript);

        // Get all the subscriptList
        restSubscriptMockMvc.perform(get("/api/subscripts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscript.getId().intValue())))
            .andExpect(jsonPath("$.[*].subscriptId").value(hasItem(DEFAULT_SUBSCRIPT_ID)))
            .andExpect(jsonPath("$.[*].subscriptCode").value(hasItem(DEFAULT_SUBSCRIPT_CODE.toString())))
            .andExpect(jsonPath("$.[*].subscriptDate").value(hasItem(DEFAULT_SUBSCRIPT_DATE.toString())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].unitBuyPriceInd").value(hasItem(DEFAULT_UNIT_BUY_PRICE_IND.doubleValue())))
            .andExpect(jsonPath("$.[*].unitBuyPrice").value(hasItem(DEFAULT_UNIT_BUY_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].unitBuyUnit").value(hasItem(DEFAULT_UNIT_BUY_UNIT.doubleValue())))
            .andExpect(jsonPath("$.[*].unitBuyLot").value(hasItem(DEFAULT_UNIT_BUY_LOT.doubleValue())))
            .andExpect(jsonPath("$.[*].unitBuyBasket").value(hasItem(DEFAULT_UNIT_BUY_BASKET.doubleValue())))
            .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET.toString())))
            .andExpect(jsonPath("$.[*].buyGrossAmount").value(hasItem(DEFAULT_BUY_GROSS_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].fee").value(hasItem(DEFAULT_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].buyNetAmount").value(hasItem(DEFAULT_BUY_NET_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].statusCash").value(hasItem(DEFAULT_STATUS_CASH.toString())))
            .andExpect(jsonPath("$.[*].statusEtf").value(hasItem(DEFAULT_STATUS_ETF.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].channel").value(hasItem(DEFAULT_CHANNEL)))
            .andExpect(jsonPath("$.[*].dealerId").value(hasItem(DEFAULT_DEALER_ID)))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].underlyingId").value(hasItem(DEFAULT_UNDERLYING_ID)));
    }
    
    @Test
    @Transactional
    public void getSubscript() throws Exception {
        // Initialize the database
        subscriptRepository.saveAndFlush(subscript);

        // Get the subscript
        restSubscriptMockMvc.perform(get("/api/subscripts/{id}", subscript.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(subscript.getId().intValue()))
            .andExpect(jsonPath("$.subscriptId").value(DEFAULT_SUBSCRIPT_ID))
            .andExpect(jsonPath("$.subscriptCode").value(DEFAULT_SUBSCRIPT_CODE.toString()))
            .andExpect(jsonPath("$.subscriptDate").value(DEFAULT_SUBSCRIPT_DATE.toString()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME.toString()))
            .andExpect(jsonPath("$.unitBuyPriceInd").value(DEFAULT_UNIT_BUY_PRICE_IND.doubleValue()))
            .andExpect(jsonPath("$.unitBuyPrice").value(DEFAULT_UNIT_BUY_PRICE.doubleValue()))
            .andExpect(jsonPath("$.unitBuyUnit").value(DEFAULT_UNIT_BUY_UNIT.doubleValue()))
            .andExpect(jsonPath("$.unitBuyLot").value(DEFAULT_UNIT_BUY_LOT.doubleValue()))
            .andExpect(jsonPath("$.unitBuyBasket").value(DEFAULT_UNIT_BUY_BASKET.doubleValue()))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET.toString()))
            .andExpect(jsonPath("$.buyGrossAmount").value(DEFAULT_BUY_GROSS_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.fee").value(DEFAULT_FEE.doubleValue()))
            .andExpect(jsonPath("$.buyNetAmount").value(DEFAULT_BUY_NET_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.statusCash").value(DEFAULT_STATUS_CASH.toString()))
            .andExpect(jsonPath("$.statusEtf").value(DEFAULT_STATUS_ETF.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.channel").value(DEFAULT_CHANNEL))
            .andExpect(jsonPath("$.dealerId").value(DEFAULT_DEALER_ID))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.underlyingId").value(DEFAULT_UNDERLYING_ID));
    }

    @Test
    @Transactional
    public void getNonExistingSubscript() throws Exception {
        // Get the subscript
        restSubscriptMockMvc.perform(get("/api/subscripts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubscript() throws Exception {
        // Initialize the database
        subscriptRepository.saveAndFlush(subscript);

        int databaseSizeBeforeUpdate = subscriptRepository.findAll().size();

        // Update the subscript
        Subscript updatedSubscript = subscriptRepository.findById(subscript.getId()).get();
        // Disconnect from session so that the updates on updatedSubscript are not directly saved in db
        em.detach(updatedSubscript);
        updatedSubscript
            .subscriptId(UPDATED_SUBSCRIPT_ID)
            .subscriptCode(UPDATED_SUBSCRIPT_CODE)
            .subscriptDate(UPDATED_SUBSCRIPT_DATE)
            .customerId(UPDATED_CUSTOMER_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .unitBuyPriceInd(UPDATED_UNIT_BUY_PRICE_IND)
            .unitBuyPrice(UPDATED_UNIT_BUY_PRICE)
            .unitBuyUnit(UPDATED_UNIT_BUY_UNIT)
            .unitBuyLot(UPDATED_UNIT_BUY_LOT)
            .unitBuyBasket(UPDATED_UNIT_BUY_BASKET)
            .market(UPDATED_MARKET)
            .buyGrossAmount(UPDATED_BUY_GROSS_AMOUNT)
            .fee(UPDATED_FEE)
            .buyNetAmount(UPDATED_BUY_NET_AMOUNT)
            .statusCash(UPDATED_STATUS_CASH)
            .statusEtf(UPDATED_STATUS_ETF)
            .status(UPDATED_STATUS)
            .channel(UPDATED_CHANNEL)
            .dealerId(UPDATED_DEALER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .underlyingId(UPDATED_UNDERLYING_ID);
        SubscriptDTO subscriptDTO = subscriptMapper.toDto(updatedSubscript);

        restSubscriptMockMvc.perform(put("/api/subscripts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subscriptDTO)))
            .andExpect(status().isOk());

        // Validate the Subscript in the database
        List<Subscript> subscriptList = subscriptRepository.findAll();
        assertThat(subscriptList).hasSize(databaseSizeBeforeUpdate);
        Subscript testSubscript = subscriptList.get(subscriptList.size() - 1);
        assertThat(testSubscript.getSubscriptId()).isEqualTo(UPDATED_SUBSCRIPT_ID);
        assertThat(testSubscript.getSubscriptCode()).isEqualTo(UPDATED_SUBSCRIPT_CODE);
        assertThat(testSubscript.getSubscriptDate()).isEqualTo(UPDATED_SUBSCRIPT_DATE);
        assertThat(testSubscript.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testSubscript.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testSubscript.getUnitBuyPriceInd()).isEqualTo(UPDATED_UNIT_BUY_PRICE_IND);
        assertThat(testSubscript.getUnitBuyPrice()).isEqualTo(UPDATED_UNIT_BUY_PRICE);
        assertThat(testSubscript.getUnitBuyUnit()).isEqualTo(UPDATED_UNIT_BUY_UNIT);
        assertThat(testSubscript.getUnitBuyLot()).isEqualTo(UPDATED_UNIT_BUY_LOT);
        assertThat(testSubscript.getUnitBuyBasket()).isEqualTo(UPDATED_UNIT_BUY_BASKET);
        assertThat(testSubscript.getMarket()).isEqualTo(UPDATED_MARKET);
        assertThat(testSubscript.getBuyGrossAmount()).isEqualTo(UPDATED_BUY_GROSS_AMOUNT);
        assertThat(testSubscript.getFee()).isEqualTo(UPDATED_FEE);
        assertThat(testSubscript.getBuyNetAmount()).isEqualTo(UPDATED_BUY_NET_AMOUNT);
        assertThat(testSubscript.getStatusCash()).isEqualTo(UPDATED_STATUS_CASH);
        assertThat(testSubscript.getStatusEtf()).isEqualTo(UPDATED_STATUS_ETF);
        assertThat(testSubscript.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSubscript.getChannel()).isEqualTo(UPDATED_CHANNEL);
        assertThat(testSubscript.getDealerId()).isEqualTo(UPDATED_DEALER_ID);
        assertThat(testSubscript.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testSubscript.getUnderlyingId()).isEqualTo(UPDATED_UNDERLYING_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSubscript() throws Exception {
        int databaseSizeBeforeUpdate = subscriptRepository.findAll().size();

        // Create the Subscript
        SubscriptDTO subscriptDTO = subscriptMapper.toDto(subscript);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriptMockMvc.perform(put("/api/subscripts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subscriptDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Subscript in the database
        List<Subscript> subscriptList = subscriptRepository.findAll();
        assertThat(subscriptList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubscript() throws Exception {
        // Initialize the database
        subscriptRepository.saveAndFlush(subscript);

        int databaseSizeBeforeDelete = subscriptRepository.findAll().size();

        // Delete the subscript
        restSubscriptMockMvc.perform(delete("/api/subscripts/{id}", subscript.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Subscript> subscriptList = subscriptRepository.findAll();
        assertThat(subscriptList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Subscript.class);
        Subscript subscript1 = new Subscript();
        subscript1.setId(1L);
        Subscript subscript2 = new Subscript();
        subscript2.setId(subscript1.getId());
        assertThat(subscript1).isEqualTo(subscript2);
        subscript2.setId(2L);
        assertThat(subscript1).isNotEqualTo(subscript2);
        subscript1.setId(null);
        assertThat(subscript1).isNotEqualTo(subscript2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptDTO.class);
        SubscriptDTO subscriptDTO1 = new SubscriptDTO();
        subscriptDTO1.setId(1L);
        SubscriptDTO subscriptDTO2 = new SubscriptDTO();
        assertThat(subscriptDTO1).isNotEqualTo(subscriptDTO2);
        subscriptDTO2.setId(subscriptDTO1.getId());
        assertThat(subscriptDTO1).isEqualTo(subscriptDTO2);
        subscriptDTO2.setId(2L);
        assertThat(subscriptDTO1).isNotEqualTo(subscriptDTO2);
        subscriptDTO1.setId(null);
        assertThat(subscriptDTO1).isNotEqualTo(subscriptDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(subscriptMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(subscriptMapper.fromId(null)).isNull();
    }
}
