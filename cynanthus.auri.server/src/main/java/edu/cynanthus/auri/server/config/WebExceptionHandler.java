package edu.cynanthus.auri.server.config;

import edu.cynanthus.auri.api.error.*;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
        ExistingElementException.class,
        IncompatibleServiceException.class,
        InterruptedServiceException.class,
        InvalidDataException.class,
        IOServiceException.class,
        NoExistingElementException.class,
        NullPointerServiceException.class,
        RemoteServiceException.class,
        ServiceException.class
    })
    public ResponseEntity<MessageNode> handlerServiceException(ServiceException ex) {
        HttpStatus status;

        ex.printStackTrace();

        if (ex instanceof ExistingElementException) {
            status = HttpStatus.NOT_MODIFIED;
        } else if (ex instanceof IncompatibleServiceException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (ex instanceof InterruptedServiceException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof InvalidDataException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof InvalidDataException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof NoExistingElementException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof NullPointerServiceException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof RemoteServiceException) {
            status = HttpStatus.BAD_GATEWAY;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(ex.toExceptionNode(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
        HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
        MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
        ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
        ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
        TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
        MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
        BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleBindException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
        AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest
    ) {
        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
