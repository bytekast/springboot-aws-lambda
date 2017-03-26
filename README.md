# springboot-aws-lambda

Example bootstrap project for creating and deploying a [Spring Boot](https://projects.spring.io/spring-boot/) application to [AWS Lambda](https://aws.amazon.com/lambda/) using the [Serverless Framework](https://serverless.com).

Note: This example uses an in-memory H2 database that is created and initialized when the Lambda function is first invoked. You may notice delay in **"cold starts"**.

### Prerequisites
- Create an [Amazon Web Services](https://aws.amazon.com) account
- Install and set-up [Serverless Framework CLI](https://serverless.com)
- Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Build and Deploy
- To build, run `./gradlew clean build`
- To deploy, run `serverless deploy`
