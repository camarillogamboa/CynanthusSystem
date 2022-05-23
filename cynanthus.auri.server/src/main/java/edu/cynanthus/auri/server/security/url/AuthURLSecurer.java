package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.AuthService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class AuthURLSecurer extends CommonURLSecurer {

    public AuthURLSecurer() {
        super(AuthService.AUTH_SERVICE_PATH);
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return authorizer.antMatchers(HttpMethod.POST, baseURL + "/login").permitAll();
    }

}
