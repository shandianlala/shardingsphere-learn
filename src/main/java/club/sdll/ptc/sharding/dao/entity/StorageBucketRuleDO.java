package club.sdll.ptc.sharding.dao.entity;

import lombok.Data;

/**
 * 云存储bucket规则表 ##
 *
 * @author 草薙
 * @since 2022/02/25
 */
@Data
public class StorageBucketRuleDO {
    /** 自增主键 */
    private Long id;

    /** 前期存储biz_type：app业务类型；私有云租户ID概念 */
    private String tenantId;

    /** 文件存储天数，1~无穷大，-1：永久bucket */
    private Integer storageDay;

    /** 文件类型，PIC-图片，MEDIA-录像，AUDIO-音频 */
    private String fileType;

    /** 云存储账户表主键id */
    private Long storageAccountId;

    /** 扩展信息 */
    private String extInfo;

    /** 删除标记，0-有效，1-已删除 */
    private Byte isDeleted;

    /** 创建时间 */
    private Long gmtCreate;

    /** 更新时间 */
    private Long gmtModified;
}