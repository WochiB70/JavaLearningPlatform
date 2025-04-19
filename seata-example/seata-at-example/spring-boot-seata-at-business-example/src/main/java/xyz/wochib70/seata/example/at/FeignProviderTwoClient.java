package xyz.wochib70.seata.example.at;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider-two", url = "http://localhost:6062")
public interface FeignProviderTwoClient {


    @PostMapping("/at/provider/two")
    public boolean insert(@RequestParam("userId") String userId);
}
