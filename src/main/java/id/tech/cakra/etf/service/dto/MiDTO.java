package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.Mi} entity.
 */
public class MiDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer miId;

    @NotNull
    @Size(max = 10)
    private String miCode;

    @Size(max = 100)
    private String miName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMiId() {
        return miId;
    }

    public void setMiId(Integer miId) {
        this.miId = miId;
    }

    public String getMiCode() {
        return miCode;
    }

    public void setMiCode(String miCode) {
        this.miCode = miCode;
    }

    public String getMiName() {
        return miName;
    }

    public void setMiName(String miName) {
        this.miName = miName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MiDTO miDTO = (MiDTO) o;
        if (miDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), miDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MiDTO{" +
            "id=" + getId() +
            ", miId=" + getMiId() +
            ", miCode='" + getMiCode() + "'" +
            ", miName='" + getMiName() + "'" +
            "}";
    }
}
