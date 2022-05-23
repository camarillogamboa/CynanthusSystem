package edu.cynanthus.auri.server.security.url;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

class TreeServerURLSecurer extends CynanthusServerURLSecurer {

    TreeServerURLSecurer(String baseURL) {
        super(baseURL);
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return super.configure(authorizer)
            .antMatchers(HttpMethod.GET, baseURL + "/**/node/**").hasAnyAuthority(ALL_ROLES);
    }

}
