package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.Portofolio} entity.
 */
public class PortofolioDTO implements Serializable {

    private Long id;

    private Integer customerId;

    private Integer productId;

    private LocalDate portofolioDate;

    private Float unit;

    private Float avgPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDate getPortofolioDate() {
        return portofolioDate;
    }

    public void setPortofolioDate(LocalDate portofolioDate) {
        this.portofolioDate = portofolioDate;
    }

    public Float getUnit() {
        return unit;
    }

    public void setUnit(Float unit) {
        this.unit = unit;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PortofolioDTO portofolioDTO = (PortofolioDTO) o;
        if (portofolioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), portofolioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PortofolioDTO{" +
            "id=" + getId() +
            ", customerId=" + getCustomerId() +
            ", productId=" + getProductId() +
            ", portofolioDate='" + getPortofolioDate() + "'" +
            ", unit=" + getUnit() +
            ", avgPrice=" + getAvgPrice() +
            "}";
    }
}
