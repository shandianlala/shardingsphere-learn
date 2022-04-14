package club.sdll.ptc.sharding.dao.mapper;

import club.sdll.ptc.sharding.dao.entity.StorageBucketRuleDO;

public interface StorageBucketRuleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StorageBucketRuleDO record);

    int insertSelective(StorageBucketRuleDO record);

    StorageBucketRuleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StorageBucketRuleDO record);

    int updateByPrimaryKey(StorageBucketRuleDO record);
}