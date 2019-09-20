package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.SubscriptDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Subscript} and its DTO {@link SubscriptDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtfProductMapper.class, DealerParticipantMapper.class, EtfExecutionDtlMapper.class})
public interface SubscriptMapper extends EntityMapper<SubscriptDTO, Subscript> {

    @Mapping(source = "etfProduct.id", target = "etfProductId")
    @Mapping(source = "dealerParticipant.id", target = "dealerParticipantId")
    @Mapping(source = "etfExecutionDtl.id", target = "etfExecutionDtlId")
    SubscriptDTO toDto(Subscript subscript);

    @Mapping(source = "etfProductId", target = "etfProduct")
    @Mapping(source = "dealerParticipantId", target = "dealerParticipant")
    @Mapping(source = "etfExecutionDtlId", target = "etfExecutionDtl")
    Subscript toEntity(SubscriptDTO subscriptDTO);

    default Subscript fromId(Long id) {
        if (id == null) {
            return null;
        }
        Subscript subscript = new Subscript();
        subscript.setId(id);
        return subscript;
    }
}
