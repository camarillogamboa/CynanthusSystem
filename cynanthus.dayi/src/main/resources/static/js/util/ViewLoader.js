/**
 *
 */
class ViewLoader {

    #httpClient;

    #waitContent;

    constructor(httpClient, waitContent) {
        this.#httpClient = httpClient;

        this.#waitContent = waitContent;
    }

    async loadAndPlaceTo(url, area) {
        if (typeof area === 'string') area = $(area);

        if (this.#waitContent != null) area.html(this.#waitContent);

        return this.#httpClient.GET(url)
            .then(response => response.text())
            .then(text => area.html(text));
    }

}