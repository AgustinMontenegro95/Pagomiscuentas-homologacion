package ar.com.dinamicaonline.pagomiscuentas.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;
import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.service.ParameterService;
import ar.com.dinamicaonline.pagomiscuentas.service.ReceiveAndSendService;
import ar.com.dinamicaonline.pagomiscuentas.validations.AvisoDtoValidation;

@RestController
public class PagoMisCuentasController {

    @Autowired
    private ReceiveAndSendService receiveAndSendService;

    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/payment_notify", method = RequestMethod.POST)
    public Map<String, Object> getState(@RequestBody AvisoDto avisoDto) {
        Map<String, Object> map = new HashMap<String, Object>();
        ReceiveAndSend receiveAndSend = new ReceiveAndSend();
        int idOrigin = 6; // 6 por tabla api_ReceiveAndSendOrigin
        String token = parameterService.fetchTokenByParameterName("PMC-TOKEN");

        if (AvisoDtoValidation.validationAvisoDto(avisoDto)) {

            if (avisoDto.getAccess_token().contains(token)) {
                // receiveAndSend.setId(0);

                receiveAndSend.setCreatedOn(Calendar.getInstance().getTime());

                receiveAndSend.setIdOrigin(idOrigin);

                // receiveAndSend.setIdAccount();

                // 1:recibe ; 2:envia
                receiveAndSend.setReceiveSend(1);

                // avisoDto.toString() -> es la solicitud llegada desde Prisma
                receiveAndSend.setMessagge(avisoDto.toString());

                receiveAndSendService.saveReceiveAndSend(receiveAndSend, avisoDto);

                map.put("code", "200");
                map.put("message", "OK");
            } else {
                map.put("code", "400");
                map.put("message", "Error de autenticacion");
            }
        } else {
            // respuesta error de solicitud...
            map.put("code", "400");
            map.put("message", "Error de Autenticación");
        }

        return map;
    }

    // Save operation
    @PostMapping("/receiveAndSend")
    public ReceiveAndSend saveReceiveAndSend(@RequestBody ReceiveAndSend recieveAndSend) {
        return receiveAndSendService.saveReceiveAndSend(recieveAndSend, null);
    }

}
