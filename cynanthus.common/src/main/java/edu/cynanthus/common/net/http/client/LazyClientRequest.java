package edu.cynanthus.common.net.http.client;

import edu.cynanthus.common.net.ConnectionPoint;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

class LazyClientRequest extends BasicLazyRequest {

    final ConnectionPoint connectionPoint;

    LazyClientRequest(HttpClient httpClient, ConnectionPoint connectionPoint) {
        super(httpClient);
        this.connectionPoint = connectionPoint;
    }

    String buildPath() {
        return "http://" + connectionPoint.getServerName() + ":" + connectionPoint.getServerPort();
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

    @Override
    public LazyRequest clone() {
        LazyClientRequest lazyClientRequest = new LazyClientRequest(httpClient, connectionPoint);
        lazyClientRequest.builderConsumer = builderConsumer;
        return lazyClientRequest;
    }

}
