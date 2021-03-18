
/*
 *    © [2021] Cognizant. All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http:www.apache.orglicensesLICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.client.AlmClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * AlmAuthComponent
 *
 * @author Cognizant
 */

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
