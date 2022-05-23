package edu.cynanthus.auri.server.security.url;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class CynanthusServerURLSecurer extends CommonURLSecurer {

    public CynanthusServerURLSecurer(String baseURL) {
        super(baseURL);
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return authorizer.antMatchers(HttpMethod.GET, baseURL + "/**/config").hasAnyAuthority(ALL_ROLES)
            .antMatchers(HttpMethod.PUT, baseURL + "/**/config").hasAnyAuthority(TOP_LEVEL_ROLES)
            .antMatchers(HttpMethod.GET, baseURL + "/**/log").hasAnyAuthority(ALL_ROLES)
            .antMatchers(HttpMethod.GET, baseURL + "/**/log/**").hasAnyAuthority(ALL_ROLES)
            .antMatchers(HttpMethod.GET, baseURL + "/**/available").hasAnyAuthority(ALL_ROLES);
    }

}
