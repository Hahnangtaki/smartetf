package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.GlobalParameterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GlobalParameter} and its DTO {@link GlobalParameterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GlobalParameterMapper extends EntityMapper<GlobalParameterDTO, GlobalParameter> {



    default GlobalParameter fromId(Long id) {
        if (id == null) {
            return null;
        }
        GlobalParameter globalParameter = new GlobalParameter();
        globalParameter.setId(id);
        return globalParameter;
    }
}
