package com.liyk.apps.sys.dao;


import com.liyk.apps.sys.pojo.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
	List<SysMenu> findPage();

	List<SysMenu> findPageByName(@Param(value="name") String name);
	
	List<SysMenu> findAll();

	List<SysMenu> findByUserName(@Param(value="userName") String userName);

	List<SysMenu> findRoleMenus(@Param(value="roleId") Long roleId);
}