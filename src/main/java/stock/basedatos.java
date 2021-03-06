package stock;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(productorepositorio repository) {
        return args -> {
            log.info("Preloading " + repository.save(new producto("Aflazacort", "Inmunodepresor")));
            log.info("Preloading " + repository.save(new producto("NAproxeno sodico", "Antiinflamatorio")));
            productorepositorio.save(new Order("MacBook Pro", Status.COMPLETED));
            productorepositorio.save(new Order("iPhone", Status.IN_PROGRESS));

            productorepositorio.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }

}