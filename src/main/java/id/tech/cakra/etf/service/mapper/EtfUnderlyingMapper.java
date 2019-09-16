package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfUnderlying} and its DTO {@link EtfUnderlyingDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtfProductMapper.class})
public interface EtfUnderlyingMapper extends EntityMapper<EtfUnderlyingDTO, EtfUnderlying> {

    @Mapping(source = "etfProduct.id", target = "etfProductId")
    EtfUnderlyingDTO toDto(EtfUnderlying etfUnderlying);

    @Mapping(target = "etfUnderlyingDtls", ignore = true)
    @Mapping(target = "removeEtfUnderlyingDtl", ignore = true)
    @Mapping(source = "etfProductId", target = "etfProduct")
    EtfUnderlying toEntity(EtfUnderlyingDTO etfUnderlyingDTO);

    default EtfUnderlying fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfUnderlying etfUnderlying = new EtfUnderlying();
        etfUnderlying.setId(id);
        return etfUnderlying;
    }
}
