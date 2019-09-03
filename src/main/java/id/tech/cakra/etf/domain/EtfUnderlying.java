package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EtfUnderlying.
 */
@Entity
@Table(name = "etf_underlying")
public class EtfUnderlying implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "underlying_id", nullable = false, unique = true)
    private Integer underlyingId;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "product_id")
    private Integer productId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUnderlyingId() {
        return underlyingId;
    }

    public EtfUnderlying underlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
        return this;
    }

    public void setUnderlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public EtfUnderlying effectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public EtfUnderlying productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfUnderlying)) {
            return false;
        }
        return id != null && id.equals(((EtfUnderlying) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfUnderlying{" +
            "id=" + getId() +
            ", underlyingId=" + getUnderlyingId() +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", productId=" + getProductId() +
            "}";
    }
}
