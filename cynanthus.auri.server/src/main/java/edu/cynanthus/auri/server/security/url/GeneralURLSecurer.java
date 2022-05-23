package edu.cynanthus.auri.server.security.url;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.LinkedList;
import java.util.List;

public class GeneralURLSecurer implements URLSecurer {

    @Override
    public ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
    configure(
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizer
    ) throws Exception {
        for (URLSecurer securer : loadSecurers()) authorizer = securer.configure(authorizer);
        return authorizer;
    }

    private static List<URLSecurer> loadSecurers() {
        List<URLSecurer> securers = new LinkedList<>();
        securers.add(new ServerInfoURLSecurer());
        securers.add(new NodeInfoURLSecurer());
        securers.add(new InstructionSetURLSecurer());
        securers.add(new UserURLSecurer());
        securers.add(new SordidusServerURLSecurer());
        securers.add(new LatiroServerURLSecurer());
        securers.add(new StrisServerURLSecurer());
        securers.add(new AuthURLSecurer());
        return securers;
    }

}
