package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfExecutionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfExecution} and its DTO {@link EtfExecutionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfExecutionMapper extends EntityMapper<EtfExecutionDTO, EtfExecution> {


    @Mapping(target = "executeDtls", ignore = true)
    @Mapping(target = "removeExecuteDtl", ignore = true)
    EtfExecution toEntity(EtfExecutionDTO etfExecutionDTO);

    default EtfExecution fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfExecution etfExecution = new EtfExecution();
        etfExecution.setId(id);
        return etfExecution;
    }
}
