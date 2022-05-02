package edu.cynanthus.common.net.http.client;

import java.net.http.HttpResponse;

@FunctionalInterface
public interface RequestingClientExecutor<T> {

    HttpResponse<T> execute(RequestingClient requestingClient) throws Exception;

}
