package xyz.wochib70.seata.example.at;

import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.util.Random;

@RestController
@SpringBootApplication
public class SeataATProviderTwoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SeataATProviderTwoApplication.class, args);
    }


    @Resource
    JdbcTemplate jdbcTemplate;

    Random random = new Random();

    @PostMapping("/at/provider/two")
    public boolean insert(@RequestParam("userId") String userId) {
        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement("""
                    INSERT INTO provider_tow (user_id, name) VALUES (?, ?)
                    """);
            pst.setString(1, userId);
            pst.setString(2, "test");
            return pst;
        });
        if (random.nextBoolean()) {
            throw new RuntimeException("发生异常进行回滚");
        }
        return true;
    }
}
