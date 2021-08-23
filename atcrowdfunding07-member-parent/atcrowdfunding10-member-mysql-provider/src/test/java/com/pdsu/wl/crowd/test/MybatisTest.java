package com.pdsu.wl.crowd.test;

import com.pdsu.wl.crowd.entity.po.MemberPO;
import com.pdsu.wl.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wl
 * @Date 2021/8/14 22:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void TestMybatis() throws SQLException {

        final Connection connection = dataSource.getConnection();
        logger.info(String.valueOf(connection));
    }

    @Test
    public void TestMapper() {
        final MemberPO memberPO = memberPOMapper.selectByPrimaryKey(1);
        logger.info(memberPO.toString());
    }
}
