package com.liyk.apps.sys.service;


import com.liyk.apps.sys.pojo.SysConfig;
import com.liyk.tools.core.service.CurdService;

import java.util.List;

/**
 * 系统配置管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysConfigService extends CurdService<SysConfig> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysConfig> findByLable(String lable);
}
