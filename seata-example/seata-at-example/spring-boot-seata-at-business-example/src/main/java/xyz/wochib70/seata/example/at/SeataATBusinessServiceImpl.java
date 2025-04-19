package xyz.wochib70.seata.example.at;


import jakarta.annotation.Resource;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service
public class SeataATBusinessServiceImpl {

    @Resource
    FeignProviderOneClient feignProviderOneClient;

    @Resource
    FeignProviderTwoClient feignProviderTwoClient;


    @GlobalTransactional(name = "seata-at-business", rollbackFor = Exception.class)
    public void doTransaction() {
        System.out.println("========= 开始业务 ==========");
        feignProviderOneClient.debit("user01");
        feignProviderTwoClient.insert("user01");
    }
}
