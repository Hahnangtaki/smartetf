package id.tech.cakra.etf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @ManyToOne
    @JsonIgnoreProperties("etfUnderlyings")
    private EtfProduct etfProduct;

    @OneToMany(mappedBy = "etfUnderlying")
    private Set<EtfUnderlyingDtl> etfUnderlyingDtls = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EtfProduct getEtfProduct() {
        return etfProduct;
    }

    public EtfUnderlying etfProduct(EtfProduct etfProduct) {
        this.etfProduct = etfProduct;
        return this;
    }

    public void setEtfProduct(EtfProduct etfProduct) {
        this.etfProduct = etfProduct;
    }

    public Set<EtfUnderlyingDtl> getEtfUnderlyingDtls() {
        return etfUnderlyingDtls;
    }

    public EtfUnderlying etfUnderlyingDtls(Set<EtfUnderlyingDtl> etfUnderlyingDtls) {
        this.etfUnderlyingDtls = etfUnderlyingDtls;
        return this;
    }

    public EtfUnderlying addEtfUnderlyingDtl(EtfUnderlyingDtl etfUnderlyingDtl) {
        this.etfUnderlyingDtls.add(etfUnderlyingDtl);
        etfUnderlyingDtl.setEtfUnderlying(this);
        return this;
    }

    public EtfUnderlying removeEtfUnderlyingDtl(EtfUnderlyingDtl etfUnderlyingDtl) {
        this.etfUnderlyingDtls.remove(etfUnderlyingDtl);
        etfUnderlyingDtl.setEtfUnderlying(null);
        return this;
    }

    public void setEtfUnderlyingDtls(Set<EtfUnderlyingDtl> etfUnderlyingDtls) {
        this.etfUnderlyingDtls = etfUnderlyingDtls;
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
            ", effectiveDate='" + getEffectiveDate() + "'" +
            "}";
    }
}
