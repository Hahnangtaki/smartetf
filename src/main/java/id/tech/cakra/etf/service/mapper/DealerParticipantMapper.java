package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.DealerParticipantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealerParticipant} and its DTO {@link DealerParticipantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DealerParticipantMapper extends EntityMapper<DealerParticipantDTO, DealerParticipant> {


    @Mapping(target = "redemptions", ignore = true)
    @Mapping(target = "removeRedemption", ignore = true)
    @Mapping(target = "subscripts", ignore = true)
    @Mapping(target = "removeSubscript", ignore = true)
    @Mapping(target = "etfProducts", ignore = true)
    @Mapping(target = "removeEtfProduct", ignore = true)
    DealerParticipant toEntity(DealerParticipantDTO dealerParticipantDTO);

    default DealerParticipant fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealerParticipant dealerParticipant = new DealerParticipant();
        dealerParticipant.setId(id);
        return dealerParticipant;
    }
}