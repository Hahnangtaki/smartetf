package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfUnderlying} and its DTO {@link EtfUnderlyingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfUnderlyingMapper extends EntityMapper<EtfUnderlyingDTO, EtfUnderlying> {



    default EtfUnderlying fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfUnderlying etfUnderlying = new EtfUnderlying();
        etfUnderlying.setId(id);
        return etfUnderlying;
    }
}
