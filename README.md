# Neo4j Date Min Max User Defined Functions <!-- omit in toc -->

[![Release](https://github.com/thehappycheese/neo4j-extensions/actions/workflows/release.yml/badge.svg)](https://github.com/thehappycheese/neo4j-extensions/actions/workflows/release.yml)

Date Min / Max user defined functions for `neo4j` because I was frustrated with
the built in solutions.

Sadly I am still unhappy with this solution because generics didn't work (see
explanation further below). Perhaps it can be improved?

- [1. Functions](#1-functions)
- [2. Names](#2-names)
  - [2.1. Why such long method names?](#21-why-such-long-method-names)
  - [2.2. What is the package name `ndt.`?](#22-what-is-the-package-name-ndt)
- [3. Installation](#3-installation)
- [4. Example usage](#4-example-usage)
- [5. Process followed to create this Repo](#5-process-followed-to-create-this-repo)


## 1. Functions

This extensions adds the following functions that can be used in cypher:

- `ndt.min_date(dateA, dateB)`
- `ndt.min_datetime(dateA, dateB)`
- `ndt.min_localdatetime(dateA, dateB)`
- `ndt.max_date(dateA, dateB)`
- `ndt.max_datetime(dateA, dateB)`
- `ndt.max_localdatetime(dateA, dateB)`

Note that

- All functions ignore `null` values;
  - if one of the arguments is `null`, the other argument is returned
  - if both arguments are `null`, then `null` is returned
- Argument types must match; e.g. a `localdatetime` cannot be compared with a
  `date` even though that would seem ok.

## 2. Names

### 2.1. Why such long method names?

`neo4j` throws a tantrum if you try to use java's method overloading or generics
to create a single `ndt.min()` function that works for all date types. Therefore
This module provides suffixed date comparison functions.

These long method names like `ndt.max_localdatetime` are obviously deeply
unsatisfactory, and are likely why neo4j does not provide this functionality out
of the box.

Note that the alternative to this way of doing this using built-in functions as
shown below. For me this is also highly unsatisfactory as it is unlikely to be
performant under the hood in my opinion. At least it works for all types.

```cypher
RETURN apoc.coll.min([dateA, dateB])
```

### 2.2. What is the package name `ndt.`?

The package name is like the pandas date-accessor module `Series(...).dt.year()`
but I added an `n` to the start to get `ndt`

## 3. Installation

Download the latest artifact from the
[releases](https://github.com/thehappycheese/neo4j-extensions/releases) page and
apparently all you have to do is put it in the plugins folder of your neo4j
deployment. Beware that I have not tested this process on an actual server yet.

## 4. Example usage

Easily return the first of two dates:

```cypher
// Minimum
RETURN ndt.min_date( date('2023-01-01'), date('2022-01-01') )

// >> 2022-01-01
```

## 5. Process followed to create this Repo

1. Read the [guide on neoj4 docs](https://neo4j.com/docs/java-reference/current/extending-neo4j/functions/)

2. Run some occult looking java / maven setup command hallucinated by ChatGPT

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
