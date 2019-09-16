package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.BankCustody} entity.
 */
public class BankCustodyDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 10)
    private String custodyCode;

    @Size(max = 150)
    private String custodiName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustodyCode() {
        return custodyCode;
    }

    public void setCustodyCode(String custodyCode) {
        this.custodyCode = custodyCode;
    }

    public String getCustodiName() {
        return custodiName;
    }

    public void setCustodiName(String custodiName) {
        this.custodiName = custodiName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankCustodyDTO bankCustodyDTO = (BankCustodyDTO) o;
        if (bankCustodyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankCustodyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankCustodyDTO{" +
            "id=" + getId() +
            ", custodyCode='" + getCustodyCode() + "'" +
            ", custodiName='" + getCustodiName() + "'" +
            "}";
    }
}
