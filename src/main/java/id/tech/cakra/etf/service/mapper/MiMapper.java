package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.MiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mi} and its DTO {@link MiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MiMapper extends EntityMapper<MiDTO, Mi> {


    @Mapping(target = "etfProducts", ignore = true)
    @Mapping(target = "removeEtfProduct", ignore = true)
    Mi toEntity(MiDTO miDTO);

    default Mi fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mi mi = new Mi();
        mi.setId(id);
        return mi;
    }
}
