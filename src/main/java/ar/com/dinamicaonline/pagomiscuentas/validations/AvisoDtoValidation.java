package ar.com.dinamicaonline.pagomiscuentas.validations;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;

public class AvisoDtoValidation {

    public static boolean validationAvisoDto(AvisoDto avisoDto) {
        if (avisoDto.getAccess_token() != null
                && avisoDto.getCurrency() != null
                && avisoDto.getAmount() != null
                && avisoDto.getOperation_id() != null
                && avisoDto.getCustomer_id() != null
                && avisoDto.getInvoice_id() != null
                && avisoDto.getDue_date() != null) {
            return true;
        }
        return false;
    }

}
