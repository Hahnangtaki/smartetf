package id.tech.cakra.etf.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Portofolio.
 */
@Entity
@Table(name = "portofolio")
public class Portofolio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "portofolio_date")
    private LocalDate portofolioDate;

    @Column(name = "unit")
    private Float unit;

    @Column(name = "avg_price")
    private Float avgPrice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Portofolio customerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Portofolio productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDate getPortofolioDate() {
        return portofolioDate;
    }

    public Portofolio portofolioDate(LocalDate portofolioDate) {
        this.portofolioDate = portofolioDate;
        return this;
    }

    public void setPortofolioDate(LocalDate portofolioDate) {
        this.portofolioDate = portofolioDate;
    }

    public Float getUnit() {
        return unit;
    }

    public Portofolio unit(Float unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(Float unit) {
        this.unit = unit;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public Portofolio avgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Portofolio)) {
            return false;
        }
        return id != null && id.equals(((Portofolio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Portofolio{" +
            "id=" + getId() +
            ", customerId=" + getCustomerId() +
            ", productId=" + getProductId() +
            ", portofolioDate='" + getPortofolioDate() + "'" +
            ", unit=" + getUnit() +
            ", avgPrice=" + getAvgPrice() +
            "}";
    }
}
