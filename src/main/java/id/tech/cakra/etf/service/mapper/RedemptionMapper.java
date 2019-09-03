package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.RedemptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Redemption} and its DTO {@link RedemptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RedemptionMapper extends EntityMapper<RedemptionDTO, Redemption> {



    default Redemption fromId(Long id) {
        if (id == null) {
            return null;
        }
        Redemption redemption = new Redemption();
        redemption.setId(id);
        return redemption;
    }
}
