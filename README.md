# Spring Boot based GraphQL gateway micro-service 

This application is part of [this blog post]() about a gRPC based micro-services architecture 
using GraphQL to gather data from multiple services. 


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