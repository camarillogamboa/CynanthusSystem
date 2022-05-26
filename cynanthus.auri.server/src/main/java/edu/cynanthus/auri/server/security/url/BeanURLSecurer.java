package edu.cynanthus.auri.server.security.url;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

class BeanURLSecurer extends CommonURLSecurer {

    BeanURLSecurer(String baseURL) {
        super(baseURL);
        this.baseURL = baseURL;
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return authorizer
            .antMatchers(HttpMethod.POST, baseURL).hasAnyAuthority(TOP_LEVEL_ROLES)
            .antMatchers(HttpMethod.GET, baseURL, baseURL + "/**").hasAnyAuthority(ALL_ROLES)
            .antMatchers(HttpMethod.PUT, baseURL).hasAnyAuthority(TOP_LEVEL_ROLES)
            .antMatchers(HttpMethod.DELETE, baseURL, baseURL + "/**").hasAnyAuthority(TOP_LEVEL_ROLES);
    }

}
