package engineernick;

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
public class DateMinMaxTest {

    private Neo4j embeddedDatabaseServer;

    @BeforeAll
    void initializeNeo4j() {
        this.embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder()
                .withDisabledServer()
                .withFunction(DateMinMax.class)
                .build();
    }

    @AfterAll
    void closeNeo4j() {
        this.embeddedDatabaseServer.close();
    }

    @Test
    void dateMaxReturnsLaterDate() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
             Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemax( date('2023-01-01'), date('2022-01-01') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2023-01-01");
        }
    }

    @Test
    void dateMaxHandlesNulls() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
            Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemax( date('2023-01-01'), null )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2023-01-01");
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemax( null, date('2022-01-01') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2022-01-01");
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemax( null, null )
                    )
                """).single().get(0).toString()
            ).isEqualTo("NULL");
        }
    }

    @Test
    void dateMinReturnsEarlierDate() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
            Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemin( date('2023-01-01'), date('2022-01-01') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2022-01-01");
        }
    }

    @Test
    void dateMinHandlesNulls() {
        try (Driver driver = GraphDatabase.driver(embeddedDatabaseServer.boltURI());
            Session session = driver.session()) {
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemin( date('2023-01-01'), null )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2023-01-01");
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemin( null, date('2022-01-01') )
                    )
                """).single().get(0).asString()
            ).isEqualTo("2022-01-01");
            assertThat(
                session.run("""
                    RETURN toString(
                        engineernick.datemin( null, null )
                    )
                """).single().get(0).toString()
            ).isEqualTo("NULL");
        }
    }
}
