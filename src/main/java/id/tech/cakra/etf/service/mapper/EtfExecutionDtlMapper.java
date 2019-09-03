package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfExecutionDtlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfExecutionDtl} and its DTO {@link EtfExecutionDtlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfExecutionDtlMapper extends EntityMapper<EtfExecutionDtlDTO, EtfExecutionDtl> {



    default EtfExecutionDtl fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfExecutionDtl etfExecutionDtl = new EtfExecutionDtl();
        etfExecutionDtl.setId(id);
        return etfExecutionDtl;
    }
}
