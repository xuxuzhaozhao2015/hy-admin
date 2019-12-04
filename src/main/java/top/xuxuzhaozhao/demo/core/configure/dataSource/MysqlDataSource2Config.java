package top.xuxuzhaozhao.demo.core.configure.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "top.xuxuzhaozhao.demo.dao.db2")
public class MysqlDataSource2Config {

    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource(){
        return new DruidDataSource();
    }


    @Bean(name="secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondDataSource")DataSource secondDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(secondDataSource);
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*.xml");
        sessionFactory.setMapperLocations(mapperLocations);
        return sessionFactory.getObject();
    }
}
