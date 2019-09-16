package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.RedemptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Redemption} and its DTO {@link RedemptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtfProductMapper.class, DealerParticipantMapper.class, EtfExecutionDtlMapper.class})
public interface RedemptionMapper extends EntityMapper<RedemptionDTO, Redemption> {

    @Mapping(source = "etfProduct.id", target = "etfProductId")
    @Mapping(source = "dealerParticipant.id", target = "dealerParticipantId")
    @Mapping(source = "etfExecutionDtl.id", target = "etfExecutionDtlId")
    RedemptionDTO toDto(Redemption redemption);

    @Mapping(source = "etfProductId", target = "etfProduct")
    @Mapping(source = "dealerParticipantId", target = "dealerParticipant")
    @Mapping(source = "etfExecutionDtlId", target = "etfExecutionDtl")
    Redemption toEntity(RedemptionDTO redemptionDTO);

    default Redemption fromId(Long id) {
        if (id == null) {
            return null;
        }
        Redemption redemption = new Redemption();
        redemption.setId(id);
        return redemption;
    }
}
