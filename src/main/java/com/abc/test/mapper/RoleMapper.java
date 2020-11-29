package com.abc.test.mapper;

import com.abc.test.Role;

public interface RoleMapper {

  Role getRole(Long id);

  Role findRole(String roleName);

  int deleteRole(Long id);

  int insertRole(Role role);

}
