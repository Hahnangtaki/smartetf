package id.tech.cakra.etf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Size(max = 10)
    @Column(name = "product_code", length = 10, nullable = false, unique = true)
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

    @OneToMany(mappedBy = "etfProduct")
    private Set<EtfUnderlying> etfUnderlyings = new HashSet<>();

    @OneToMany(mappedBy = "etfProduct")
    private Set<EtfHistory> etfHistories = new HashSet<>();

    @OneToMany(mappedBy = "etfProduct")
    private Set<Subscript> subscripts = new HashSet<>();

    @OneToMany(mappedBy = "etfProduct")
    private Set<Redemption> redemptions = new HashSet<>();

    @OneToMany(mappedBy = "etfProduct")
    private Set<Portofolio> portofolios = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "etf_product_dealer_participant",
               joinColumns = @JoinColumn(name = "etf_product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "dealer_participant_id", referencedColumnName = "id"))
    private Set<DealerParticipant> dealerParticipants = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("etfProducts")
    private Mi mi;

    @ManyToOne
    @JsonIgnoreProperties("etfProducts")
    private BankCustody bankCustody;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<EtfUnderlying> getEtfUnderlyings() {
        return etfUnderlyings;
    }

    public EtfProduct etfUnderlyings(Set<EtfUnderlying> etfUnderlyings) {
        this.etfUnderlyings = etfUnderlyings;
        return this;
    }

    public EtfProduct addEtfUnderlying(EtfUnderlying etfUnderlying) {
        this.etfUnderlyings.add(etfUnderlying);
        etfUnderlying.setEtfProduct(this);
        return this;
    }

    public EtfProduct removeEtfUnderlying(EtfUnderlying etfUnderlying) {
        this.etfUnderlyings.remove(etfUnderlying);
        etfUnderlying.setEtfProduct(null);
        return this;
    }

    public void setEtfUnderlyings(Set<EtfUnderlying> etfUnderlyings) {
        this.etfUnderlyings = etfUnderlyings;
    }

    public Set<EtfHistory> getEtfHistories() {
        return etfHistories;
    }

    public EtfProduct etfHistories(Set<EtfHistory> etfHistories) {
        this.etfHistories = etfHistories;
        return this;
    }

    public EtfProduct addEtfHistory(EtfHistory etfHistory) {
        this.etfHistories.add(etfHistory);
        etfHistory.setEtfProduct(this);
        return this;
    }

    public EtfProduct removeEtfHistory(EtfHistory etfHistory) {
        this.etfHistories.remove(etfHistory);
        etfHistory.setEtfProduct(null);
        return this;
    }

    public void setEtfHistories(Set<EtfHistory> etfHistories) {
        this.etfHistories = etfHistories;
    }

    public Set<Subscript> getSubscripts() {
        return subscripts;
    }

    public EtfProduct subscripts(Set<Subscript> subscripts) {
        this.subscripts = subscripts;
        return this;
    }

    public EtfProduct addSubscript(Subscript subscript) {
        this.subscripts.add(subscript);
        subscript.setEtfProduct(this);
        return this;
    }

    public EtfProduct removeSubscript(Subscript subscript) {
        this.subscripts.remove(subscript);
        subscript.setEtfProduct(null);
        return this;
    }

    public void setSubscripts(Set<Subscript> subscripts) {
        this.subscripts = subscripts;
    }

    public Set<Redemption> getRedemptions() {
        return redemptions;
    }

    public EtfProduct redemptions(Set<Redemption> redemptions) {
        this.redemptions = redemptions;
        return this;
    }

    public EtfProduct addRedemption(Redemption redemption) {
        this.redemptions.add(redemption);
        redemption.setEtfProduct(this);
        return this;
    }

    public EtfProduct removeRedemption(Redemption redemption) {
        this.redemptions.remove(redemption);
        redemption.setEtfProduct(null);
        return this;
    }

    public void setRedemptions(Set<Redemption> redemptions) {
        this.redemptions = redemptions;
    }

    public Set<Portofolio> getPortofolios() {
        return portofolios;
    }

    public EtfProduct portofolios(Set<Portofolio> portofolios) {
        this.portofolios = portofolios;
        return this;
    }

    public EtfProduct addPortofolio(Portofolio portofolio) {
        this.portofolios.add(portofolio);
        portofolio.setEtfProduct(this);
        return this;
    }

    public EtfProduct removePortofolio(Portofolio portofolio) {
        this.portofolios.remove(portofolio);
        portofolio.setEtfProduct(null);
        return this;
    }

    public void setPortofolios(Set<Portofolio> portofolios) {
        this.portofolios = portofolios;
    }

    public Set<DealerParticipant> getDealerParticipants() {
        return dealerParticipants;
    }

    public EtfProduct dealerParticipants(Set<DealerParticipant> dealerParticipants) {
        this.dealerParticipants = dealerParticipants;
        return this;
    }

    public EtfProduct addDealerParticipant(DealerParticipant dealerParticipant) {
        this.dealerParticipants.add(dealerParticipant);
        dealerParticipant.getEtfProducts().add(this);
        return this;
    }

    public EtfProduct removeDealerParticipant(DealerParticipant dealerParticipant) {
        this.dealerParticipants.remove(dealerParticipant);
        dealerParticipant.getEtfProducts().remove(this);
        return this;
    }

    public void setDealerParticipants(Set<DealerParticipant> dealerParticipants) {
        this.dealerParticipants = dealerParticipants;
    }

    public Mi getMi() {
        return mi;
    }

    public EtfProduct mi(Mi mi) {
        this.mi = mi;
        return this;
    }

    public void setMi(Mi mi) {
        this.mi = mi;
    }

    public BankCustody getBankCustody() {
        return bankCustody;
    }

    public EtfProduct bankCustody(BankCustody bankCustody) {
        this.bankCustody = bankCustody;
        return this;
    }

    public void setBankCustody(BankCustody bankCustody) {
        this.bankCustody = bankCustody;
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
            "}";
    }
}
