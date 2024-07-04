# neo4j-extensions


## Instructions used to Build this Repo

1. Read the [guide on neoj4 docs](https://neo4j.com/docs/java-reference/current/extending-neo4j/functions/)

2. Run some magic setup hallucinated by gpt

```bash
mvn archetype:generate -DgroupId=org.neo4j.example -DartifactId=procedure-template -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

3. Move stuff out of the `procedure-template` folder and delete the empty folder

```bash
mv procedure-template/* .
rm -rf procedure-template
```

4. Manually replace the contents of `pom.xml` with the example at
   [neo4j-examples/neo4j-procedure-template/pom.xml](https://github.com/neo4j-examples/neo4j-procedure-template/blob/0cb8dd95f28171cde47d1a46c08c7b63106d448c/pom.xml)
   - Replace the project and artefact name with `engineernick-util` (may need to change this again later?)
   - Optionally copy
     [`Join.java`](https://github.com/neo4j-examples/neo4j-procedure-template/blob/0cb8dd95f28171cde47d1a46c08c7b63106d448c/src/main/java/example/Join.java)
     and
     [`JoinTest.java`](https://github.com/neo4j-examples/neo4j-procedure-template/blob/0cb8dd95f28171cde47d1a46c08c7b63106d448c/src/test/java/example/JoinTest.java)
     from the example repo

5. Run the test using

```bash
mvn test
```

6. Add custom Code
7. Add custom tests
   - refer to <https://neo4j.com/docs/api/java-driver/current/org.neo4j.driver/org/neo4j/driver/Session.html>

7. Package

```bash
mvn clean package
```

8. Deploy?
   - Move `target/engineernick-util-1.0.0-SNAPSHOT.jar` into plugin directory of neo4j