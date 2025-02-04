package tests;

import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import servicesApi.CitrusConf;

import javax.sql.DataSource;

import static org.citrusframework.actions.ExecuteSQLQueryAction.Builder.query;

@CitrusSpringSupport
@ContextConfiguration(classes = {CitrusSpringConfig.class, CitrusConf.class})
public class SqlTests {

    @Autowired
    private DataSource dataSource;

    @Test
    @CitrusTest
    public void sqlCountByNameTest(@CitrusResource TestActionRunner action) {
        action.$(query(dataSource)
                .statement("select count(*) as sum from users where name = 'Name_1'")
                .validate("sum", "2")
        );
    }

    @Test
    @CitrusTest
    public void sqlValidateAllByIdTest(@CitrusResource TestActionRunner action) {
        action.$(query(dataSource)
                .statement("select id, name, age, cource, email from users where id = 3")
                .validate("id", "3")
                .validate("name", "Dev_3")
                .validate("age", "30")
                .validate("cource", "Data Science")
                .validate("email", "test3@test.test")
        );
    }


}
