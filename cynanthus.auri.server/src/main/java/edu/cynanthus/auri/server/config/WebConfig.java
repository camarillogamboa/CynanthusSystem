package edu.cynanthus.auri.server.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cynanthus.domain.InstructionSet;
import edu.cynanthus.domain.NodeInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(ServerInfo.class, BeanInstanceCreator.SERVER_INFO_BEAN_INSTANCE_CREATOR);
        builder.registerTypeAdapter(NodeInfo.class, BeanInstanceCreator.NODE_INFO_BEAN_INSTANCE_CREATOR);
        builder.registerTypeAdapter(User.class, BeanInstanceCreator.USER_BEAN_INSTANCE_CREATOR);
        builder.registerTypeAdapter(InstructionSet.class, BeanInstanceCreator.INSTRUCTION_SET_BEAN_INSTANCE_CREATOR);
        return builder.create();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(itm -> itm.getSupportedMediaTypes().contains(MediaType.APPLICATION_JSON));

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();

        stringConverter.setWriteAcceptCharset(false);
        stringConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));

        converters.add(stringConverter);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());

        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson());
        gsonHttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        converters.add(gsonHttpMessageConverter);
    }

}
