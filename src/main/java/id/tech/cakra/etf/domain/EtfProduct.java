package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EtfProduct.
 */
@Entity
@Table(name = "etf_product")
public class EtfProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @NotNull
    @Size(max = 10)
    @Column(name = "product_code", length = 10, nullable = false)
    private String productCode;

    @Size(max = 50)
    @Column(name = "product_name", length = 50)
    private String productName;

    @Size(max = 10)
    @Column(name = "fund_category", length = 10)
    private String fundCategory;

    @Column(name = "inception_date")
    private LocalDate inceptionDate;

    @Size(max = 10)
    @Column(name = "clearing_house", length = 10)
    private String clearingHouse;

    @Size(max = 10)
    @Column(name = "exchage", length = 10)
    private String exchage;

    @Min(value = 0)
    @Column(name = "unit_creation")
    private Integer unitCreation;

    @Column(name = "unit_price_initial")
    private Float unitPriceInitial;

    @Column(name = "unit_price_curr")
    private Float unitPriceCurr;

    @Column(name = "lot_per_basket")
    private Float lotPerBasket;

    @Size(max = 10)
    @Column(name = "index_reff", length = 10)
    private String indexReff;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "fee")
    private Float fee;

    @Column(name = "mi_id")
    private Integer miId;

    @Column(name = "custody_id")
    private Integer custodyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public EtfProduct productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public EtfProduct productCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public EtfProduct productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public EtfProduct fundCategory(String fundCategory) {
        this.fundCategory = fundCategory;
        return this;
    }

    public void setFundCategory(String fundCategory) {
        this.fundCategory = fundCategory;
    }

    public LocalDate getInceptionDate() {
        return inceptionDate;
    }

    public EtfProduct inceptionDate(LocalDate inceptionDate) {
        this.inceptionDate = inceptionDate;
        return this;
    }

    public void setInceptionDate(LocalDate inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public String getClearingHouse() {
        return clearingHouse;
    }

    public EtfProduct clearingHouse(String clearingHouse) {
        this.clearingHouse = clearingHouse;
        return this;
    }

    public void setClearingHouse(String clearingHouse) {
        this.clearingHouse = clearingHouse;
    }

    public String getExchage() {
        return exchage;
    }

    public EtfProduct exchage(String exchage) {
        this.exchage = exchage;
        return this;
    }

    public void setExchage(String exchage) {
        this.exchage = exchage;
    }

    public Integer getUnitCreation() {
        return unitCreation;
    }

    public EtfProduct unitCreation(Integer unitCreation) {
        this.unitCreation = unitCreation;
        return this;
    }

    public void setUnitCreation(Integer unitCreation) {
        this.unitCreation = unitCreation;
    }

    public Float getUnitPriceInitial() {
        return unitPriceInitial;
    }

    public EtfProduct unitPriceInitial(Float unitPriceInitial) {
        this.unitPriceInitial = unitPriceInitial;
        return this;
    }

    public void setUnitPriceInitial(Float unitPriceInitial) {
        this.unitPriceInitial = unitPriceInitial;
    }

    public Float getUnitPriceCurr() {
        return unitPriceCurr;
    }

    public EtfProduct unitPriceCurr(Float unitPriceCurr) {
        this.unitPriceCurr = unitPriceCurr;
        return this;
    }

    public void setUnitPriceCurr(Float unitPriceCurr) {
        this.unitPriceCurr = unitPriceCurr;
    }

    public Float getLotPerBasket() {
        return lotPerBasket;
    }

    public EtfProduct lotPerBasket(Float lotPerBasket) {
        this.lotPerBasket = lotPerBasket;
        return this;
    }

    public void setLotPerBasket(Float lotPerBasket) {
        this.lotPerBasket = lotPerBasket;
    }

    public String getIndexReff() {
        return indexReff;
    }

    public EtfProduct indexReff(String indexReff) {
        this.indexReff = indexReff;
        return this;
    }

    public void setIndexReff(String indexReff) {
        this.indexReff = indexReff;
    }

    public Float getRating() {
        return rating;
    }

    public EtfProduct rating(Float rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getFee() {
        return fee;
    }

    public EtfProduct fee(Float fee) {
        this.fee = fee;
        return this;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Integer getMiId() {
        return miId;
    }

    public EtfProduct miId(Integer miId) {
        this.miId = miId;
        return this;
    }

    public void setMiId(Integer miId) {
        this.miId = miId;
    }

    public Integer getCustodyId() {
        return custodyId;
    }

    public EtfProduct custodyId(Integer custodyId) {
        this.custodyId = custodyId;
        return this;
    }

    public void setCustodyId(Integer custodyId) {
        this.custodyId = custodyId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfProduct)) {
            return false;
        }
        return id != null && id.equals(((EtfProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfProduct{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", productName='" + getProductName() + "'" +
            ", fundCategory='" + getFundCategory() + "'" +
            ", inceptionDate='" + getInceptionDate() + "'" +
            ", clearingHouse='" + getClearingHouse() + "'" +
            ", exchage='" + getExchage() + "'" +
            ", unitCreation=" + getUnitCreation() +
            ", unitPriceInitial=" + getUnitPriceInitial() +
            ", unitPriceCurr=" + getUnitPriceCurr() +
            ", lotPerBasket=" + getLotPerBasket() +
            ", indexReff='" + getIndexReff() + "'" +
            ", rating=" + getRating() +
            ", fee=" + getFee() +
            ", miId=" + getMiId() +
            ", custodyId=" + getCustodyId() +
            "}";
    }
}
