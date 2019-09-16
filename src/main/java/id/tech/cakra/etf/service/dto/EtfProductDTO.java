package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfProduct} entity.
 */
public class EtfProductDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 10)
    private String productCode;

    @Size(max = 50)
    private String productName;

    @Size(max = 10)
    private String fundCategory;

    private LocalDate inceptionDate;

    @Size(max = 10)
    private String clearingHouse;

    @Size(max = 10)
    private String exchage;

    @Min(value = 0)
    private Integer unitCreation;

    private Float unitPriceInitial;

    private Float unitPriceCurr;

    private Float lotPerBasket;

    @Size(max = 10)
    private String indexReff;

    private Float rating;

    private Float fee;


    private Set<DealerParticipantDTO> dealerParticipants = new HashSet<>();

    private Long miId;

    private Long bankCustodyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public void setFundCategory(String fundCategory) {
        this.fundCategory = fundCategory;
    }

    public LocalDate getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(LocalDate inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public String getClearingHouse() {
        return clearingHouse;
    }

    public void setClearingHouse(String clearingHouse) {
        this.clearingHouse = clearingHouse;
    }

    public String getExchage() {
        return exchage;
    }

    public void setExchage(String exchage) {
        this.exchage = exchage;
    }

    public Integer getUnitCreation() {
        return unitCreation;
    }

    public void setUnitCreation(Integer unitCreation) {
        this.unitCreation = unitCreation;
    }

    public Float getUnitPriceInitial() {
        return unitPriceInitial;
    }

    public void setUnitPriceInitial(Float unitPriceInitial) {
        this.unitPriceInitial = unitPriceInitial;
    }

    public Float getUnitPriceCurr() {
        return unitPriceCurr;
    }

    public void setUnitPriceCurr(Float unitPriceCurr) {
        this.unitPriceCurr = unitPriceCurr;
    }

    public Float getLotPerBasket() {
        return lotPerBasket;
    }

    public void setLotPerBasket(Float lotPerBasket) {
        this.lotPerBasket = lotPerBasket;
    }

    public String getIndexReff() {
        return indexReff;
    }

    public void setIndexReff(String indexReff) {
        this.indexReff = indexReff;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public Set<DealerParticipantDTO> getDealerParticipants() {
        return dealerParticipants;
    }

    public void setDealerParticipants(Set<DealerParticipantDTO> dealerParticipants) {
        this.dealerParticipants = dealerParticipants;
    }

    public Long getMiId() {
        return miId;
    }

    public void setMiId(Long miId) {
        this.miId = miId;
    }

    public Long getBankCustodyId() {
        return bankCustodyId;
    }

    public void setBankCustodyId(Long bankCustodyId) {
        this.bankCustodyId = bankCustodyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfProductDTO etfProductDTO = (EtfProductDTO) o;
        if (etfProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfProductDTO{" +
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
            ", mi=" + getMiId() +
            ", bankCustody=" + getBankCustodyId() +
            "}";
    }
}
