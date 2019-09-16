package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfExecutionDtlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfExecutionDtl} and its DTO {@link EtfExecutionDtlDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtfExecutionMapper.class})
public interface EtfExecutionDtlMapper extends EntityMapper<EtfExecutionDtlDTO, EtfExecutionDtl> {

    @Mapping(source = "execute.id", target = "executeId")
    EtfExecutionDtlDTO toDto(EtfExecutionDtl etfExecutionDtl);

    @Mapping(target = "subscripts", ignore = true)
    @Mapping(target = "removeSubscript", ignore = true)
    @Mapping(target = "redemptions", ignore = true)
    @Mapping(target = "removeRedemption", ignore = true)
    @Mapping(source = "executeId", target = "execute")
    EtfExecutionDtl toEntity(EtfExecutionDtlDTO etfExecutionDtlDTO);

    default EtfExecutionDtl fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfExecutionDtl etfExecutionDtl = new EtfExecutionDtl();
        etfExecutionDtl.setId(id);
        return etfExecutionDtl;
    }
}
