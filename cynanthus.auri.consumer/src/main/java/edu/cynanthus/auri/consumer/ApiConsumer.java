package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.net.http.client.WebServiceConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

@FunctionalInterface
interface ApiConsumer {

    HttpResponse<InputStream> consume(WebServiceConsumer webServiceConsumer)
        throws ServiceException, IOException, InterruptedException;

}
