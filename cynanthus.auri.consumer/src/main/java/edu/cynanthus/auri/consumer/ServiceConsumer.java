package edu.cynanthus.auri.consumer;

import com.google.gson.JsonSyntaxException;
import edu.cynanthus.auri.api.ExceptionRecord;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
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

class ServiceConsumer {

    private final LazyRequest lazyRequest;

    ServiceConsumer(LazyRequest lazyRequest) {
        this.lazyRequest = lazyRequest;
    }

    <T> T consumeService(Consumer<LazyRequest> lazyRequestConsumer, Type type) {
        try {

            lazyRequestConsumer.accept(lazyRequest);

            HttpResponse<InputStream> response = lazyRequest.doRequestAndGetInputStream();

            try (Reader reader = new InputStreamReader(response.body())) {
                if (HttpStatus.isCorrect(response.statusCode())) return JsonProvider.fromJson(reader, type);
                else {
                    ExceptionRecord exceptionRecord = JsonProvider.fromJson(reader, ExceptionRecord.class);

                    throw new ServiceException(
                        exceptionRecord.getMessage(),
                        exceptionRecord.getExceptionType(),
                        exceptionRecord.getSubErrors()
                    );
                }
            }
        } catch (IOException ex) {
            throw new ServiceException("Error de IO", ExceptionType.IO, ex);
        } catch (InterruptedException ex) {
            throw new ServiceException("Proceso interrumpido", ExceptionType.INTERRUPTED, ex);
        } catch (JsonSyntaxException ex) {
            throw new ServiceException("Error en los datos de respuesta", ExceptionType.INVALID_DATA, ex);
        } catch (Exception ex) {
            throw new ServiceException("Excepción al realizar la petición", ExceptionType.INVALID_DATA);
        }
    }

}
