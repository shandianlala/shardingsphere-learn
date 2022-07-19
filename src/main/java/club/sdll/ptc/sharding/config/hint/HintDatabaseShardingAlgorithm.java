package club.sdll.ptc.sharding.config.hint;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Optional;

@Slf4j
public class HintDatabaseShardingAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        log.info("db availableTargetNames={}", JSON.toJSONString(availableTargetNames));
        Collection<String> values = shardingValue.getValues();
        log.info("db values={}", JSON.toJSONString(values));
        String tenantId = values.stream().findFirst().get();
        Optional<String> any = availableTargetNames.stream().filter(dbName -> dbName.endsWith("_" + tenantId)).findAny();
        if (any.isPresent()) {
            return Lists.newArrayList(any.get());
        } else {
            throw new IllegalArgumentException("illegal tenantId = " + tenantId);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }
}
