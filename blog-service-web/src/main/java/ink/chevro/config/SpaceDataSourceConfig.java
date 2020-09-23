package ink.chevro.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  0:10 2019-08-13
 **/
@Configuration
@MapperScan(basePackages = "ink.chevro.space.mybatis.mapper",sqlSessionFactoryRef = "spaceSqlSessionFactory")
public class SpaceDataSourceConfig {

    @Bean(name = "spaceDataSource")
    @ConfigurationProperties("spring.datasource.space")
    public HikariDataSource getDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "spaceSqlSessionFactory")
    public SqlSessionFactory adminSqlSessionFactory(@Qualifier("spaceDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:ink/chevro/admin/mapper/*/*.xml"));
        return bean.getObject();
    }

    @Bean("spaceSqlSessionTemplate")
    public SqlSessionTemplate adminSqlSessionTemplate(
            @Qualifier("spaceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
