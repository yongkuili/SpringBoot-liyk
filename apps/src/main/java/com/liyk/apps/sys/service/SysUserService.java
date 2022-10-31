package com.liyk.apps.sys.service;

import com.liyk.apps.sys.pojo.SysUser;
import com.liyk.apps.sys.pojo.SysUserRole;
import com.liyk.tools.core.page.PageRequest;
import com.liyk.tools.core.service.CurdService;
import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 用户管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param userId
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);

	/**
	 * 生成用户信息Excel文件
	 * @param pageRequest 要导出的分页查询参数
	 * @return
	 */
	File createUserExcelFile(PageRequest pageRequest);

}
