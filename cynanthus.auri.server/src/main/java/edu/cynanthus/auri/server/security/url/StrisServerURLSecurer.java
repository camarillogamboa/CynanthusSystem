package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.StrisServerService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class StrisServerURLSecurer extends TreeServerURLSecurer {

    public StrisServerURLSecurer() {
        super(StrisServerService.STRIS_SERVER_SERVICE_PATH);
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return super.configure(authorizer)
            .antMatchers(HttpMethod.POST, baseURL + "/**/indication").hasAnyAuthority(ALL_ROLES)
            .antMatchers(HttpMethod.GET, baseURL + "/indication/to/**/with/**").hasAnyAuthority(ALL_ROLES);
    }

}
