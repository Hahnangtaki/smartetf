package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @Size(max = 10)
    @Column(name = "custody_code", length = 10, nullable = false, unique = true)
    private String custodyCode;

    @Size(max = 150)
    @Column(name = "custodi_name", length = 150)
    private String custodiName;

    @OneToMany(mappedBy = "bankCustody")
    private Set<EtfProduct> etfProducts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<EtfProduct> getEtfProducts() {
        return etfProducts;
    }

    public BankCustody etfProducts(Set<EtfProduct> etfProducts) {
        this.etfProducts = etfProducts;
        return this;
    }

    public BankCustody addEtfProduct(EtfProduct etfProduct) {
        this.etfProducts.add(etfProduct);
        etfProduct.setBankCustody(this);
        return this;
    }

    public BankCustody removeEtfProduct(EtfProduct etfProduct) {
        this.etfProducts.remove(etfProduct);
        etfProduct.setBankCustody(null);
        return this;
    }

    public void setEtfProducts(Set<EtfProduct> etfProducts) {
        this.etfProducts = etfProducts;
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
            ", custodyCode='" + getCustodyCode() + "'" +
            ", custodiName='" + getCustodiName() + "'" +
            "}";
    }
}
