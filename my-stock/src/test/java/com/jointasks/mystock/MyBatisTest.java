package com.jointasks.mystock;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jointasks.mystock.model.Blog;
import com.jointasks.mystock.model.BlogMapper;

public class MyBatisTest {

    private static SqlSessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        String resource = "mybatis-config.xml";
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testSelectBLog() {
        try {
            SqlSession session = sessionFactory.openSession();

            BlogMapper mapper = session.getMapper(BlogMapper.class);

            Blog blog = mapper.selectBlog(111);

            Assert.assertNotNull(blog);
            session.close();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
