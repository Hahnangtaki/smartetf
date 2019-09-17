package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A GlobalParameter.
 */
@Entity
@Table(name = "global_parameter")
public class GlobalParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prm_id", length = 20, nullable = false, unique = true)
    private String prmId;

    @NotNull
    @Size(max = 50)
    @Column(name = "prm_name", length = 50, nullable = false)
    private String prmName;

    @NotNull
    @Size(max = 1)
    @Column(name = "prm_type", length = 1, nullable = false)
    private String prmType;

    @Column(name = "app_type")
    private String appType;

    @Column(name = "int_val")
    private Integer intVal;

    @Column(name = "float_val")
    private Float floatVal;

    @Column(name = "str_val")
    private String strVal;

    @Column(name = "date_val")
    private LocalDate dateVal;

    @Column(name = "show")
    private Boolean show;

    @Column(name = "edit")
    private Boolean edit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrmId() {
        return prmId;
    }

    public GlobalParameter prmId(String prmId) {
        this.prmId = prmId;
        return this;
    }

    public void setPrmId(String prmId) {
        this.prmId = prmId;
    }

    public String getPrmName() {
        return prmName;
    }

    public GlobalParameter prmName(String prmName) {
        this.prmName = prmName;
        return this;
    }

    public void setPrmName(String prmName) {
        this.prmName = prmName;
    }

    public String getPrmType() {
        return prmType;
    }

    public GlobalParameter prmType(String prmType) {
        this.prmType = prmType;
        return this;
    }

    public void setPrmType(String prmType) {
        this.prmType = prmType;
    }

    public String getAppType() {
        return appType;
    }

    public GlobalParameter appType(String appType) {
        this.appType = appType;
        return this;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Integer getIntVal() {
        return intVal;
    }

    public GlobalParameter intVal(Integer intVal) {
        this.intVal = intVal;
        return this;
    }

    public void setIntVal(Integer intVal) {
        this.intVal = intVal;
    }

    public Float getFloatVal() {
        return floatVal;
    }

    public GlobalParameter floatVal(Float floatVal) {
        this.floatVal = floatVal;
        return this;
    }

    public void setFloatVal(Float floatVal) {
        this.floatVal = floatVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public GlobalParameter strVal(String strVal) {
        this.strVal = strVal;
        return this;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

    public LocalDate getDateVal() {
        return dateVal;
    }

    public GlobalParameter dateVal(LocalDate dateVal) {
        this.dateVal = dateVal;
        return this;
    }

    public void setDateVal(LocalDate dateVal) {
        this.dateVal = dateVal;
    }

    public Boolean isShow() {
        return show;
    }

    public GlobalParameter show(Boolean show) {
        this.show = show;
        return this;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean isEdit() {
        return edit;
    }

    public GlobalParameter edit(Boolean edit) {
        this.edit = edit;
        return this;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GlobalParameter)) {
            return false;
        }
        return id != null && id.equals(((GlobalParameter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GlobalParameter{" +
            "id=" + getId() +
            ", prmId='" + getPrmId() + "'" +
            ", prmName='" + getPrmName() + "'" +
            ", prmType='" + getPrmType() + "'" +
            ", appType='" + getAppType() + "'" +
            ", intVal=" + getIntVal() +
            ", floatVal=" + getFloatVal() +
            ", strVal='" + getStrVal() + "'" +
            ", dateVal='" + getDateVal() + "'" +
            ", show='" + isShow() + "'" +
            ", edit='" + isEdit() + "'" +
            "}";
    }
}
