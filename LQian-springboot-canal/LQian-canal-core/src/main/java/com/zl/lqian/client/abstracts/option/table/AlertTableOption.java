package com.zl.lqian.client.abstracts.option.table;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.AbstractDBOption;

/**
 * 修改表操作
 */
public abstract class AlertTableOption extends AbstractDBOption {
	/**
	 * 修改表操作
	 *
	 * @author 阿导
	 * @CopyRight 萬物皆導
	 * @created 2018/5/29 09:21
	 */
	@Override
	protected void setEventType() {
		this.eventType = CanalEntry.EventType.ALTER;
	}
}
