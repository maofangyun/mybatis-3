package com.abc.test.mapper;

import com.abc.test.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

  Role getRole(@Param("id") Long id);

  Role findRole(String roleName);

  int deleteRole(Long id);

  int insertRole(Role role);

}
