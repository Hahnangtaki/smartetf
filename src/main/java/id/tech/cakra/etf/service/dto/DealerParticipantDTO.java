package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.DealerParticipant} entity.
 */
public class DealerParticipantDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 10)
    private String dealerCode;

    @Size(max = 100)
    private String dealerName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DealerParticipantDTO dealerParticipantDTO = (DealerParticipantDTO) o;
        if (dealerParticipantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dealerParticipantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DealerParticipantDTO{" +
            "id=" + getId() +
            ", dealerCode='" + getDealerCode() + "'" +
            ", dealerName='" + getDealerName() + "'" +
            "}";
    }
}
