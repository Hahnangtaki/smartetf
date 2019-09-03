package id.tech.cakra.etf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.GlobalParameter} entity.
 */
public class GlobalParameterDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 20, max = 20)
    private String prmId;

    @NotNull
    @Size(max = 50)
    private String prmName;

    @NotNull
    @Size(max = 1)
    private String prmType;

    private String appType;

    private Integer intVal;

    private Float floatVal;

    private String strVal;

    private LocalDate dateVal;

    private Boolean show;

    private Boolean edit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrmId() {
        return prmId;
    }

    public void setPrmId(String prmId) {
        this.prmId = prmId;
    }

    public String getPrmName() {
        return prmName;
    }

    public void setPrmName(String prmName) {
        this.prmName = prmName;
    }

    public String getPrmType() {
        return prmType;
    }

    public void setPrmType(String prmType) {
        this.prmType = prmType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Integer getIntVal() {
        return intVal;
    }

    public void setIntVal(Integer intVal) {
        this.intVal = intVal;
    }

    public Float getFloatVal() {
        return floatVal;
    }

    public void setFloatVal(Float floatVal) {
        this.floatVal = floatVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

    public LocalDate getDateVal() {
        return dateVal;
    }

    public void setDateVal(LocalDate dateVal) {
        this.dateVal = dateVal;
    }

    public Boolean isShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean isEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GlobalParameterDTO globalParameterDTO = (GlobalParameterDTO) o;
        if (globalParameterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), globalParameterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GlobalParameterDTO{" +
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
