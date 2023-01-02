package ar.com.dinamicaonline.pagomiscuentas.service;

import org.springframework.http.ResponseEntity;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;

public interface ReceiveAndSendService {
    // Save operation
    ResponseEntity<?> saveReceiveAndSend(AvisoDto avisoDto);
}
