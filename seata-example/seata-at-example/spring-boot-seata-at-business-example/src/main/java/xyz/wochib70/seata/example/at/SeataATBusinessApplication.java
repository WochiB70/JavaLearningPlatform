package xyz.wochib70.seata.example.at;


import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SeataATBusinessApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SeataATBusinessApplication.class, args);
    }

    @Resource
    SeataATBusinessServiceImpl seataATBusinessService;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            try {
                seataATBusinessService.doTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
