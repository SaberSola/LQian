package com.zl.lqian.optiontest;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zl.lqian.client.abstracts.option.table.RenameTableOption;
import org.springframework.stereotype.Component;

/**
 * 真正的重新命名操作
 */
@Component
public class RealRenameTableOption extends RenameTableOption {
	
	
	/**
	 * 重命名表操作
	 *
	 * @param destination 指令
	 * @param schemaName  实例名称
	 * @param tableName   表名称
	 * @param rowChange   数据
	 * @return
	 */
	@Override
	public void doOption(String destination, String schemaName, String tableName, CanalEntry.RowChange rowChange) {
		System.out.println("======================接口方式（重新命名表操作）==========================");
		System.out.println("use "+schemaName+";\n"+rowChange.getSql());
		System.out.println("\n======================================================");
	}
}
