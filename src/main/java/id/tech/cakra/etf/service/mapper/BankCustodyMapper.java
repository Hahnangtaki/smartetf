package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.BankCustodyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BankCustody} and its DTO {@link BankCustodyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankCustodyMapper extends EntityMapper<BankCustodyDTO, BankCustody> {



    default BankCustody fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankCustody bankCustody = new BankCustody();
        bankCustody.setId(id);
        return bankCustody;
    }
}
