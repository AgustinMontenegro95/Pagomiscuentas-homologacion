package ar.com.dinamicaonline.pagomiscuentas.service;

public interface EntidadService {

    // Read operation
    Long fetchEntidadByDocumentId(String customerId);

    String ingresoDineroProc(Long qIdEntidad, Double qPago, String qOrigen);
}
