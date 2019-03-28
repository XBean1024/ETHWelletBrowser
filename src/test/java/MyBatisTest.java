import com.binny.wallet.bean.Employee;
import com.binny.wallet.mapper.EmployeeMapper;
import com.binny.wallet.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * describe:
 *
 * @author 596928539@qq.com
 * @date 2019/03/28
 */
public class MyBatisTest {
    @Test
    public void test() throws IOException {
        /*
        * 1:根据  mybatis-config.xml 全局配置文件创建一个 SqlSessionFactory 对象
        * 2:sql映射文件,配置了每一个sql语句,以及SQL语句的封装规则
        *   2.1:SQL配置文件 EmployeeSQLMapper.xml
        * 3:将SQL映射文件注册在全局配置文件里
        * 4: 写代码
        *    4.1:根据全局配置文件得到 SqlSessionFactory
        *    4.2:使用 SqlSessionFactory 获取 SqlSession
        *    4.3:SqlSession来执行增删改查
        *    4.4:使用SQL唯一标示来告诉 mybatis 执行那个SQL;SQL保存在  EmployeeSQLMapper.xml文件中
        *    4.5:一个SqlSession就是代表和数据库的一次灰化,用完关闭
        */
        String res = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(res);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Employee employee = sqlSession.selectOne("selectEmployeeMapperId",1);
        System.out.println(employee.toString());
        sqlSession.close();
    }
    @Test
    public void test_new(){
        /*接口式查询
        * */
        SqlSessionFactory sqlSessionFactory = MyBatisUtils.obtionSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.selectEmployeeMapperId(1);
        System.out.println(employee.toString());
        sqlSession.close();
    }
}
