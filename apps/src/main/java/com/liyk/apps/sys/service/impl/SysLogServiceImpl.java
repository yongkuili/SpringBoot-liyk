package com.liyk.apps.sys.service.impl;

import java.util.List;

import com.liyk.apps.sys.dao.SysLogMapper;
import com.liyk.apps.sys.pojo.SysLog;
import com.liyk.apps.sys.service.SysLogService;
import com.liyk.toos.core.page.MybatisPageHelper;
import com.liyk.toos.core.page.PageRequest;
import com.liyk.toos.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysLogServiceImpl  implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public int save(SysLog record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysLogMapper.insertSelective(record);
		}
		return sysLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysLog record) {
		return sysLogMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysLog> records) {
		for(SysLog record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysLog findById(Long id) {
		return sysLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		Object label = pageRequest.getParamValue("userName");
		if(label != null) {
			return MybatisPageHelper.findPage(pageRequest, sysLogMapper, "findPageByUserName", label);
		}
		return MybatisPageHelper.findPage(pageRequest, sysLogMapper);
	}
	
}
