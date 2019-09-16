package id.tech.cakra.etf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EtfHistory.
 */
@Entity
@Table(name = "etf_history")
public class EtfHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "value_date")
    private LocalDate valueDate;

    @Column(name = "unit_price")
    private Float unitPrice;

    @Column(name = "unit_qty")
    private Float unitQty;

    @Column(name = "aum")
    private Float aum;

    @ManyToOne
    @JsonIgnoreProperties("etfHistories")
    private EtfProduct etfProduct;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public EtfHistory valueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public EtfHistory unitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getUnitQty() {
        return unitQty;
    }

    public EtfHistory unitQty(Float unitQty) {
        this.unitQty = unitQty;
        return this;
    }

    public void setUnitQty(Float unitQty) {
        this.unitQty = unitQty;
    }

    public Float getAum() {
        return aum;
    }

    public EtfHistory aum(Float aum) {
        this.aum = aum;
        return this;
    }

    public void setAum(Float aum) {
        this.aum = aum;
    }

    public EtfProduct getEtfProduct() {
        return etfProduct;
    }

    public EtfHistory etfProduct(EtfProduct etfProduct) {
        this.etfProduct = etfProduct;
        return this;
    }

    public void setEtfProduct(EtfProduct etfProduct) {
        this.etfProduct = etfProduct;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfHistory)) {
            return false;
        }
        return id != null && id.equals(((EtfHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfHistory{" +
            "id=" + getId() +
            ", valueDate='" + getValueDate() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", unitQty=" + getUnitQty() +
            ", aum=" + getAum() +
            "}";
    }
}
