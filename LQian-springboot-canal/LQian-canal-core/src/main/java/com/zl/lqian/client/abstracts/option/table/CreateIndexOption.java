package com.zl.lqian.client.abstracts.option.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.AbstractDBOption;

/**
 * 創建索引操作
 */
public abstract class CreateIndexOption extends AbstractDBOption {
	/**
	 * 创建索引
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.CINDEX;
	}
}
