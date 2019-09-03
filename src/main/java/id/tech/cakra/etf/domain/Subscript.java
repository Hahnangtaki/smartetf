package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Subscript.
 */
@Entity
@Table(name = "subscript")
public class Subscript implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "subscript_id", nullable = false, unique = true)
    private Integer subscriptId;

    @Size(max = 20)
    @Column(name = "subscript_code", length = 20)
    private String subscriptCode;

    @Column(name = "subscript_date")
    private LocalDate subscriptDate;

    @Size(max = 20)
    @Column(name = "customer_id", length = 20)
    private String customerId;

    @Size(max = 200)
    @Column(name = "customer_name", length = 200)
    private String customerName;

    @Column(name = "unit_buy_price_ind")
    private Float unitBuyPriceInd;

    @Column(name = "unit_buy_price")
    private Float unitBuyPrice;

    @Column(name = "unit_buy_unit")
    private Float unitBuyUnit;

    @Column(name = "unit_buy_lot")
    private Float unitBuyLot;

    @Column(name = "unit_buy_basket")
    private Float unitBuyBasket;

    @Column(name = "market")
    private String market;

    @Column(name = "buy_gross_amount")
    private Float buyGrossAmount;

    @Column(name = "fee")
    private Float fee;

    @Column(name = "buy_net_amount")
    private Float buyNetAmount;

    @Column(name = "status_cash")
    private String statusCash;

    @Column(name = "status_etf")
    private String statusEtf;

    @Column(name = "status")
    private String status;

    @Column(name = "channel")
    private Integer channel;

    @Column(name = "dealer_id")
    private Integer dealerId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "underlying_id")
    private Integer underlyingId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSubscriptId() {
        return subscriptId;
    }

    public Subscript subscriptId(Integer subscriptId) {
        this.subscriptId = subscriptId;
        return this;
    }

    public void setSubscriptId(Integer subscriptId) {
        this.subscriptId = subscriptId;
    }

    public String getSubscriptCode() {
        return subscriptCode;
    }

    public Subscript subscriptCode(String subscriptCode) {
        this.subscriptCode = subscriptCode;
        return this;
    }

    public void setSubscriptCode(String subscriptCode) {
        this.subscriptCode = subscriptCode;
    }

    public LocalDate getSubscriptDate() {
        return subscriptDate;
    }

    public Subscript subscriptDate(LocalDate subscriptDate) {
        this.subscriptDate = subscriptDate;
        return this;
    }

    public void setSubscriptDate(LocalDate subscriptDate) {
        this.subscriptDate = subscriptDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Subscript customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Subscript customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getUnitBuyPriceInd() {
        return unitBuyPriceInd;
    }

    public Subscript unitBuyPriceInd(Float unitBuyPriceInd) {
        this.unitBuyPriceInd = unitBuyPriceInd;
        return this;
    }

    public void setUnitBuyPriceInd(Float unitBuyPriceInd) {
        this.unitBuyPriceInd = unitBuyPriceInd;
    }

    public Float getUnitBuyPrice() {
        return unitBuyPrice;
    }

    public Subscript unitBuyPrice(Float unitBuyPrice) {
        this.unitBuyPrice = unitBuyPrice;
        return this;
    }

    public void setUnitBuyPrice(Float unitBuyPrice) {
        this.unitBuyPrice = unitBuyPrice;
    }

    public Float getUnitBuyUnit() {
        return unitBuyUnit;
    }

    public Subscript unitBuyUnit(Float unitBuyUnit) {
        this.unitBuyUnit = unitBuyUnit;
        return this;
    }

    public void setUnitBuyUnit(Float unitBuyUnit) {
        this.unitBuyUnit = unitBuyUnit;
    }

    public Float getUnitBuyLot() {
        return unitBuyLot;
    }

    public Subscript unitBuyLot(Float unitBuyLot) {
        this.unitBuyLot = unitBuyLot;
        return this;
    }

    public void setUnitBuyLot(Float unitBuyLot) {
        this.unitBuyLot = unitBuyLot;
    }

    public Float getUnitBuyBasket() {
        return unitBuyBasket;
    }

    public Subscript unitBuyBasket(Float unitBuyBasket) {
        this.unitBuyBasket = unitBuyBasket;
        return this;
    }

    public void setUnitBuyBasket(Float unitBuyBasket) {
        this.unitBuyBasket = unitBuyBasket;
    }

    public String getMarket() {
        return market;
    }

    public Subscript market(String market) {
        this.market = market;
        return this;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Float getBuyGrossAmount() {
        return buyGrossAmount;
    }

    public Subscript buyGrossAmount(Float buyGrossAmount) {
        this.buyGrossAmount = buyGrossAmount;
        return this;
    }

    public void setBuyGrossAmount(Float buyGrossAmount) {
        this.buyGrossAmount = buyGrossAmount;
    }

    public Float getFee() {
        return fee;
    }

    public Subscript fee(Float fee) {
        this.fee = fee;
        return this;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Float getBuyNetAmount() {
        return buyNetAmount;
    }

    public Subscript buyNetAmount(Float buyNetAmount) {
        this.buyNetAmount = buyNetAmount;
        return this;
    }

    public void setBuyNetAmount(Float buyNetAmount) {
        this.buyNetAmount = buyNetAmount;
    }

    public String getStatusCash() {
        return statusCash;
    }

    public Subscript statusCash(String statusCash) {
        this.statusCash = statusCash;
        return this;
    }

    public void setStatusCash(String statusCash) {
        this.statusCash = statusCash;
    }

    public String getStatusEtf() {
        return statusEtf;
    }

    public Subscript statusEtf(String statusEtf) {
        this.statusEtf = statusEtf;
        return this;
    }

    public void setStatusEtf(String statusEtf) {
        this.statusEtf = statusEtf;
    }

    public String getStatus() {
        return status;
    }

    public Subscript status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getChannel() {
        return channel;
    }

    public Subscript channel(Integer channel) {
        this.channel = channel;
        return this;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public Subscript dealerId(Integer dealerId) {
        this.dealerId = dealerId;
        return this;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Subscript productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUnderlyingId() {
        return underlyingId;
    }

    public Subscript underlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
        return this;
    }

    public void setUnderlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subscript)) {
            return false;
        }
        return id != null && id.equals(((Subscript) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Subscript{" +
            "id=" + getId() +
            ", subscriptId=" + getSubscriptId() +
            ", subscriptCode='" + getSubscriptCode() + "'" +
            ", subscriptDate='" + getSubscriptDate() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", unitBuyPriceInd=" + getUnitBuyPriceInd() +
            ", unitBuyPrice=" + getUnitBuyPrice() +
            ", unitBuyUnit=" + getUnitBuyUnit() +
            ", unitBuyLot=" + getUnitBuyLot() +
            ", unitBuyBasket=" + getUnitBuyBasket() +
            ", market='" + getMarket() + "'" +
            ", buyGrossAmount=" + getBuyGrossAmount() +
            ", fee=" + getFee() +
            ", buyNetAmount=" + getBuyNetAmount() +
            ", statusCash='" + getStatusCash() + "'" +
            ", statusEtf='" + getStatusEtf() + "'" +
            ", status='" + getStatus() + "'" +
            ", channel=" + getChannel() +
            ", dealerId=" + getDealerId() +
            ", productId=" + getProductId() +
            ", underlyingId=" + getUnderlyingId() +
            "}";
    }
}
