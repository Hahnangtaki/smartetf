package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.Subscript} entity.
 */
public class SubscriptDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer subscriptId;

    @Size(max = 20)
    private String subscriptCode;

    private LocalDate subscriptDate;

    @Size(max = 20)
    private String customerId;

    @Size(max = 200)
    private String customerName;

    private Float unitBuyPriceInd;

    private Float unitBuyPrice;

    private Float unitBuyUnit;

    private Float unitBuyLot;

    private Float unitBuyBasket;

    private String market;

    private Float buyGrossAmount;

    private Float fee;

    private Float buyNetAmount;

    private String statusCash;

    private String statusEtf;

    private String status;

    private Integer channel;

    private Integer dealerId;

    private Integer productId;

    private Integer underlyingId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSubscriptId() {
        return subscriptId;
    }

    public void setSubscriptId(Integer subscriptId) {
        this.subscriptId = subscriptId;
    }

    public String getSubscriptCode() {
        return subscriptCode;
    }

    public void setSubscriptCode(String subscriptCode) {
        this.subscriptCode = subscriptCode;
    }

    public LocalDate getSubscriptDate() {
        return subscriptDate;
    }

    public void setSubscriptDate(LocalDate subscriptDate) {
        this.subscriptDate = subscriptDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Float getUnitBuyPriceInd() {
        return unitBuyPriceInd;
    }

    public void setUnitBuyPriceInd(Float unitBuyPriceInd) {
        this.unitBuyPriceInd = unitBuyPriceInd;
    }

    public Float getUnitBuyPrice() {
        return unitBuyPrice;
    }

    public void setUnitBuyPrice(Float unitBuyPrice) {
        this.unitBuyPrice = unitBuyPrice;
    }

    public Float getUnitBuyUnit() {
        return unitBuyUnit;
    }

    public void setUnitBuyUnit(Float unitBuyUnit) {
        this.unitBuyUnit = unitBuyUnit;
    }

    public Float getUnitBuyLot() {
        return unitBuyLot;
    }

    public void setUnitBuyLot(Float unitBuyLot) {
        this.unitBuyLot = unitBuyLot;
    }

    public Float getUnitBuyBasket() {
        return unitBuyBasket;
    }

    public void setUnitBuyBasket(Float unitBuyBasket) {
        this.unitBuyBasket = unitBuyBasket;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Float getBuyGrossAmount() {
        return buyGrossAmount;
    }

    public void setBuyGrossAmount(Float buyGrossAmount) {
        this.buyGrossAmount = buyGrossAmount;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Float getBuyNetAmount() {
        return buyNetAmount;
    }

    public void setBuyNetAmount(Float buyNetAmount) {
        this.buyNetAmount = buyNetAmount;
    }

    public String getStatusCash() {
        return statusCash;
    }

    public void setStatusCash(String statusCash) {
        this.statusCash = statusCash;
    }

    public String getStatusEtf() {
        return statusEtf;
    }

    public void setStatusEtf(String statusEtf) {
        this.statusEtf = statusEtf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUnderlyingId() {
        return underlyingId;
    }

    public void setUnderlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubscriptDTO subscriptDTO = (SubscriptDTO) o;
        if (subscriptDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subscriptDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubscriptDTO{" +
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
