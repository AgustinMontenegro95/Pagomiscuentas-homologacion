package ar.com.dinamicaonline.pagomiscuentas.service;

public interface ParameterService {

    String fetchTokenById(int id);

    String fetchTokenByParameterName(String parameterName);
}
