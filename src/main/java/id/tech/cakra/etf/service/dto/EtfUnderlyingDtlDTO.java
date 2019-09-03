package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfUnderlyingDtl} entity.
 */
public class EtfUnderlyingDtlDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer underlyingId;

    @Size(max = 10)
    private String stockCode;

    private Float weight;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUnderlyingId() {
        return underlyingId;
    }

    public void setUnderlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfUnderlyingDtlDTO etfUnderlyingDtlDTO = (EtfUnderlyingDtlDTO) o;
        if (etfUnderlyingDtlDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfUnderlyingDtlDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfUnderlyingDtlDTO{" +
            "id=" + getId() +
            ", underlyingId=" + getUnderlyingId() +
            ", stockCode='" + getStockCode() + "'" +
            ", weight=" + getWeight() +
            "}";
    }
}
