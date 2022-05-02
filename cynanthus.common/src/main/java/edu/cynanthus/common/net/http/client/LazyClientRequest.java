package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.ClientInfo;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

class LazyClientRequest extends BasicLazyRequest {

    private final ClientInfo clientInfo;

    LazyClientRequest(HttpClient httpClient, ClientInfo clientInfo) {
        super(httpClient);
        this.clientInfo = clientInfo;
    }

    String buildPath() {
        return "http://" + clientInfo.getServerName() + ":" + clientInfo.getServerPort();
    }

    @Override
    public LazyRequest POST(String uri, HttpRequest.BodyPublisher bodyPublisher) {
        return super.POST(buildPath() + uri, bodyPublisher);
    }

    @Override
    public LazyRequest GET(String uri) {
        return super.GET(buildPath() + uri);
    }

    @Override
    public LazyRequest PUT(String uri, HttpRequest.BodyPublisher bodyPublisher) {
        return super.PUT(buildPath() + uri, bodyPublisher);
    }

    @Override
    public LazyRequest DELETE(String uri) {
        return super.DELETE(buildPath() + uri);
    }

}
