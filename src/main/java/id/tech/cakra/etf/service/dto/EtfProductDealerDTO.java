package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfProductDealer} entity.
 */
public class EtfProductDealerDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer productId;

    @NotNull
    private Integer dealerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfProductDealerDTO etfProductDealerDTO = (EtfProductDealerDTO) o;
        if (etfProductDealerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfProductDealerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfProductDealerDTO{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", dealerId=" + getDealerId() +
            "}";
    }
}
