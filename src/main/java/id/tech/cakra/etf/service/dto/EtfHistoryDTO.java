package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfHistory} entity.
 */
public class EtfHistoryDTO implements Serializable {

    private Long id;

    private LocalDate valueDate;

    private Float unitPrice;

    private Float unitQty;

    private Float aum;


    private Long etfProductId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(Float unitQty) {
        this.unitQty = unitQty;
    }

    public Float getAum() {
        return aum;
    }

    public void setAum(Float aum) {
        this.aum = aum;
    }

    public Long getEtfProductId() {
        return etfProductId;
    }

    public void setEtfProductId(Long etfProductId) {
        this.etfProductId = etfProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfHistoryDTO etfHistoryDTO = (EtfHistoryDTO) o;
        if (etfHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfHistoryDTO{" +
            "id=" + getId() +
            ", valueDate='" + getValueDate() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", unitQty=" + getUnitQty() +
            ", aum=" + getAum() +
            ", etfProduct=" + getEtfProductId() +
            "}";
    }
}
