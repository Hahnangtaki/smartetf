package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDtlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfUnderlyingDtl} and its DTO {@link EtfUnderlyingDtlDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtfUnderlyingMapper.class})
public interface EtfUnderlyingDtlMapper extends EntityMapper<EtfUnderlyingDtlDTO, EtfUnderlyingDtl> {

    @Mapping(source = "etfUnderlying.id", target = "etfUnderlyingId")
    @Mapping(source = "etfUnderlying.id", target = "etfUnderlyingId")
    EtfUnderlyingDtlDTO toDto(EtfUnderlyingDtl etfUnderlyingDtl);

    @Mapping(source = "etfUnderlyingId", target = "etfUnderlying")
    @Mapping(source = "etfUnderlyingId", target = "etfUnderlying")
    EtfUnderlyingDtl toEntity(EtfUnderlyingDtlDTO etfUnderlyingDtlDTO);

    default EtfUnderlyingDtl fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfUnderlyingDtl etfUnderlyingDtl = new EtfUnderlyingDtl();
        etfUnderlyingDtl.setId(id);
        return etfUnderlyingDtl;
    }
}
