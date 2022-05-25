package ru.itis.info.semesterwork.interceptor;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import ru.itis.info.semesterwork.util.JsonParser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Interceptor(order = 1)
@Log
public class LocaleDefineInterceptor implements HandlerInterceptor {

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private Environment environment;

    @Autowired
    private JsonParser jsonParser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && Arrays.stream(request.getCookies()).anyMatch(cookie -> cookie.getName().equals("localeInfo"))) {
            return true;
        }
        String ip = request.getRemoteAddr();
        Optional<String> localeIp = jsonParser.getPropertyFromInputStream(new URL
                (environment.getProperty("ip.api.url") + ip).openStream(),
                environment.getProperty("ip.codename"));
        String locale = localeIp.orElseGet(() -> request.getHeader("Accept-Language"));
        log.info("LocaleDefineInterceptor");
        localeResolver.setLocale(request, response, Locale.forLanguageTag(locale.toLowerCase(Locale.ROOT)));
        return true;
    }
}
