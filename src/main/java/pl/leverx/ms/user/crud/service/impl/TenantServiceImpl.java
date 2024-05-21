package pl.leverx.ms.user.crud.service.impl;

import liquibase.command.CommandScope;
import liquibase.exception.LiquibaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.leverx.ms.user.crud.service.TenantService;
import pl.leverx.ms.user.crud.util.TenantUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

import static liquibase.command.core.UpdateCommandStep.CHANGELOG_FILE_ARG;
import static liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep.DEFAULT_SCHEMA_NAME_ARG;
import static liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep.PASSWORD_ARG;
import static liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep.URL_ARG;
import static liquibase.command.core.helpers.DbUrlConnectionArgumentsCommandStep.USERNAME_ARG;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private static final String DB_CHANGELOG_PATH = "db/changelog/db.changelog-master.yaml";

    private static final String CREATE_SCHEMA_QUERY = "CREATE SCHEMA IF NOT EXISTS %s";
    private static final String DROP_SCHEMA_QUERY = "DROP SCHEMA IF EXISTS %s CASCADE";

    private final DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void createTenant(String tenantId) {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()
        ) {
            var schemaName = TenantUtil.createSchemaName(tenantId);
            statement.execute(String.format(CREATE_SCHEMA_QUERY, schemaName));

            var commandScope = new CommandScope("update");
            commandScope.addArgumentValue(CHANGELOG_FILE_ARG, DB_CHANGELOG_PATH);
            commandScope.addArgumentValue(URL_ARG, url);
            commandScope.addArgumentValue(USERNAME_ARG, username);
            commandScope.addArgumentValue(PASSWORD_ARG, password);
            commandScope.addArgumentValue(DEFAULT_SCHEMA_NAME_ARG, schemaName);
            commandScope.execute();

        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTenant(String tenantName) {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var schemaName = TenantUtil.createSchemaName(tenantName);
            statement.execute(String.format(DROP_SCHEMA_QUERY, schemaName));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
