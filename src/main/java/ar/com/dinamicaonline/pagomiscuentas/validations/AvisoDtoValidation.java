package ar.com.dinamicaonline.pagomiscuentas.validations;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;

public class AvisoDtoValidation {

    public static boolean validationAvisoDto(AvisoDto avisoDto) {
        if (avisoDto != null) {
            return true;
        }
        return false;
    }

}
