package id.tech.cakra.etf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Redemption.
 */
@Entity
@Table(name = "redemption")
public class Redemption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 20)
    @Column(name = "redemption_code", length = 20, unique = true)
    private String redemptionCode;

    @Column(name = "redemption_date")
    private LocalDate redemptionDate;

    @Size(max = 20)
    @Column(name = "customer_id", length = 20)
    private String customerId;

    @Size(max = 200)
    @Column(name = "customer_name", length = 200)
    private String customerName;

    @Column(name = "unit_sell_price_ind")
    private Float unitSellPriceInd;

    @Column(name = "unit_sell_price")
    private Float unitSellPrice;

    @Column(name = "unit_sell_unit")
    private Float unitSellUnit;

    @Column(name = "unit_sell_lot")
    private Float unitSellLot;

    @Column(name = "unit_sell_basket")
    private Float unitSellBasket;

    @Column(name = "market")
    private String market;

    @Column(name = "sell_gross_amount")
    private Float sellGrossAmount;

    @Column(name = "fee")
    private Float fee;

    @Column(name = "sell_net_amount")
    private Float sellNetAmount;

    @Column(name = "status_cash")
    private String statusCash;

    @Column(name = "status_etf")
    private String statusEtf;

    @Column(name = "status")
    private String status;

    @Column(name = "channel")
    private Integer channel;

    @ManyToOne
    @JsonIgnoreProperties("redemptions")
    private EtfProduct etfProduct;

    @ManyToOne
    @JsonIgnoreProperties("redemptions")
    private DealerParticipant dealerParticipant;

    @ManyToOne
    @JsonIgnoreProperties("redemptions")
    private EtfExecutionDtl etfExecutionDtl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRedemptionCode() {
        return redemptionCode;
    }

    public Redemption redemptionCode(String redemptionCode) {
        this.redemptionCode = redemptionCode;
        return this;
    }

    public void setRedemptionCode(String redemptionCode) {
        this.redemptionCode = redemptionCode;
    }

    public LocalDate getRedemptionDate() {
        return redemptionDate;
    }

    public Redemption redemptionDate(LocalDate redemptionDate) {
        this.redemptionDate = redemptionDate;
        return this;
    }

    public void setRedemptionDate(LocalDate redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Redemption customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Redemption customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getUnitSellPriceInd() {
        return unitSellPriceInd;
    }

    public Redemption unitSellPriceInd(Float unitSellPriceInd) {
        this.unitSellPriceInd = unitSellPriceInd;
        return this;
    }

    public void setUnitSellPriceInd(Float unitSellPriceInd) {
        this.unitSellPriceInd = unitSellPriceInd;
    }

    public Float getUnitSellPrice() {
        return unitSellPrice;
    }

    public Redemption unitSellPrice(Float unitSellPrice) {
        this.unitSellPrice = unitSellPrice;
        return this;
    }

    public void setUnitSellPrice(Float unitSellPrice) {
        this.unitSellPrice = unitSellPrice;
    }

    public Float getUnitSellUnit() {
        return unitSellUnit;
    }

    public Redemption unitSellUnit(Float unitSellUnit) {
        this.unitSellUnit = unitSellUnit;
        return this;
    }

    public void setUnitSellUnit(Float unitSellUnit) {
        this.unitSellUnit = unitSellUnit;
    }

    public Float getUnitSellLot() {
        return unitSellLot;
    }

    public Redemption unitSellLot(Float unitSellLot) {
        this.unitSellLot = unitSellLot;
        return this;
    }

    public void setUnitSellLot(Float unitSellLot) {
        this.unitSellLot = unitSellLot;
    }

    public Float getUnitSellBasket() {
        return unitSellBasket;
    }

    public Redemption unitSellBasket(Float unitSellBasket) {
        this.unitSellBasket = unitSellBasket;
        return this;
    }

    public void setUnitSellBasket(Float unitSellBasket) {
        this.unitSellBasket = unitSellBasket;
    }

    public String getMarket() {
        return market;
    }

    public Redemption market(String market) {
        this.market = market;
        return this;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Float getSellGrossAmount() {
        return sellGrossAmount;
    }

    public Redemption sellGrossAmount(Float sellGrossAmount) {
        this.sellGrossAmount = sellGrossAmount;
        return this;
    }

    public void setSellGrossAmount(Float sellGrossAmount) {
        this.sellGrossAmount = sellGrossAmount;
    }

    public Float getFee() {
        return fee;
    }

    public Redemption fee(Float fee) {
        this.fee = fee;
        return this;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Float getSellNetAmount() {
        return sellNetAmount;
    }

    public Redemption sellNetAmount(Float sellNetAmount) {
        this.sellNetAmount = sellNetAmount;
        return this;
    }

    public void setSellNetAmount(Float sellNetAmount) {
        this.sellNetAmount = sellNetAmount;
    }

    public String getStatusCash() {
        return statusCash;
    }

    public Redemption statusCash(String statusCash) {
        this.statusCash = statusCash;
        return this;
    }

    public void setStatusCash(String statusCash) {
        this.statusCash = statusCash;
    }

    public String getStatusEtf() {
        return statusEtf;
    }

    public Redemption statusEtf(String statusEtf) {
        this.statusEtf = statusEtf;
        return this;
    }

    public void setStatusEtf(String statusEtf) {
        this.statusEtf = statusEtf;
    }

    public String getStatus() {
        return status;
    }

    public Redemption status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getChannel() {
        return channel;
    }

    public Redemption channel(Integer channel) {
        this.channel = channel;
        return this;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public EtfProduct getEtfProduct() {
        return etfProduct;
    }

    public Redemption etfProduct(EtfProduct etfProduct) {
        this.etfProduct = etfProduct;
        return this;
    }

    public void setEtfProduct(EtfProduct etfProduct) {
        this.etfProduct = etfProduct;
    }

    public DealerParticipant getDealerParticipant() {
        return dealerParticipant;
    }

    public Redemption dealerParticipant(DealerParticipant dealerParticipant) {
        this.dealerParticipant = dealerParticipant;
        return this;
    }

    public void setDealerParticipant(DealerParticipant dealerParticipant) {
        this.dealerParticipant = dealerParticipant;
    }

    public EtfExecutionDtl getEtfExecutionDtl() {
        return etfExecutionDtl;
    }

    public Redemption etfExecutionDtl(EtfExecutionDtl etfExecutionDtl) {
        this.etfExecutionDtl = etfExecutionDtl;
        return this;
    }

    public void setEtfExecutionDtl(EtfExecutionDtl etfExecutionDtl) {
        this.etfExecutionDtl = etfExecutionDtl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Redemption)) {
            return false;
        }
        return id != null && id.equals(((Redemption) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Redemption{" +
            "id=" + getId() +
            ", redemptionCode='" + getRedemptionCode() + "'" +
            ", redemptionDate='" + getRedemptionDate() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", unitSellPriceInd=" + getUnitSellPriceInd() +
            ", unitSellPrice=" + getUnitSellPrice() +
            ", unitSellUnit=" + getUnitSellUnit() +
            ", unitSellLot=" + getUnitSellLot() +
            ", unitSellBasket=" + getUnitSellBasket() +
            ", market='" + getMarket() + "'" +
            ", sellGrossAmount=" + getSellGrossAmount() +
            ", fee=" + getFee() +
            ", sellNetAmount=" + getSellNetAmount() +
            ", statusCash='" + getStatusCash() + "'" +
            ", statusEtf='" + getStatusEtf() + "'" +
            ", status='" + getStatus() + "'" +
            ", channel=" + getChannel() +
            "}";
    }
}
