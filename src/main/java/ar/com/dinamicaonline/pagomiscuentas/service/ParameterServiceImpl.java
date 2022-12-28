package ar.com.dinamicaonline.pagomiscuentas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.dinamicaonline.pagomiscuentas.repositories.ParameterRepository;

@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public String fetchTokenById(int id) {
        return parameterRepository.findById(id);
    }

    @Override
    public String fetchTokenByParameterName(String parameterName) {
        return parameterRepository.findByParameterName(parameterName);
    }

}
