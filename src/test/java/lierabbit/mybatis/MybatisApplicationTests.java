package lierabbit.mybatis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional//声明事务，配合Rollback
public class MybatisApplicationTests
{
    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback//测试结束回滚数据，保证测试单元每次运行的数据环境独立
    public void testUser()
    {
        userMapper.insert("QQQ",1);
        userMapper.insert("WWW",2);
        userMapper.insert("EEE",3);
        userMapper.insert("AAA",4);
        userMapper.insert("SSS",5);
        userMapper.insert("DDD",6);
        userMapper.insert("ZZZ",7);
        userMapper.insert("XXX",8);
        userMapper.insert("CCC",9);
        userMapper.insert("SSS213",10);

        // 测试findAll, 查询所有记录
        Assert.assertEquals(10, userMapper.countAll());

        // 测试findByName, 查询姓名为AAA的User
        Assert.assertEquals(4, userMapper.findByName("AAA").getAge().longValue());

        // 更新CCC用户的年龄为15
        userMapper.update("CCC",15);

        // 测试findByName, 查询姓名为CCC的User的年龄是否为15
        Assert.assertEquals(15, userMapper.findByName("CCC").getAge().longValue());

        // 测试删除姓名为AAA的User
        userMapper.delete("AAA");

        // 测试findAll, 查询所有记录, 验证上面的删除是否成功
        Assert.assertEquals(9, userMapper.countAll());

        // 测试findAll, 查询名字有S的有几个
        Assert.assertEquals(2,userMapper.findByNameLike("%S%").size());
    }

}
