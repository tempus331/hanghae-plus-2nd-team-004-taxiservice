package hanghae.four.taxiservice.acceptance

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DatabaseCleanup : InitializingBean {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private lateinit var tableNames: List<String>

    override fun afterPropertiesSet() {
        tableNames = jdbcTemplate.query("SHOW TABLES", {resultSet, rowNumber -> resultSet.getString(1)})
    }

    @Transactional
    fun execute() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE")
        for (tableName in tableNames) {
            jdbcTemplate.execute("TRUNCATE TABLE " + tableName)
        }
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE")
    }
}
