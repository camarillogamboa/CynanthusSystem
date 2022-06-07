/**
 *
 */
class HttpClientRedirector extends HttpClient {

    async doRequest(request) {
        return super.doRequest(request)
            .then(response => {
                if (response.redirected) window.location = response.url;
                else return response;
            });
    }

}