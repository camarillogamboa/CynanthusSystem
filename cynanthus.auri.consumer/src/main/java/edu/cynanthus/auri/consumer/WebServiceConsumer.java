package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.net.http.client.WebConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

@FunctionalInterface
interface WebServiceConsumer {
    HttpResponse<InputStream> consume(WebConsumer webConsumer)
        throws ServiceException, IOException, InterruptedException;

}
