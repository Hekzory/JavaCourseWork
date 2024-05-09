package one.tsv.medclinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MedClinicApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        String configValue = applicationContext.getEnvironment().getProperty("spring.jpa.properties.hibernate.dialect");
        assertThat(configValue).isNotNull().isEqualTo("org.hibernate.dialect.HSQLDialect");
    }

}
