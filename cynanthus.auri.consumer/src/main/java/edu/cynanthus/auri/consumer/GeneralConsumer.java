package edu.cynanthus.auri.consumer;

import com.google.gson.JsonSyntaxException;
import edu.cynanthus.auri.api.error.*;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.client.LazyRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

class GeneralConsumer {

    private final LazyRequest lazyRequest;

    GeneralConsumer(LazyRequest lazyRequest) {
        this.lazyRequest = lazyRequest;
    }

    <T> T consume(Consumer<LazyRequest> lazyRequestConsumer, Type returnType) {
        try {

            LazyRequest lazyRequestCopy = lazyRequest.clone();

            lazyRequestConsumer.accept(lazyRequestCopy);

            HttpResponse<InputStream> response = lazyRequestCopy.doRequestAndGetInputStream();

            try (Reader reader = new InputStreamReader(response.body())) {
                if (HttpStatus.isCorrect(response.statusCode())) return JsonProvider.fromJson(reader, returnType);
                else {
                    MessageNode exceptionRecord = JsonProvider.fromJson(reader, MessageNode.class);

                    throw new RemoteServiceException(
                        exceptionRecord.getMessage(),
                        exceptionRecord.getExceptionNodes()
                    );
                }
            }
        } catch (IOException ex) {
            throw new IOServiceException("Error de IO", ex);
        } catch (InterruptedException ex) {
            throw new InvalidDataException("Proceso interrumpido", ex);
        } catch (JsonSyntaxException ex) {
            throw new ServiceException("Error en los datos de respuesta", ex);
        } catch (ServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServiceException("Excepción al realizar la petición", ex);
        }
    }

}
