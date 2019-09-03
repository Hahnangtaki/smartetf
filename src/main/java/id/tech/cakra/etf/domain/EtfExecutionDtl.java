package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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

    
    @Column(name = "basket_order_id", unique = true)
    private Integer basketOrderId;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_type")
    private String transactionType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBasketOrderId() {
        return basketOrderId;
    }

    public EtfExecutionDtl basketOrderId(Integer basketOrderId) {
        this.basketOrderId = basketOrderId;
        return this;
    }

    public void setBasketOrderId(Integer basketOrderId) {
        this.basketOrderId = basketOrderId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public EtfExecutionDtl transactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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
            ", basketOrderId=" + getBasketOrderId() +
            ", transactionId=" + getTransactionId() +
            ", transactionType='" + getTransactionType() + "'" +
            "}";
    }
}
