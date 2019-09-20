package id.tech.cakra.etf.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DealerParticipant.
 */
@Entity
@Table(name = "dealer_participant")
public class DealerParticipant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "dealer_code", length = 10, nullable = false, unique = true)
    private String dealerCode;

    @Size(max = 100)
    @Column(name = "dealer_name", length = 100)
    private String dealerName;

    @OneToMany(mappedBy = "dealerParticipant")
    private Set<Redemption> redemptions = new HashSet<>();

    @OneToMany(mappedBy = "dealerParticipant")
    private Set<Subscript> subscripts = new HashSet<>();

    @ManyToMany(mappedBy = "dealerParticipants")
    @JsonIgnore
    private Set<EtfProduct> etfProducts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public DealerParticipant dealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
        return this;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public DealerParticipant dealerName(String dealerName) {
        this.dealerName = dealerName;
        return this;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Set<Redemption> getRedemptions() {
        return redemptions;
    }

    public DealerParticipant redemptions(Set<Redemption> redemptions) {
        this.redemptions = redemptions;
        return this;
    }

    public DealerParticipant addRedemption(Redemption redemption) {
        this.redemptions.add(redemption);
        redemption.setDealerParticipant(this);
        return this;
    }

    public DealerParticipant removeRedemption(Redemption redemption) {
        this.redemptions.remove(redemption);
        redemption.setDealerParticipant(null);
        return this;
    }

    public void setRedemptions(Set<Redemption> redemptions) {
        this.redemptions = redemptions;
    }

    public Set<Subscript> getSubscripts() {
        return subscripts;
    }

    public DealerParticipant subscripts(Set<Subscript> subscripts) {
        this.subscripts = subscripts;
        return this;
    }

    public DealerParticipant addSubscript(Subscript subscript) {
        this.subscripts.add(subscript);
        subscript.setDealerParticipant(this);
        return this;
    }

    public DealerParticipant removeSubscript(Subscript subscript) {
        this.subscripts.remove(subscript);
        subscript.setDealerParticipant(null);
        return this;
    }

    public void setSubscripts(Set<Subscript> subscripts) {
        this.subscripts = subscripts;
    }

    public Set<EtfProduct> getEtfProducts() {
        return etfProducts;
    }

    public DealerParticipant etfProducts(Set<EtfProduct> etfProducts) {
        this.etfProducts = etfProducts;
        return this;
    }

    public DealerParticipant addEtfProduct(EtfProduct etfProduct) {
        this.etfProducts.add(etfProduct);
        etfProduct.getDealerParticipants().add(this);
        return this;
    }

    public DealerParticipant removeEtfProduct(EtfProduct etfProduct) {
        this.etfProducts.remove(etfProduct);
        etfProduct.getDealerParticipants().remove(this);
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
        if (!(o instanceof DealerParticipant)) {
            return false;
        }
        return id != null && id.equals(((DealerParticipant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DealerParticipant{" +
            "id=" + getId() +
            ", dealerCode='" + getDealerCode() + "'" +
            ", dealerName='" + getDealerName() + "'" +
            "}";
    }
}
