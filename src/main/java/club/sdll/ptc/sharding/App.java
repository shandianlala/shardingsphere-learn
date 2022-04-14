package club.sdll.ptc.sharding;

import club.sdll.ptc.sharding.dao.entity.StorageBucketRuleDO;
import club.sdll.ptc.sharding.dao.mapper.StorageBucketRuleDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Hello world!
 */
@Slf4j
public class App {


    public static void main(String[] args) {
        System.out.println("Hello World!");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");


        StorageBucketRuleDOMapper storageBucketRuleDOMapper = ApplicationContextHelper.getBean(StorageBucketRuleDOMapper.class);

        StorageBucketRuleDO temp = new StorageBucketRuleDO();
        temp.setFileType("PIC");
        temp.setStorageAccountId(1L);
        temp.setStorageDay(7);
        temp.setTenantId("9527");
        temp.setExtInfo("{}");
        temp.setIsDeleted((byte) 0);
        int insert = storageBucketRuleDOMapper.insert(temp);
        log.info("insert flag = {}", insert == 1);

        StorageBucketRuleDO storageBucketRuleDO = storageBucketRuleDOMapper.selectByPrimaryKey(1L);

        log.info("storageBucketRuleDO = {}", storageBucketRuleDO.getTenantId());

    }
}
