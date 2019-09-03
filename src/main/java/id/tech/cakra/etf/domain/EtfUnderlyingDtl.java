package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EtfUnderlyingDtl.
 */
@Entity
@Table(name = "etf_underlying_dtl")
public class EtfUnderlyingDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "underlying_id", nullable = false, unique = true)
    private Integer underlyingId;

    @Size(max = 10)
    @Column(name = "stock_code", length = 10)
    private String stockCode;

    @Column(name = "weight")
    private Float weight;

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

    public EtfUnderlyingDtl underlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
        return this;
    }

    public void setUnderlyingId(Integer underlyingId) {
        this.underlyingId = underlyingId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public EtfUnderlyingDtl stockCode(String stockCode) {
        this.stockCode = stockCode;
        return this;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Float getWeight() {
        return weight;
    }

    public EtfUnderlyingDtl weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfUnderlyingDtl)) {
            return false;
        }
        return id != null && id.equals(((EtfUnderlyingDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfUnderlyingDtl{" +
            "id=" + getId() +
            ", underlyingId=" + getUnderlyingId() +
            ", stockCode='" + getStockCode() + "'" +
            ", weight=" + getWeight() +
            "}";
    }
}
