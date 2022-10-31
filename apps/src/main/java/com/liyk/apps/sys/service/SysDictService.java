package com.liyk.apps.sys.service;

import com.liyk.apps.sys.pojo.SysDict;
import com.liyk.tools.core.service.CurdService;

import java.util.List;

/**
 * 字典管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysDictService extends CurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDict> findByLable(String lable);
}
