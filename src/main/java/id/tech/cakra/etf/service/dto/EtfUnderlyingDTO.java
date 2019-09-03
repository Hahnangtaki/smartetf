package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfUnderlying} entity.
 */
public class EtfUnderlyingDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer underlyingId;

    private LocalDate effectiveDate;

    private Integer productId;


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

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfUnderlyingDTO etfUnderlyingDTO = (EtfUnderlyingDTO) o;
        if (etfUnderlyingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfUnderlyingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfUnderlyingDTO{" +
            "id=" + getId() +
            ", underlyingId=" + getUnderlyingId() +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", productId=" + getProductId() +
            "}";
    }
}
