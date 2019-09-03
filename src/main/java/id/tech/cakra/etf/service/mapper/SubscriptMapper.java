package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.SubscriptDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Subscript} and its DTO {@link SubscriptDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubscriptMapper extends EntityMapper<SubscriptDTO, Subscript> {



    default Subscript fromId(Long id) {
        if (id == null) {
            return null;
        }
        Subscript subscript = new Subscript();
        subscript.setId(id);
        return subscript;
    }
}
