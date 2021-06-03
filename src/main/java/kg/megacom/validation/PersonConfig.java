package kg.megacom.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PersonConfig {
    @Bean
    public List<PersonForm> getPerson(){
        return new ArrayList<>();
    }
}
