package club.sdll.ptc.sharding.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.algorithm.sharding.mod.ModShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;

import java.util.Collection;
import java.util.Optional;

/**
 * 表分片算法
 *
 * @author 草薙
 * @since 2022/4/15
 */
@Slf4j
public class TableShardingAlgorithm implements StandardShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String value = shardingValue.getValue();
        Integer gid = Integer.valueOf(value);
        int mod = (gid / 2) % 4;

        log.info("mod = {}, table collection is {}", mod, JSON.toJSONString(availableTargetNames));
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
