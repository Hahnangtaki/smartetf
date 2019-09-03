package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Mi.
 */
@Entity
@Table(name = "mi")
public class Mi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mi_id", nullable = false, unique = true)
    private Integer miId;

    @NotNull
    @Size(max = 10)
    @Column(name = "mi_code", length = 10, nullable = false)
    private String miCode;

    @Size(max = 100)
    @Column(name = "mi_name", length = 100)
    private String miName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMiId() {
        return miId;
    }

    public Mi miId(Integer miId) {
        this.miId = miId;
        return this;
    }

    public void setMiId(Integer miId) {
        this.miId = miId;
    }

    public String getMiCode() {
        return miCode;
    }

    public Mi miCode(String miCode) {
        this.miCode = miCode;
        return this;
    }

    public void setMiCode(String miCode) {
        this.miCode = miCode;
    }

    public String getMiName() {
        return miName;
    }

    public Mi miName(String miName) {
        this.miName = miName;
        return this;
    }

    public void setMiName(String miName) {
        this.miName = miName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mi)) {
            return false;
        }
        return id != null && id.equals(((Mi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mi{" +
            "id=" + getId() +
            ", miId=" + getMiId() +
            ", miCode='" + getMiCode() + "'" +
            ", miName='" + getMiName() + "'" +
            "}";
    }
}
