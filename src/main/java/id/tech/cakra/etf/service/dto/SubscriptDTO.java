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


    private Long etfProductId;

    private Long dealerParticipantId;

    private Long etfExecutionDtlId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEtfProductId() {
        return etfProductId;
    }

    public void setEtfProductId(Long etfProductId) {
        this.etfProductId = etfProductId;
    }

    public Long getDealerParticipantId() {
        return dealerParticipantId;
    }

    public void setDealerParticipantId(Long dealerParticipantId) {
        this.dealerParticipantId = dealerParticipantId;
    }

    public Long getEtfExecutionDtlId() {
        return etfExecutionDtlId;
    }

    public void setEtfExecutionDtlId(Long etfExecutionDtlId) {
        this.etfExecutionDtlId = etfExecutionDtlId;
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
            ", etfProduct=" + getEtfProductId() +
            ", dealerParticipant=" + getDealerParticipantId() +
            ", etfExecutionDtl=" + getEtfExecutionDtlId() +
            "}";
    }
}
