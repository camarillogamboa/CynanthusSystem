package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.domain.StringRoles;

abstract class CommonURLSecurer implements URLSecurer, StringRoles {

    String baseURL;

    CommonURLSecurer(String baseURL) {
        this.baseURL = baseURL;
    }

}
