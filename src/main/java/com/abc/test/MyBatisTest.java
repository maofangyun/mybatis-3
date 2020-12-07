/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.abc.test;

import com.abc.test.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {
  public static void main(String[] args) {
    String resource="mybatis-config.xml";
    InputStream inputStream=null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory=null;
    // SqlSessionFactory里面包含了Configuration(存储了mybatis-config.xml中配置的各种属性值)
    sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession=null;
    try {
      // sqlSession中包含了Executor(持有Configuration对象和Transaction对象)
      sqlSession=sqlSessionFactory.openSession();
      // 动态代理生成的对象，内部会持有sqlSession的引用
      RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
      Role role=roleMapper.getRole(1L);
      System.out.println(role.getId()+":"+role.getRoleName()+":"+role.getNote());
      sqlSession.commit();

    } catch (Exception e) {
      sqlSession.rollback();
      e.printStackTrace();
    }finally {
      sqlSession.close();
    }
  }
}
