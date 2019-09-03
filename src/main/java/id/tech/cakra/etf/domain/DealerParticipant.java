package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

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
    @Column(name = "dealer_id", nullable = false, unique = true)
    private Integer dealerId;

    @NotNull
    @Size(max = 10)
    @Column(name = "dealer_code", length = 10, nullable = false)
    private String dealerCode;

    @Size(max = 100)
    @Column(name = "dealer_name", length = 100)
    private String dealerName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public DealerParticipant dealerId(Integer dealerId) {
        this.dealerId = dealerId;
        return this;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
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
            ", dealerId=" + getDealerId() +
            ", dealerCode='" + getDealerCode() + "'" +
            ", dealerName='" + getDealerName() + "'" +
            "}";
    }
}
