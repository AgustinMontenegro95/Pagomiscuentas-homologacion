package ar.com.dinamicaonline.pagomiscuentas.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;
import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.repositories.ReceiveAndSendRepository;

@Service
public class ReceiveAndSendServiceImpl implements ReceiveAndSendService {

    @Autowired
    private ReceiveAndSendRepository receiveAndSendRepository;

    @Autowired
    private EntidadServiceImpl entidadServiceImpl;

    @Override
    public ReceiveAndSend saveReceiveAndSend(ReceiveAndSend receiveAndSend, AvisoDto avisoDto) {
        Long idAccount = entidadServiceImpl.fetchEntidadByDocumentId(avisoDto.getCustomer_id());
        int idOrigin = 6;

        if (idAccount != null) {
            // proc_cash_in_app
            double QPAGO = Double.parseDouble(avisoDto.getAmount());
            String QORIGEN = "pagomiscuentas";
            Long QID_ENTIDAD = idAccount;
            // llamada a procedimiento para registrar el pago
            String resProcCashInApp = entidadServiceImpl.ingresoDineroProc(QID_ENTIDAD, QPAGO, QORIGEN);
            ReceiveAndSend receiveAndSend2 = new ReceiveAndSend();
            receiveAndSend2.setCreatedOn(Calendar.getInstance().getTime());
            receiveAndSend2.setIdAccount(QID_ENTIDAD);
            receiveAndSend2.setIdOrigin(idOrigin);
            receiveAndSend2.setReceiveSend(2);

            receiveAndSend2.setMessagge("{\"result\":" + resProcCashInApp + ", \"customerId\": \""
                    + avisoDto.getCustomer_id() + "\", \"amount\": \"" + avisoDto.getAmount() + "\"}");
            // receiveAndSendRepository.save(receiveAndSend2);
            //
            receiveAndSend.setIdAccount(idAccount);
            receiveAndSendRepository.save(receiveAndSend);
            return receiveAndSendRepository.save(receiveAndSend2);
        } else {
            ReceiveAndSend rasError = new ReceiveAndSend();
            rasError.setCreatedOn(Calendar.getInstance().getTime());
            rasError.setIdAccount(null);
            rasError.setIdOrigin(idOrigin);
            rasError.setReceiveSend(2);
            rasError.setMessagge(
                    "{\"error\":\"Documento no encontrado\", \"request\":" + avisoDto.toString() + "}");
            receiveAndSendRepository.save(rasError);
        }

        return null;
    }

}
