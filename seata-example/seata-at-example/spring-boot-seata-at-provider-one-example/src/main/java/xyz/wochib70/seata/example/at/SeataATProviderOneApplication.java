package xyz.wochib70.seata.example.at;


import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SeataATProviderOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataATProviderOneApplication.class, args);
    }

    @Resource
    JdbcTemplate jdbcTemplate;


    @PostMapping("/at/provider/one")
    public boolean debit(@RequestParam("userId") String userId) {
        jdbcTemplate.update("""
                UPDATE provider_one 
                SET balance = balance - 100
                WHERE user_id = ?
                """, userId);
        return true;
    }

}
