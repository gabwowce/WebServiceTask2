package lt.eif.viko.gtamaseviciute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Pagrindinė aplikacijos klasė, kuri paleidžia Spring Boot programą.
 * <p>
 * Aplikacija teikia SOAP paslaugas studentų duomenų valdymui,
 * įskaitant studentų paiešką, visų studentų sąrašo gavimą bei paiešką pagal grupę.
 * </p>
 */
@SpringBootApplication
public class WebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
}
