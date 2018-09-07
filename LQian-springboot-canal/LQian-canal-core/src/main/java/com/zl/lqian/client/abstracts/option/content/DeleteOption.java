package com.zl.lqian.client.abstracts.option.content;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.AbstractDBOption;

/**
 * 删除数据
 */

public abstract class DeleteOption extends AbstractDBOption {
	
	/**
	 * 设置删除操作
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.DELETE;
	}
	
}
