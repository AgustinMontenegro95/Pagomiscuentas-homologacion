package ar.com.dinamicaonline.pagomiscuentas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.repositories.ReceiveAndSendRepository;

@Service
public class ReceiveAndSendServiceImpl implements ReceiveAndSendService {

    @Autowired
    private ReceiveAndSendRepository recieveAndSendRepository;

    @Autowired
    private EntidadServiceImpl entidadServiceImpl;

    @Override
    public ReceiveAndSend saveRecieveAndSend(ReceiveAndSend recieveAndSend) {
        Integer customerId = entidadServiceImpl.fetchEntidadByDocumentId(recieveAndSend.getIdAccount());

        if (customerId != null) {
            recieveAndSend.setIdAccount(customerId);
            recieveAndSendRepository.save(recieveAndSend);
        }

        return recieveAndSendRepository.save(recieveAndSend);
    }

}
