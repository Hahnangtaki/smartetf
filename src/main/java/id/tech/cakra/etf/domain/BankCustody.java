package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BankCustody.
 */
@Entity
@Table(name = "bank_custody")
public class BankCustody implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "custody_id", nullable = false, unique = true)
    private Integer custodyId;

    @NotNull
    @Size(max = 10)
    @Column(name = "custody_code", length = 10, nullable = false)
    private String custodyCode;

    @Size(max = 150)
    @Column(name = "custodi_name", length = 150)
    private String custodiName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustodyId() {
        return custodyId;
    }

    public BankCustody custodyId(Integer custodyId) {
        this.custodyId = custodyId;
        return this;
    }

    public void setCustodyId(Integer custodyId) {
        this.custodyId = custodyId;
    }

    public String getCustodyCode() {
        return custodyCode;
    }

    public BankCustody custodyCode(String custodyCode) {
        this.custodyCode = custodyCode;
        return this;
    }

    public void setCustodyCode(String custodyCode) {
        this.custodyCode = custodyCode;
    }

    public String getCustodiName() {
        return custodiName;
    }

    public BankCustody custodiName(String custodiName) {
        this.custodiName = custodiName;
        return this;
    }

    public void setCustodiName(String custodiName) {
        this.custodiName = custodiName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankCustody)) {
            return false;
        }
        return id != null && id.equals(((BankCustody) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BankCustody{" +
            "id=" + getId() +
            ", custodyId=" + getCustodyId() +
            ", custodyCode='" + getCustodyCode() + "'" +
            ", custodiName='" + getCustodiName() + "'" +
            "}";
    }
}
