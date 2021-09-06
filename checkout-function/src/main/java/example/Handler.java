package example;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaAsyncClient;
import software.amazon.awssdk.services.lambda.model.GetAccountSettingsRequest;
import software.amazon.awssdk.services.lambda.model.GetAccountSettingsResponse;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

// Handler value: example.Handler
public class Handler implements RequestHandler<SQSEvent, String> {
    private static final Logger logger = LoggerFactory.getLogger(Handler.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final LambdaAsyncClient lambdaClient = LambdaAsyncClient.create();
    private static final Region SNS_REGION = Region.US_EAST_1;
    public static final String SNS_TARGET_ARN = "arn:aws:sns:us-east-1:310213316965:shop-order";
    public static final String SNS_NEW_ORDER = "New order";
    private final CompletableFuture<GetAccountSettingsResponse> accountSettings;
    private final SnsClient snsClient;

    public Handler() {
        accountSettings = lambdaClient.getAccountSettings(GetAccountSettingsRequest.builder().build());
        snsClient = SnsClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(SNS_REGION)
                .build();
    }

    @Override
    public String handleRequest(SQSEvent event, Context context) {
        String message = getEventMessage(event);
        logging(event, context, message);
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .targetArn(SNS_TARGET_ARN)
                .subject(SNS_NEW_ORDER)
                .build();
        PublishResponse responseSns = null;
        try {
            responseSns = snsClient.publish(request);
        } catch (Exception e) {
            e.getStackTrace();
        }
        String response = Optional.ofNullable(responseSns).map(PublishResponse::toString).orElse("nothing");
        logger.info("RESPONSE: {}", response);
        return response;
    }

    private void logging(SQSEvent event, Context context, String message) {
        // call Lambda API
        logger.info("Getting account settings");
        // log execution details
        logger.info("ENVIRONMENT VARIABLES: {}", gson.toJson(System.getenv()));
        logger.info("CONTEXT: {}", gson.toJson(context));
        logger.info("EVENT: {}", gson.toJson(event));
        logger.info(message);
        // process event

        // process Lambda API response
        try {
            String accountUsage = gson.toJson(accountSettings.get().accountUsage());
            logger.info("Account usage: {}", accountUsage);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private String getEventMessage(SQSEvent event) {
        StringBuilder message = new StringBuilder();
        for (SQSMessage msg : event.getRecords()) {
            message.append(msg.getBody());
        }
        return message.toString();
    }
}