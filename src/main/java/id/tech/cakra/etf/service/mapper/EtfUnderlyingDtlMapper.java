package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfUnderlyingDtlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfUnderlyingDtl} and its DTO {@link EtfUnderlyingDtlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfUnderlyingDtlMapper extends EntityMapper<EtfUnderlyingDtlDTO, EtfUnderlyingDtl> {



    default EtfUnderlyingDtl fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfUnderlyingDtl etfUnderlyingDtl = new EtfUnderlyingDtl();
        etfUnderlyingDtl.setId(id);
        return etfUnderlyingDtl;
    }
}
