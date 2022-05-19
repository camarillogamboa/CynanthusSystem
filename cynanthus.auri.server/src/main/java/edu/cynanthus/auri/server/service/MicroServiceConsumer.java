package edu.cynanthus.auri.server.service;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.exception.ServiceException;
import edu.cynanthus.auri.api.exception.SyntaxException;
import edu.cynanthus.auri.api.exception.WebClientException;
import edu.cynanthus.auri.api.exception.WebServiceException;
import edu.cynanthus.bean.ErrorMessage;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.HttpStatusCode;
import edu.cynanthus.common.net.http.client.HttpRequester;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.common.security.Encryption;
import edu.cynanthus.common.security.SystemUser;

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
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.function.Consumer;

class MicroServiceConsumer {

    private static final Type ERROR_MESSAGE_TYPE = new TypeToken<ErrorMessage<String>>() {}.getType();

    private final LazyRequest lazyRequest;

    MicroServiceConsumer() {
        this.lazyRequest = new HttpRequester().lazyRequest().building(builder ->
            builder.timeout(Duration.ofSeconds(3))
        );

        SystemUser systemUser = AgentUser.DEFAUL_AGENT_USER;

        String[] credentials = systemUser.getCredentials();

        try {
            credentials[1] = Encryption.decrypt(credentials[1]);
        } catch (GeneralSecurityException e) {
        }
        this.lazyRequest.addHeader("Credentials", credentials[0] + " " + credentials[1]);
    }

    LazyRequest getLazyRequest() {
        return lazyRequest.clone();
    }

    <T> T consume(Consumer<LazyRequest> lazyRequestConsumer, Type returnType) {
        LazyRequest lazyRequestCopy = lazyRequest.clone();

        lazyRequestConsumer.accept(lazyRequestCopy);

        try {
            HttpResponse<InputStream> response = lazyRequestCopy.doRequestAndGetInputStream();

            try (Reader reader = new InputStreamReader(response.body())) {
                if (HttpStatusCode.isCorrect(response.statusCode())) {
                    if (returnType.equals(String.class)) return (T) StreamUtil.toString(reader);
                    return JsonProvider.fromJson(reader, returnType);
                } else {

                    ErrorMessage<String> errorMessage = JsonProvider.fromJson(reader, ERROR_MESSAGE_TYPE);

                    throw new WebServiceException(
                        "El servidor de microservicio ha respondido con código de error",
                        errorMessage
                    );
                }
            }

        } catch (InterruptedException ex) {
            throw new WebClientException("Proceso de petición hacia microservicio interrumpido", ex);
        } catch (IllegalArgumentException ex) {
            throw new WebClientException("Argumentos para el cliente web ilegales", ex);
        } catch (SecurityException ex) {
            throw new WebClientException("Vulneración de la seguridad al realizar la petición hacia el microservicio", ex);
        } catch (HttpConnectTimeoutException ex) {
            throw new WebClientException("Tiempo de conexión expirado", ex);
        } catch (HttpTimeoutException ex) {
            throw new WebClientException("Tiempo muerto de conexión", ex);
        } catch (ConnectException ex) {
            throw new WebClientException("No se pudo establecer la conexión con el servidor del microservicio", ex);
        } catch (SSLHandshakeException ex) {
            throw new WebClientException("Error de intercambio de contexto SSL", ex);
        } catch (SSLException ex) {
            throw new WebClientException("Error de contexto de SSL", ex);
        } catch (IOException ex) {
            throw new WebClientException("Error de entrada y salida", ex);
        } catch (JsonSyntaxException ex) {
            throw new SyntaxException("Sintaxis de los datos de respuesta erroneos", ex);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException("Error producido al comunicarse con el microservicio", ex);
        }
    }

}
