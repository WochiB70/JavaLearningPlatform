package xyz.wochib70.seata.example.at;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider-one", url = "http://localhost:6061")
public interface FeignProviderOneClient {


    @PostMapping("/at/provider/one")
    public boolean debit(@RequestParam("userId") String userId);
}
