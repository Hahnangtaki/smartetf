package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.DealerParticipantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealerParticipant} and its DTO {@link DealerParticipantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DealerParticipantMapper extends EntityMapper<DealerParticipantDTO, DealerParticipant> {



    default DealerParticipant fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealerParticipant dealerParticipant = new DealerParticipant();
        dealerParticipant.setId(id);
        return dealerParticipant;
    }
}
