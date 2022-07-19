package club.sdll.ptc.sharding;

import static org.junit.Assert.assertTrue;

import club.sdll.ptc.sharding.dao.entity.StorageBucketRuleDO;
import club.sdll.ptc.sharding.dao.mapper.StorageBucketRuleDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
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

    @Test
    public void testSelectDataByHint() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        StorageBucketRuleDOMapper storageBucketRuleDOMapper = ApplicationContextHelper.getBean(StorageBucketRuleDOMapper.class);

        HintManager hintManager = HintManager.getInstance();
        // 指定数据库分片：_1, 可在hint自定义算法中调整
        hintManager.addDatabaseShardingValue("storage_bucket_rule", "1");

        // 指定数据表分片：_3, 可在hint自定义算法中调整
        hintManager.addTableShardingValue("storage_bucket_rule", "3");

        // 最终sql: Actual SQL: sdll_slave_1 ::: storage_bucket_rule_3
        StorageBucketRuleDO storageBucketRuleDO = storageBucketRuleDOMapper.selectByTenantIdAndId(2L, "15");

        hintManager.clearShardingValues();
        log.info("storageBucketRuleDO = {}", storageBucketRuleDO);
    }


}
