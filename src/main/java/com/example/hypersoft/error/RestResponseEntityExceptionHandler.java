package com.example.hypersoft.error;

import com.example.hypersoft.errormodel.OrderErrorMessage;
import com.example.hypersoft.errormodel.TransactionErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<OrderErrorMessage> orderErrorException(OrderException exception, WebRequest request) {
        OrderErrorMessage message = new OrderErrorMessage(exception.getMessage());
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<TransactionErrorMessage> transactionErrorExecution(TransactionException exception, WebRequest request) {
        TransactionErrorMessage message = new TransactionErrorMessage(exception.getMessage());
        return ResponseEntity.status(400).body(message);
    }


}
