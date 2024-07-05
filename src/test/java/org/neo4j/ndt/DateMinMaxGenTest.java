
package ndt;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.neo4j.driver.Value;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateMinMaxGenTest {

    private Neo4j embeddedDatabaseServer;

    @BeforeAll
    void initializeNeo4j() {
        this.embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .withFunction(DateMinMaxGen.class)
                .build();
    }

    @AfterAll
    void closeNeo4j() {
        this.embeddedDatabaseServer.close();
    }

    
    @Test
    void min_date_returns_expected() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        ndt.min_date( date('2022-01-01'), date('2023-01-01') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2022-01-01");
        }
    }

    @Test
    void max_date_returns_expected() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        ndt.max_date( date('2022-01-01'), date('2023-01-01') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2023-01-01");
        }
    }

    @Test
    void min_localdatetime_returns_expected() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        ndt.min_localdatetime( localdatetime('2022-01-01T16:30:00'), localdatetime('2023-01-01T17:30:00') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2022-01-01T16:30:00");
        }
    }

    @Test
    void max_localdatetime_returns_expected() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        ndt.max_localdatetime( localdatetime('2022-01-01T16:30:00'), localdatetime('2023-01-01T17:30:00') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2023-01-01T17:30:00");
        }
    }

    @Test
    void min_datetime_returns_expected() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        ndt.min_datetime( datetime('2022-01-01T16:30:00+08:00'), datetime('2023-01-01T17:30:00+08:00') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2022-01-01T16:30:00+08:00");
        }
    }

    @Test
    void max_datetime_returns_expected() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        ndt.max_datetime( datetime('2022-01-01T16:30:00+08:00'), datetime('2023-01-01T17:30:00+08:00') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2023-01-01T17:30:00+08:00");
        }
    }
}
