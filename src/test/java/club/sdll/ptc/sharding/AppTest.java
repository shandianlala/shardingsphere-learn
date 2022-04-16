package club.sdll.ptc.sharding;

import static org.junit.Assert.assertTrue;

import club.sdll.ptc.sharding.dao.entity.StorageBucketRuleDO;
import club.sdll.ptc.sharding.dao.mapper.StorageBucketRuleDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testInsertData() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");


        StorageBucketRuleDOMapper storageBucketRuleDOMapper = ApplicationContextHelper.getBean(StorageBucketRuleDOMapper.class);

        for (int i = 0; i < 20; i++) {
            StorageBucketRuleDO temp = new StorageBucketRuleDO();
            temp.setFileType("PIC");
            temp.setStorageAccountId(1L);
            temp.setStorageDay(7);
            temp.setTenantId(String.valueOf(i));
            temp.setExtInfo("{}");
            temp.setIsDeleted((byte) 0);
            int insert = storageBucketRuleDOMapper.insert(temp);
            log.info("insert flag = {}", insert == 1);
        }

    }


    @Test
    public void testSelectData() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        StorageBucketRuleDOMapper storageBucketRuleDOMapper = ApplicationContextHelper.getBean(StorageBucketRuleDOMapper.class);

        // Actual SQL: sdll_slave_1 ::: storage_bucket_rule_3
        StorageBucketRuleDO storageBucketRuleDO = storageBucketRuleDOMapper.selectByTenantIdAndId(2L, "15");

        log.info("storageBucketRuleDO = {}", storageBucketRuleDO);
    }


}
