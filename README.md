This ongoing E-commerce project enables users to add books to their cart and purchase them. The application is being developed using Java, Spring, and React. Spring Cloud Microservices and the Spring Boot Framework has been extensively used to ensure the application is distributed in nature.

All microservices are built using Spring Boot and are registered with the Eureka Discovery Server.

The front-end React App sends requests to the NGINX server, which acts as a reverse proxy. NGINX then redirects these requests to the Spring Cloud API Gateway. The Spring Gateway manages request routing to the appropriate microservices based on configured routing roles.

I've written a separate microservice for user authorization using Spring Security OAuth2 Authorization Server with PKCE. This server issues JWT tokens, a more secure alternative to using the deprecated grant_type password. Monitoring is done by Prometheus and Graphana in local environment and Cloud Watch and Cloud Trail in the AWS cloud.
