package edu.cynanthus.auri.consumer;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.AuriService;
import edu.cynanthus.auri.api.exception.ServiceException;
import edu.cynanthus.auri.api.exception.SyntaxException;
import edu.cynanthus.auri.api.exception.WebClientException;
import edu.cynanthus.auri.api.exception.WebServiceException;
import edu.cynanthus.bean.ErrorMessage;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.common.net.http.client.LazyRequest;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.util.function.Consumer;

class ServiceConsumer implements AuriService {

    private static final Type ERROR_MESSAGE_TYPE = new TypeToken<ErrorMessage<String>>() {}.getType();

    private final LazyRequest lazyRequest;

    ServiceConsumer(LazyRequest lazyRequest) {
        this.lazyRequest = lazyRequest;
    }

    <T> T consume(Consumer<LazyRequest> lazyRequestConsumer, Type returnType) {
        try {

            LazyRequest lazyRequestCopy = lazyRequest.clone();

            lazyRequestConsumer.accept(lazyRequestCopy);

            HttpResponse<InputStream> response = lazyRequestCopy.doRequestAndGetInputStream();

            try (Reader reader = new InputStreamReader(response.body())) {
                if (HttpStatusCode.isCorrect(response.statusCode()))
                    return JsonProvider.fromJson(reader, returnType);
                else {

                    ErrorMessage<String> errorMessage = JsonProvider.fromJson(reader, ERROR_MESSAGE_TYPE);

                    throw new WebServiceException(
                        "El servidor Auri ha respondido con un código de error",
                        errorMessage
                    );
                }
            }
        } catch (InterruptedException ex) {
            throw new WebClientException("Proceso de petición hacia Auri Server interrumpido", ex);
        } catch (IllegalArgumentException ex) {
            throw new WebClientException("Argumentos para el cliente web ilegales");
        } catch (SecurityException ex) {
            throw new WebClientException("Vulneración de la seguridad al realizar la petición hacia Auri Server", ex);
        } catch (HttpConnectTimeoutException ex) {
            throw new WebClientException("Tiempo de conexión expirado", ex);
        } catch (HttpTimeoutException ex) {
            throw new WebClientException("Tiempo muerto de conexión", ex);
        } catch (ConnectException ex) {
            throw new WebClientException("No se pudo establecer la conexión con el servidor Auri", ex);
        } catch (SSLHandshakeException ex) {
            throw new WebClientException("Error de intercambio de contexto SSL", ex);
        } catch (SSLException ex) {
            throw new WebClientException("Error de contexto de SSL", ex);
        } catch (IOException ex) {
            throw new WebClientException("Error de entrada y salida", ex);
        } catch (JsonSyntaxException ex) {
            throw new SyntaxException("Sintaxis de los datos de respuesta erróneos", ex);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException("Error producido al comunicarse con el servidor Auri", ex);
        }
    }

}
