package com.liyk.apps.sys.service.impl;

import java.util.List;

import com.liyk.apps.sys.dao.SysLoginLogMapper;
import com.liyk.apps.sys.pojo.SysLoginLog;
import com.liyk.apps.sys.service.SysLoginLogService;
import com.liyk.tools.core.page.MybatisPageHelper;
import com.liyk.tools.core.page.PageRequest;
import com.liyk.tools.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginLogServiceImpl  implements SysLoginLogService {

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;

	@Override
	public int save(SysLoginLog record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysLoginLogMapper.insertSelective(record);
		}
		return sysLoginLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysLoginLog record) {
		return sysLoginLogMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysLoginLog> records) {
		for(SysLoginLog record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysLoginLog findById(Long id) {
		return sysLoginLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		Object userName = pageRequest.getParamValue("userName");
		if(userName != null) {
			return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper, "findPageByUserName", userName);
		}
		Object status = pageRequest.getParamValue("status");
		if(status != null) {
			return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper, "findPageByStatus", status);
		}
		return MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper);
	}
	
}
