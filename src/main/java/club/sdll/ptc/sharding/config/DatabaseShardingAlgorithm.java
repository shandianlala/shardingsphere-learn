package club.sdll.ptc.sharding.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Optional;

/**
 * 数据库分片算法
 *
 * @author 草薙
 * @since 2022/4/16
 */
@Slf4j
public class DatabaseShardingAlgorithm implements StandardShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String value = shardingValue.getValue();
        Integer gid = Integer.valueOf(value);
        int mod = gid % 2;

        log.info("mod = {}, database collection is {}", mod, JSON.toJSONString(availableTargetNames));
        Optional<String> any = availableTargetNames.stream().filter(tableName -> tableName.endsWith("_" + mod)).findAny();
        if (any.isPresent()) {
            return any.get();
        } else {
            throw new IllegalArgumentException("illegal gid = " + gid);
        }
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<String> shardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }
}
