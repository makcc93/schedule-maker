package pl.mateuszkruk;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import pl.mateuszkruk.UserInput.InputHandler;

import java.util.Random;

@Configuration
public class AppConfiguration {
    private DaysOfWeek daysOfWeek;
    private InputHandler inputHandler;

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public DaysOfWeek daysOfWeek() {
        return daysOfWeek;
    }

    @Bean
    public InputHandler inputHandler() {
        return new InputHandler();
    }

}
