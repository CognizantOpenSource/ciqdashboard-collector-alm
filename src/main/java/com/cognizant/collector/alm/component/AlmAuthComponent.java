package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.client.AlmClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AlmAuthComponent {
    @Autowired
    AlmClient almClient;

    private String cookies = "";


    public void loginALM() {
        log.info("Signing In");
        Map<String, Collection<String>> headers = almClient.signIn().headers();

        Collection<String> setCookies = headers.get("Set-Cookie");
        this.setCookies(setCookies.stream().collect(Collectors.joining(";")));
        log.info("Cookies Saved !");
    }

    public void logoutALM() {
        almClient.signOut(cookies);
        this.setCookies("");
    }

    public void refreshALM() {
        logoutALM();
        loginALM();
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }
}
