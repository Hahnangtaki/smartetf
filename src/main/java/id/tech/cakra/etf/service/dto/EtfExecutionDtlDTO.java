package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfExecutionDtl} entity.
 */
public class EtfExecutionDtlDTO implements Serializable {

    private Long id;

    
    private Integer basketOrderId;

    private Integer transactionId;

    private String transactionType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBasketOrderId() {
        return basketOrderId;
    }

    public void setBasketOrderId(Integer basketOrderId) {
        this.basketOrderId = basketOrderId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfExecutionDtlDTO etfExecutionDtlDTO = (EtfExecutionDtlDTO) o;
        if (etfExecutionDtlDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfExecutionDtlDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfExecutionDtlDTO{" +
            "id=" + getId() +
            ", basketOrderId=" + getBasketOrderId() +
            ", transactionId=" + getTransactionId() +
            ", transactionType='" + getTransactionType() + "'" +
            "}";
    }
}
