# Statistics Service

## Solution overview
The solution relies in three different types of components:

- **Controllers**: interface with the client
- **Repositories**: store data in memory
- **Tasks**: process transaction data

When data is received by the system, it is stored asynchronously in the `TransactionsRepository`, which means that the service clients has a quick response. The data storage is thread safe, it uses a synchronized (thread-safe) list backed by the specified list.

There are two background scheduled tasks that run from time to time, one task is resonsible to clean expired transactions and there is another task to calculate statistics, this last one pushes the calculation results to `StatisticsRepository`.

When a client requests the statistics, it's already calculated and ready to be delivered to the client in a timely manner.

## Used Tools

- Maven
- Spring Boot
- Mockito

## Tests

To run the tests use the following command:
```bash
./mvnw clean test
```

## Running the application

To run the appliaction use the following command:
```bash
./mvnw clean spring-boot:run
```

This command will run the applicatin on port `8080`.
