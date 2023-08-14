package GestionPersonnel.gestionBugetMAJ.swaggerconfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API GESTION DE BUDGET")
                        .description("API GESTION DE BUDGET")
                        .version("1.0"));
    }
}
