package ru.itis.info.semesterwork.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.itis.info.semesterwork.filter.EncodingFilter;
import ru.itis.info.semesterwork.interceptor.Interceptor;
import ru.itis.info.semesterwork.util.AnnotationRegister;

import javax.servlet.Filter;
import java.util.Set;

@Log
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Set<GenericConverter> converters;

    @Autowired
    private EncodingFilter encodingFilter;

    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;

    @Autowired
    private AnnotationRegister register;

    @Override
    public Validator getValidator() {
        return validatorFactoryBean;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        codesResolver.setMessageCodeFormatter(DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
        return codesResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        register.getBeansByAnnotationWithData(HandlerInterceptor.class, Interceptor.class)
                .forEach((HandlerInterceptor key, Interceptor value) ->
                        registry.addInterceptor(key)
                                .addPathPatterns(value.pathPatterns())
                                .excludePathPatterns(value.excludePathPatterns())
                                .order(value.order()));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        converters.forEach(registry::addConverter);
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(encodingFilter);
        filterRegistrationBean.addUrlPatterns("/**");
        return filterRegistrationBean;
    }
}
