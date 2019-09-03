package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfProduct} and its DTO {@link EtfProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfProductMapper extends EntityMapper<EtfProductDTO, EtfProduct> {



    default EtfProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfProduct etfProduct = new EtfProduct();
        etfProduct.setId(id);
        return etfProduct;
    }
}
