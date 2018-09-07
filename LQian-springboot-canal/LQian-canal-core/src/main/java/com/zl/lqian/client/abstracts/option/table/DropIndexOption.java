package com.zl.lqian.client.abstracts.option.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.AbstractDBOption;

/**
 * 刪除索引操作
 */
public abstract class DropIndexOption extends AbstractDBOption {
	/**
	 * 刪除索引
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.DINDEX;
	}
}
