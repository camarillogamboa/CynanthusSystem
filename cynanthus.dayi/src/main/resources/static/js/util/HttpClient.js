/**
 *
 */
class HttpClient {

    async GET(url) {
        let request = new Request(
            url,
            {
                credentials: "include",
                method: "GET",
                redirect: "manual"
            }
        );

        return this.doRequest(request);
    }

    async POST(url, json) {
        let request = new Request(
            url,
            {
                credentials: "include",
                headers: {'Content-Type': 'application/json'},
                method: "POST",
                redirect: "manual",
                body: json
            }
        );

        return this.doRequest(request);
    }

    async PUT(url, json) {
        let request = new Request(
            url,
            {
                credentials: "include",
                headers: {'Content-Type': 'application/json'},
                method: "PUT",
                redirect: "manual",
                body: json
            }
        );

        return this.doRequest(request);
    }

    async DELETE(url) {
        let request = new Request(
            url,
            {
                credentials: "include",
                method: "DELETE",
                redirect: "manual"
            }
        );

        return this.doRequest(request);
    }

    async doRequest(request) {
        return fetch(request)
            .then(response => {
                if (response.ok) return response;
                else response
                    .text()
                    .then(text => {
                        throw new HttpError(text, response.status)
                    });
            });
    }

}