package ar.com.dinamicaonline.pagomiscuentas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.service.ReceiveAndSendService;

@RestController
public class ReceiveAndSendController {
    // Annotation
    @Autowired
    private ReceiveAndSendService recieveAndSendService;

    // Save operation
    @PostMapping("/receiveAndSend")
    public ReceiveAndSend saveDepartment(
            @RequestBody ReceiveAndSend recieveAndSend) {
        return recieveAndSendService.saveRecieveAndSend(recieveAndSend);
    }
}
