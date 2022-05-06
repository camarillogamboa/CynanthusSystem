package edu.cynanthus.auri.server.service;

import com.google.gson.JsonSyntaxException;
import edu.cynanthus.auri.api.error.IOServiceException;
import edu.cynanthus.auri.api.error.InterruptedServiceException;
import edu.cynanthus.auri.api.error.RemoteServiceException;
import edu.cynanthus.auri.api.error.ServiceException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.client.HttpRequester;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.common.security.AgentUser;
import edu.cynanthus.common.security.Encryption;
import edu.cynanthus.common.security.SystemUser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.function.Consumer;

class MicroServiceConsumer {

    private final LazyRequest lazyRequest;

    MicroServiceConsumer() {
        this.lazyRequest = new HttpRequester().lazyRequest();

        SystemUser requestUser = AgentUser.DEFAUL_AGENT_USER;

        String[] credentials = requestUser.getCredentials();

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
                if (HttpStatus.isCorrect(response.statusCode())) {
                    if (returnType.equals(String.class)) return (T) StreamUtil.toString(reader);
                    return JsonProvider.fromJson(reader, returnType);
                } else throw new RemoteServiceException(StreamUtil.toString(reader));
            }

        } catch (IOException ex) {
            throw new IOServiceException("Error de IO", ex);
        } catch (InterruptedException ex) {
            throw new InterruptedServiceException("Interrupción de proceso de petición", ex);
        } catch (JsonSyntaxException ex) {
            throw new ServiceException("Error en los datos de respuesta desde el microservicio", ex);
        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException("Excepción al realizar la petición", ex);
        }
    }

}
