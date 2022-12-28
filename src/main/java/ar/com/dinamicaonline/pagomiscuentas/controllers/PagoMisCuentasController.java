package ar.com.dinamicaonline.pagomiscuentas.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;
import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.service.ReceiveAndSendService;
import ar.com.dinamicaonline.pagomiscuentas.validations.AvisoDtoValidation;

@RestController
public class PagoMisCuentasController {
    // Annotation
    @Autowired
    private ReceiveAndSendService receiveAndSendService;

    @RequestMapping(value = "/payment_notify", method = RequestMethod.POST)
    public Map<String, Object> getState(@RequestBody AvisoDto avisoDto) {
        Map<String, Object> map = new HashMap<String, Object>();
        ReceiveAndSend receiveAndSend = new ReceiveAndSend();

        // String timeStamp = new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss").format(Calendar.getInstance().getTime());
        // System.out.println(timeStamp);

        if (AvisoDtoValidation.validationAvisoDto(avisoDto)) {

            // receiveAndSend.setId(0);
            // receiveAndSend.setCreatedOn(Calendar.getInstance().getTime());

            // idOrigin debe ser 6 por tabla api_ReceiveAndSendOrigin
            // Hardcoreado pero sacar de tabla :(
            receiveAndSend.setIdOrigin(6);

            // se rellena en el metodo saveReceiveAndSend
            receiveAndSend.setIdAccount(0);

            // NO ES ASIII
            // 1:recibe ; 2:envia
            if (avisoDto.getOperation_id().contains("AVISO_PAGO")) {
                receiveAndSend.setReceiveSend(1);
            } else if (avisoDto.getOperation_id().contains("ANULAR_PAGO")) {
                receiveAndSend.setReceiveSend(2);
            }

            // Crear Json de respuesta
            receiveAndSend.setMessagge(avisoDto.toString());

            ReceiveAndSend rs = receiveAndSendService.saveReceiveAndSend(receiveAndSend, avisoDto);

            // Token hardcoreado, incluir en bd api_parameters
            if (avisoDto.getAccess_token().contains("a4a0e9a321dd1a4b718f95f5a7e2f177")) {
                map.put("code", "200");
                map.put("message", "OK");
                map.put("idAccount", rs.getIdAccount());
                map.put("customerId", avisoDto.getCustomer_id());

            } else {
                map.put("code", "400");
                map.put("message", "Error de autenticacion");
            }

        } else {
            // respuesta error de solicitud...
        }

        return map;
    }

    // Save operation
    /*
     * @PostMapping("/receiveAndSend")
     * public ReceiveAndSend saveReceiveAndSend(
     * 
     * @RequestBody ReceiveAndSend recieveAndSend) {
     * return receiveAndSendService.saveReceiveAndSend(recieveAndSend);
     * }
     */
}
