package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfProduct} and its DTO {@link EtfProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealerParticipantMapper.class, MiMapper.class, BankCustodyMapper.class})
public interface EtfProductMapper extends EntityMapper<EtfProductDTO, EtfProduct> {

    @Mapping(source = "mi.id", target = "miId")
    @Mapping(source = "bankCustody.id", target = "bankCustodyId")
    EtfProductDTO toDto(EtfProduct etfProduct);

    @Mapping(target = "etfUnderlyings", ignore = true)
    @Mapping(target = "removeEtfUnderlying", ignore = true)
    @Mapping(target = "etfHistories", ignore = true)
    @Mapping(target = "removeEtfHistory", ignore = true)
    @Mapping(target = "subscripts", ignore = true)
    @Mapping(target = "removeSubscript", ignore = true)
    @Mapping(target = "redemptions", ignore = true)
    @Mapping(target = "removeRedemption", ignore = true)
    @Mapping(target = "portofolios", ignore = true)
    @Mapping(target = "removePortofolio", ignore = true)
    @Mapping(target = "removeDealerParticipant", ignore = true)
    @Mapping(source = "miId", target = "mi")
    @Mapping(source = "bankCustodyId", target = "bankCustody")
    EtfProduct toEntity(EtfProductDTO etfProductDTO);

    default EtfProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfProduct etfProduct = new EtfProduct();
        etfProduct.setId(id);
        return etfProduct;
    }
}
