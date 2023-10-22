package game.you.conf;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class Conf {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
