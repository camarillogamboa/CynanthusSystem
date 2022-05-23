package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.NodeInfoService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class NodeInfoURLSecurer extends BeanURLSecurer {

    public NodeInfoURLSecurer() {
        super(NodeInfoService.NODE_INFO_SERVICE_PATH);
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return super.configure(authorizer).antMatchers(HttpMethod.GET, baseURL + "/of/**").hasAnyAuthority(ALL_ROLES);
    }

}
