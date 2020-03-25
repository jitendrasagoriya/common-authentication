package org.js.autenticationclient;

import org.apache.commons.configuration2.ex.ConfigurationException;
import static org.assertj.core.api.Assertions.assertThat;
import org.js.autenticationclient.bean.CommonConfiguration;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CommonConfigurationTest {

    @Test
    public void testConfig() throws ConfigurationException {
       String appId =  CommonConfiguration.getProperties("app.id");
       assertThat(appId)
               .isEqualTo("RKLSU1RET0NUT1JBUFA29XI7R0UAV1584903618054");

        String appAccess =  CommonConfiguration.getProperties("app.access");
        assertThat(appAccess)
                .isEqualTo("BNDBNBM878UURY");
    }

    @Test
    public void testSaveConfig() throws ConfigurationException {
        CommonConfiguration.addNewProperties("app.new","new value");

        String newValue =  CommonConfiguration.getProperties("app.new");
        assertThat(newValue)
                .isEqualTo("new value");

    }
}
