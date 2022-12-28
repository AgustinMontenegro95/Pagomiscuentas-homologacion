package ar.com.dinamicaonline.pagomiscuentas.service;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;
import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;

public interface ReceiveAndSendService {
    // Save operation
    ReceiveAndSend saveReceiveAndSend(ReceiveAndSend recieveAndSend, AvisoDto avisoDto);
}
