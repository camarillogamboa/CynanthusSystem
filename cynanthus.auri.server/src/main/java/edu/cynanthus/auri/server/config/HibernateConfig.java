package edu.cynanthus.auri.server.config;

import edu.cynanthus.bean.Required;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HibernateConfig implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(
            "javax.persistence.validation.group.pre-persist",
            new Class[]{Required.class}
        );
        hibernateProperties.put(
            "javax.persistence.validation.group.pre-update",
            new Class[]{Required.class}
        );
    }

}
