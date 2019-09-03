package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.Redemption} entity.
 */
public class RedemptionDTO implements Serializable {

    private Long id;

    
    private Integer redemptionId;

    @Size(max = 20)
    private String redemptionCode;

    private LocalDate redemptionDate;

    @Size(max = 20)
    private String customerId;

    @Size(max = 200)
    private String customerName;

    private Float unitSellPriceInd;

    private Float unitSellPrice;

    private Float unitSellUnit;

    private Float unitSellLot;

    private Float unitSellBasket;

    private String market;

    private Float sellGrossAmount;

    private Float fee;

    private Float sellNetAmount;

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

    public Integer getRedemptionId() {
        return redemptionId;
    }

    public void setRedemptionId(Integer redemptionId) {
        this.redemptionId = redemptionId;
    }

    public String getRedemptionCode() {
        return redemptionCode;
    }

    public void setRedemptionCode(String redemptionCode) {
        this.redemptionCode = redemptionCode;
    }

    public LocalDate getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(LocalDate redemptionDate) {
        this.redemptionDate = redemptionDate;
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

    public Float getUnitSellPriceInd() {
        return unitSellPriceInd;
    }

    public void setUnitSellPriceInd(Float unitSellPriceInd) {
        this.unitSellPriceInd = unitSellPriceInd;
    }

    public Float getUnitSellPrice() {
        return unitSellPrice;
    }

    public void setUnitSellPrice(Float unitSellPrice) {
        this.unitSellPrice = unitSellPrice;
    }

    public Float getUnitSellUnit() {
        return unitSellUnit;
    }

    public void setUnitSellUnit(Float unitSellUnit) {
        this.unitSellUnit = unitSellUnit;
    }

    public Float getUnitSellLot() {
        return unitSellLot;
    }

    public void setUnitSellLot(Float unitSellLot) {
        this.unitSellLot = unitSellLot;
    }

    public Float getUnitSellBasket() {
        return unitSellBasket;
    }

    public void setUnitSellBasket(Float unitSellBasket) {
        this.unitSellBasket = unitSellBasket;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Float getSellGrossAmount() {
        return sellGrossAmount;
    }

    public void setSellGrossAmount(Float sellGrossAmount) {
        this.sellGrossAmount = sellGrossAmount;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Float getSellNetAmount() {
        return sellNetAmount;
    }

    public void setSellNetAmount(Float sellNetAmount) {
        this.sellNetAmount = sellNetAmount;
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

        RedemptionDTO redemptionDTO = (RedemptionDTO) o;
        if (redemptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), redemptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RedemptionDTO{" +
            "id=" + getId() +
            ", redemptionId=" + getRedemptionId() +
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
            ", dealerId=" + getDealerId() +
            ", productId=" + getProductId() +
            ", underlyingId=" + getUnderlyingId() +
            "}";
    }
}
