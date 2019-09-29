package grpcgraphql.graphql;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.Internal;
import graphql.spring.web.servlet.GraphQLInvocation;
import graphql.spring.web.servlet.GraphQLInvocationData;
import grpcgraphql.graphql.model.CountryTO;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.CompletableFuture;

@Component
@Internal
@Primary
public class CustomGraphQLInvocation implements GraphQLInvocation {

    private final GraphQL graphQL;
    private final GraphQLDataFetchers dataFetchers;

    @Autowired(required = false)
    DataLoaderRegistry dataLoaderRegistry;

    public CustomGraphQLInvocation(GraphQL graphQL, GraphQLDataFetchers dataFetchers) {
        this.graphQL = graphQL;
        this.dataFetchers = dataFetchers;
    }

    @Override
    public CompletableFuture<ExecutionResult> invoke(GraphQLInvocationData invocationData, WebRequest webRequest) {
        ExecutionInput.Builder executionInputBuilder = ExecutionInput.newExecutionInput()
          .query(invocationData.getQuery())
          .operationName(invocationData.getOperationName())
          .variables(invocationData.getVariables());

        if (dataLoaderRegistry != null) {
            executionInputBuilder.dataLoaderRegistry(dataLoaderRegistry);
            executionInputBuilder.context(dataLoaderRegistry);
        }

        ExecutionInput executionInput = executionInputBuilder.build();
        return graphQL.executeAsync(executionInput);
    }

}