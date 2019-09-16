package id.tech.cakra.etf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EtfExecutionDtl.
 */
@Entity
@Table(name = "etf_execution_dtl")
public class EtfExecutionDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "transaction_type")
    private String transactionType;

    @OneToMany(mappedBy = "etfExecutionDtl")
    private Set<Subscript> subscripts = new HashSet<>();

    @OneToMany(mappedBy = "etfExecutionDtl")
    private Set<Redemption> redemptions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("etfExecutionDtls")
    private EtfExecution etfExecution;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public EtfExecutionDtl transactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Set<Subscript> getSubscripts() {
        return subscripts;
    }

    public EtfExecutionDtl subscripts(Set<Subscript> subscripts) {
        this.subscripts = subscripts;
        return this;
    }

    public EtfExecutionDtl addSubscript(Subscript subscript) {
        this.subscripts.add(subscript);
        subscript.setEtfExecutionDtl(this);
        return this;
    }

    public EtfExecutionDtl removeSubscript(Subscript subscript) {
        this.subscripts.remove(subscript);
        subscript.setEtfExecutionDtl(null);
        return this;
    }

    public void setSubscripts(Set<Subscript> subscripts) {
        this.subscripts = subscripts;
    }

    public Set<Redemption> getRedemptions() {
        return redemptions;
    }

    public EtfExecutionDtl redemptions(Set<Redemption> redemptions) {
        this.redemptions = redemptions;
        return this;
    }

    public EtfExecutionDtl addRedemption(Redemption redemption) {
        this.redemptions.add(redemption);
        redemption.setEtfExecutionDtl(this);
        return this;
    }

    public EtfExecutionDtl removeRedemption(Redemption redemption) {
        this.redemptions.remove(redemption);
        redemption.setEtfExecutionDtl(null);
        return this;
    }

    public void setRedemptions(Set<Redemption> redemptions) {
        this.redemptions = redemptions;
    }

    public EtfExecution getEtfExecution() {
        return etfExecution;
    }

    public EtfExecutionDtl etfExecution(EtfExecution etfExecution) {
        this.etfExecution = etfExecution;
        return this;
    }

    public void setEtfExecution(EtfExecution etfExecution) {
        this.etfExecution = etfExecution;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfExecutionDtl)) {
            return false;
        }
        return id != null && id.equals(((EtfExecutionDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfExecutionDtl{" +
            "id=" + getId() +
            ", transactionType='" + getTransactionType() + "'" +
            "}";
    }
}
