package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EtfProductDealer.
 */
@Entity
@Table(name = "etf_product_dealer")
public class EtfProductDealer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false, unique = true)
    private Integer productId;

    @NotNull
    @Column(name = "dealer_id", nullable = false, unique = true)
    private Integer dealerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public EtfProductDealer productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public EtfProductDealer dealerId(Integer dealerId) {
        this.dealerId = dealerId;
        return this;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfProductDealer)) {
            return false;
        }
        return id != null && id.equals(((EtfProductDealer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfProductDealer{" +
            "id=" + getId() +
            ", productId=" + getProductId() +
            ", dealerId=" + getDealerId() +
            "}";
    }
}
