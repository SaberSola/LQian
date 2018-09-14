package com.zl.lqian.service;


import com.zl.lqian.client.abstracts.option.content.DeleteOption;
import com.zl.lqian.client.abstracts.option.content.InsertOption;
import com.zl.lqian.client.abstracts.option.content.UpdateOption;
import com.zl.lqian.client.core.DealCanalEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class MyEventListenerimpl extends DealCanalEventListener {
	
	@Autowired
	public MyEventListenerimpl(@Qualifier("realInsertOptoin") InsertOption insertOption, @Qualifier("realDeleteOption") DeleteOption deleteOption,
							   @Qualifier("realUpdateOption") UpdateOption updateOption) {
		super(insertOption, deleteOption, updateOption);
	}
	
}
