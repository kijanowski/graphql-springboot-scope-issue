# Sample project for issue reported at https://github.com/graphql-java/graphql-java-spring/issues/21 

Build and run:
```
$ ./gradlew clean build

$ java -jar build/libs/graphql-scope-issue-0.0.1-SNAPSHOT.jar
```

GraphiQL, an in-browser IDE for exploring GraphQL, is embedded through `graphiql-spring-boot-starter`
and available at `http://localhost:8081/graphiql`.

Sample queries to be run:

```
{
  animals(id: 0) {
    name
    color
    countryIds
    countries {
      name
    }
  }
}
```

```
{
  animals {
    name
    color
    countries {
      name
    }
  }
}
```