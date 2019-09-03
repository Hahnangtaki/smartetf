package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfProductDealerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfProductDealer} and its DTO {@link EtfProductDealerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfProductDealerMapper extends EntityMapper<EtfProductDealerDTO, EtfProductDealer> {



    default EtfProductDealer fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfProductDealer etfProductDealer = new EtfProductDealer();
        etfProductDealer.setId(id);
        return etfProductDealer;
    }
}
