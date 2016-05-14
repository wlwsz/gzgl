package com.stx.zzq.back.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.stx.zzq.base.BaseAction;

@Scope("prototype")
@Controller("back.OperationAction")
public class OperationAction extends BaseAction {

	/* 此处是设置页数和显示的条数 */
	private Integer rows;
	private Integer page;

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
