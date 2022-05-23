package edu.cynanthus.auri.server.security.url;

import edu.cynanthus.auri.api.InstructionSetService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class InstructionSetURLSecurer extends BeanURLSecurer {

    public InstructionSetURLSecurer() {
        super(InstructionSetService.INSTRUCTION_SET_SERVICE_PATH);
    }

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        return super.configure(authorizer).antMatchers(
                HttpMethod.GET,
                baseURL + "/instruction/**",
                baseURL + "/instruction/**/**"
            ).hasAnyAuthority(ALL_ROLES)
            .antMatchers(
                HttpMethod.DELETE,
                baseURL + "/instruction/**",
                baseURL + "/instruction/**/**"
            ).hasAnyAuthority(TOP_LEVEL_ROLES);
    }

}
