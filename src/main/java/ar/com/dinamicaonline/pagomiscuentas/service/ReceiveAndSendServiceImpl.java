package ar.com.dinamicaonline.pagomiscuentas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.dinamicaonline.pagomiscuentas.dto.AvisoDto;
import ar.com.dinamicaonline.pagomiscuentas.models.ReceiveAndSend;
import ar.com.dinamicaonline.pagomiscuentas.repositories.ReceiveAndSendRepository;

@Service
public class ReceiveAndSendServiceImpl implements ReceiveAndSendService {

    @Autowired
    private ReceiveAndSendRepository recieveAndSendRepository;

    @Autowired
    private EntidadServiceImpl entidadServiceImpl;

    @Override
    public ReceiveAndSend saveReceiveAndSend(ReceiveAndSend recieveAndSend, AvisoDto avisoDto) {
        Integer idAccount = entidadServiceImpl.fetchEntidadByDocumentId(avisoDto.getCustomer_id());

        if (idAccount != 0) {
            recieveAndSend.setIdAccount(idAccount);
            recieveAndSendRepository.save(recieveAndSend);
        }

        return recieveAndSendRepository.save(recieveAndSend);
    }

}
