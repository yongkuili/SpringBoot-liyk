package com.liyk.apps.sys.service;

import com.liyk.apps.sys.pojo.SysDept;
import com.liyk.tools.core.service.CurdService;

import java.util.List;

/**
 * 机构管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysDeptService extends CurdService<SysDept> {

	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<SysDept> findTree();
}
