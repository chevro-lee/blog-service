package ink.chevro.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  0:10 2019-08-13
 **/
@Configuration
@MapperScan(basePackages = "ink.chevro.admin.mybatis.mapper", sqlSessionFactoryRef = "adminSqlSessionFactory")
public class AdminDataSourceConfig {

    private final static Logger logger = LoggerFactory.getLogger(AdminDataSourceConfig.class);

    @Bean(name = "adminDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.admin")
    public HikariDataSource getDataSource(DataSourceProperties properties) {
        logger.info("init master data source：{}", properties);
        return new HikariDataSource();
    }

    @Bean(name = "adminSqlSessionFactory")
    @Primary
    public SqlSessionFactory adminSqlSessionFactory(@Qualifier("adminDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:ink/chevro/admin/mapper/*/*.xml"));
        return bean.getObject();
    }

    @Bean("adminSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate adminSqlSessionTemplate(
            @Qualifier("adminSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
