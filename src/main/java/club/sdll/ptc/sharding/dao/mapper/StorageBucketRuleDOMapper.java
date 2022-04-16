package club.sdll.ptc.sharding.dao.mapper;

import club.sdll.ptc.sharding.dao.entity.StorageBucketRuleDO;
import org.apache.ibatis.annotations.Param;

public interface StorageBucketRuleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StorageBucketRuleDO record);

    int insertSelective(StorageBucketRuleDO record);

    StorageBucketRuleDO selectByPrimaryKey(Long id);

    StorageBucketRuleDO selectByTenantIdAndId(@Param("id") Long id, @Param("tenantId") String tenantId);

    int updateByPrimaryKeySelective(StorageBucketRuleDO record);

    int updateByPrimaryKey(StorageBucketRuleDO record);
}