package edu.cynanthus.auri.server.config;

import edu.cynanthus.auri.api.ExceptionRecord;
import edu.cynanthus.auri.api.ServiceException;
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

/**
 * El tipo Web exception handler.
 */
@RestControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler service exception response entity.
     *
     * @param serviceException el service exception
     * @return el response entity
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionRecord> handlerServiceException(ServiceException serviceException) {
        HttpStatus status;

        serviceException.printStackTrace();

        switch (serviceException.getErrorType()) {
            case REQUIRED_DATA:
            case NULL_POINTER:
            case INVALID_DATA:
            case INVALID_ID:
                status = HttpStatus.BAD_REQUEST;
                break;
            case NO_EXISTING_ELEMENT:
                status = HttpStatus.NOT_FOUND;
                break;
            case EXISTING_ELEMENT:
                status = HttpStatus.NOT_ACCEPTABLE;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(serviceException.toExceptionRecord(), status);
    }

    /**
     * Handle http request method not supported response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    /**
     * Handle http media type not supported response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    /**
     * Handle http media type not acceptable response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
        HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    /**
     * Handle missing path variable response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
        MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    /**
     * Handle missing servlet request parameter response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    /**
     * Handle servlet request binding exception response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
        ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    /**
     * Handle conversion not supported response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
        ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    /**
     * Handle type mismatch response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
        TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    /**
     * Handle http message not readable response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    /**
     * Handle http message not writable response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    /**
     * Handle method argument not valid response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    /**
     * Handle missing servlet request part response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
        MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    /**
     * Handle bind exception response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleBindException(
        BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleBindException(ex, headers, status, request);
    }

    /**
     * Handle no handler found exception response entity.
     *
     * @param ex      el ex
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    /**
     * Handle async request timeout exception response entity.
     *
     * @param ex         el ex
     * @param headers    el headers
     * @param status     el status
     * @param webRequest el web request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
        AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest
    ) {
        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
    }

    /**
     * Handle exception internal response entity.
     *
     * @param ex      el ex
     * @param body    el body
     * @param headers el headers
     * @param status  el status
     * @param request el request
     * @return el response entity
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
