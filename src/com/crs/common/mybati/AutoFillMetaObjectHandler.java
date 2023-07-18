package com.crs.common.mybati;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author LZ
 */
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		// 创建数据时，创建时间与更新时间都由数据库进行自动填充
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
	}
}
