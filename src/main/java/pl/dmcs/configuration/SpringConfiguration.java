package pl.dmcs.configuration;

import jakarta.annotation.Resource;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.dmcs.service.AddressService;
import pl.dmcs.utils.AddressConverter;
import pl.dmcs.utils.AppUserRoleConverter;
import pl.dmcs.utils.AppUserRoleListConverter;
import pl.dmcs.service.AppUserRoleService;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("pl.dmcs")
public class SpringConfiguration implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/resources/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    // Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieName("myLocaleCookie");
        resolver.setCookieMaxAge(4800);

        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        interceptorRegistry.addInterceptor(interceptor);
    }

    @Bean
    @Override
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Resource(name="addressService")
    private AddressService addressService;

    @Resource(name="appUserRoleService")
    private AppUserRoleService appUserRoleService;

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry)
    {
        formatterRegistry.addConverter(getMyAddressConverter());
        formatterRegistry.addConverter(getMyUserRoleConverter());
        formatterRegistry.addConverter(getMyUserRoleListConverter());
    }

    @Bean
    public AddressConverter getMyAddressConverter() {
        return new AddressConverter(addressService);
    }

    @Bean
    public AppUserRoleConverter getMyUserRoleConverter() {
        return new AppUserRoleConverter(appUserRoleService);
    }

    @Bean
    public AppUserRoleListConverter getMyUserRoleListConverter() {
        return new AppUserRoleListConverter(appUserRoleService);
    }
}
