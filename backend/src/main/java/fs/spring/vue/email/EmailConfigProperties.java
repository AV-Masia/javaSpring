package fs.spring.vue.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="mail")
public class EmailConfigProperties {

    private String from;
    private Register register = new Register();
    private Reset reset = new Reset();

    @Getter
    @Setter
    public static class Register{
        private String subject;
        private String message;
    }

    @Getter
    @Setter
    public static class Reset{
        private String [] subject;
        private String [] message;
    }
}
