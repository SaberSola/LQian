package com.zl.lqian.client.abstracts.option.content;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.AbstractDBOption;

/**
 * 新增数据
 */

public abstract class InsertOption extends AbstractDBOption {
	
	
	/**
	 * 设置新增操作
	 **/
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.INSERT;
	}
}