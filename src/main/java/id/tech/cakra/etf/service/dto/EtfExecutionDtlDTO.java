package id.tech.cakra.etf.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfExecutionDtl} entity.
 */
public class EtfExecutionDtlDTO implements Serializable {

    private Long id;

    private String transactionType;


    private Long executeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Long etfExecutionId) {
        this.executeId = etfExecutionId;
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
            ", transactionType='" + getTransactionType() + "'" +
            ", execute=" + getExecuteId() +
            "}";
    }
}
