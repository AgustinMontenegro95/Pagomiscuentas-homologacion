package ar.com.dinamicaonline.pagomiscuentas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.dinamicaonline.pagomiscuentas.repositories.EntidadRepository;

@Service
public class EntidadServiceImpl implements EntidadService {

    @Autowired
    private EntidadRepository entidadRepository;

    @Override
    public Integer fetchEntidadByDocumentId(long idAccount) {
        return entidadRepository.findByDocumentId(idAccount);
    }
}