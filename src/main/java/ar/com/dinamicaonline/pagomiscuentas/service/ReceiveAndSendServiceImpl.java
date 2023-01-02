package ar.com.dinamicaonline.pagomiscuentas.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;
import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.repositories.ReceiveAndSendRepository;
import ar.com.dinamicaonline.pagomiscuentas.validations.AvisoDtoValidation;

@Service
public class ReceiveAndSendServiceImpl implements ReceiveAndSendService {

    @Autowired
    private ReceiveAndSendRepository receiveAndSendRepository;

    @Autowired
    private EntidadServiceImpl entidadServiceImpl;

    @Autowired
    private ParameterService parameterService;

    @Override
    public ResponseEntity<?> saveReceiveAndSend(AvisoDto avisoDto) {
        Map<String, Object> responseBody = new HashMap<>();
        int idOrigin = 6;

        if (AvisoDtoValidation.validationAvisoDto(avisoDto)) {
            Long idAccount = entidadServiceImpl.fetchEntidadByDocumentId(avisoDto.getCustomer_id());
            String token = parameterService.fetchTokenByParameterName("PMC-TOKEN");

            if (avisoDto.getAccess_token().contains(token)) {
                // guardo consulta
                ReceiveAndSend rasConsulta = new ReceiveAndSend();
                // receiveAndSend.setId(0);
                rasConsulta.setCreatedOn(Calendar.getInstance().getTime());
                rasConsulta.setIdOrigin(idOrigin);
                rasConsulta.setIdAccount(idAccount);
                rasConsulta.setReceiveSend(1);
                rasConsulta.setMessagge(avisoDto.toString());
                receiveAndSendRepository.save(rasConsulta);
                //
                if (idAccount != null) {
                    // proc_cash_in_app
                    double QPAGO = Double.parseDouble(avisoDto.getAmount());
                    String QORIGEN = "pagomiscuentas";
                    Long QID_ENTIDAD = idAccount;
                    // llamada a procedimiento para registrar el pago
                    String resProcCashInApp = entidadServiceImpl.ingresoDineroProc(QID_ENTIDAD, QPAGO, QORIGEN);
                    ReceiveAndSend rasRespuesta = new ReceiveAndSend();
                    rasRespuesta.setCreatedOn(Calendar.getInstance().getTime());
                    rasRespuesta.setIdAccount(QID_ENTIDAD);
                    rasRespuesta.setIdOrigin(idOrigin);
                    rasRespuesta.setReceiveSend(2);
                    rasRespuesta.setMessagge("{\"result\":" + resProcCashInApp + ", \"customerId\": \""
                            + avisoDto.getCustomer_id() + "\", \"amount\": \"" + avisoDto.getAmount() + "\"}");
                    receiveAndSendRepository.save(rasRespuesta);
                    responseBody.put("code", "200");
                    responseBody.put("message", "OK");
                    return new ResponseEntity<>(responseBody, HttpStatus.OK);
                } else {
                    ReceiveAndSend rasError = new ReceiveAndSend();
                    rasError.setCreatedOn(Calendar.getInstance().getTime());
                    rasError.setIdAccount(null);
                    rasError.setIdOrigin(idOrigin);
                    rasError.setReceiveSend(2);
                    rasError.setMessagge(
                            "{\"error\":\"documento no encontrado\", \"solicitud\":" + avisoDto.toString() + "}");
                    receiveAndSendRepository.save(rasError);
                    responseBody.put("code", "200");
                    responseBody.put("message", "OK");
                    return new ResponseEntity<>(responseBody, HttpStatus.OK);
                }
            } else {
                responseBody.put("code", "400");
                responseBody.put("message", "Error de autenticacion");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseBody.put("code", "400");
            responseBody.put("message", "Error de Autenticación");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }

}
