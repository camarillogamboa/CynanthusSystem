package edu.cynanthus.auri.server.config;

import edu.cynanthus.auri.api.exception.*;
import edu.cynanthus.bean.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleTransactionSystemException(
        TransactionSystemException ex,
        WebRequest request
    ) {
        Throwable cause = ex.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException) cause, request);
        } else {
            return commonHandler(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
        ConstraintViolationException constraintViolationException,
        WebRequest request
    ) {
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
        List<String> causes = new LinkedList<>();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        constraintViolations.forEach(constraintViolation -> causes.add(constraintViolation.getMessage()));

        ErrorMessage<String> errorMessage = new ErrorMessage<>(
            status.value(),
            "Se han violado algunas restricciones al validar el elemento",
            causes
        );

        return commonHandler(
            constraintViolationException,
            errorMessage,
            new HttpHeaders(),
            status,
            request
        );
    }

    @ExceptionHandler({
        ServiceException.class,
        InvalidArgumentException.class,
        InvalidTypeException.class,
        ResourceNotFoundException.class,
        SyntaxException.class,
        WebClientException.class,
        WebServiceException.class
    })
    public final ResponseEntity<Object> handleServiceException(
        ServiceException serviceException,
        WebRequest request
    ) {
        HttpHeaders headers = new HttpHeaders();

        if (serviceException instanceof InvalidArgumentException) {
            return commonHandler(
                serviceException,
                headers,
                HttpStatus.BAD_REQUEST,
                request
            );
        } else if (serviceException instanceof InvalidTypeException) {
            return commonHandler(
                serviceException,
                headers,
                HttpStatus.NOT_ACCEPTABLE,
                request
            );
        } else if (serviceException instanceof ResourceNotFoundException) {
            return commonHandler(
                serviceException,
                headers,
                HttpStatus.NOT_FOUND,
                request
            );
        } else if (serviceException instanceof SyntaxException) {
            return commonHandler(
                serviceException,
                headers,
                HttpStatus.BAD_GATEWAY,
                request
            );
        } else if (serviceException instanceof WebClientException) {
            return commonHandler(
                serviceException,
                headers,
                HttpStatus.SERVICE_UNAVAILABLE,
                request
            );
        } else if (serviceException instanceof WebServiceException) {
            return handleWebServiceException(
                (WebServiceException) serviceException,
                headers,
                HttpStatus.FAILED_DEPENDENCY,
                request
            );
        } else {
            return commonHandler(serviceException, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    public ResponseEntity<Object> handleWebServiceException(
        WebServiceException webServiceException,
        HttpHeaders headers,
        HttpStatus httpStatus,
        WebRequest request
    ) {
        ErrorMessage<String> serviceMessage = webServiceException.getErrorMessage();

        String subMessage = "[Code:" + serviceMessage.getCode() + "] " + serviceMessage.getMessage();

        ErrorMessage<String> errorMessage = new ErrorMessage<>(
            httpStatus.value(),
            webServiceException.getMessage() + "->" + subMessage,
            webServiceException.getErrorMessage().getCauses()
        );
        return commonHandler(webServiceException, errorMessage, headers, httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return commonHandler(ex, headers, status, request);
    }

    private ResponseEntity<Object> commonHandler(
        Throwable throwable,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        ErrorMessage<String> errorMessage = new ErrorMessage<>(status.value(), throwable.getMessage());
        return commonHandler(throwable, errorMessage, headers, status, request);
    }

    private ResponseEntity<Object> commonHandler(
        Throwable ex,
        Object body,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }

}
