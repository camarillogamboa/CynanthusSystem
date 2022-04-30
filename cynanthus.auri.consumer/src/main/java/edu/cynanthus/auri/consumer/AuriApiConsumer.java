package edu.cynanthus.auri.consumer;

import com.google.gson.JsonSyntaxException;
import edu.cynanthus.auri.api.ExceptionRecord;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.common.net.http.HttpStatus;
import edu.cynanthus.common.net.http.client.WebConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;

class AuriApiConsumer {

    private final WebConsumer webConsumer;

    AuriApiConsumer(ClientInfo clientInfo) {
        this.webConsumer = new WebConsumer(clientInfo);
    }

    <T> T consumeApi(WebServiceConsumer webServiceConsumer, Type type) {
        try {
            HttpResponse<InputStream> response = webServiceConsumer.consume(webConsumer);

            try (Reader reader = new InputStreamReader(response.body())) {
                if (HttpStatus.isCorrect(response.statusCode())) {
                    return JsonProvider.fromJson(reader, type);
                } else {
                    ExceptionRecord exceptionRecord = JsonProvider.fromJson(reader, ExceptionRecord.class);

                    throw new ServiceException(
                        exceptionRecord.getMessage(),
                        exceptionRecord.getExceptionType(),
                        exceptionRecord.getSubErrors()
                    );
                }
            }
        } catch (IOException e) {
            throw new ServiceException("Error de IO", ExceptionType.IO, e);
        } catch (InterruptedException e) {
            throw new ServiceException("Proceso interrumpido", ExceptionType.INTERRUPTED, e);
        } catch (JsonSyntaxException ex) {
            throw new ServiceException("Error en los datos de respuesta", ExceptionType.INVALID_DATA, ex);
        }
    }

}
