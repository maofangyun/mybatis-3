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
      sqlSession=sqlSessionFactory.openSession();
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
