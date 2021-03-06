package example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.strategy.sampling.NoSamplingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class InvokeTest {
    private static final Logger logger = LoggerFactory.getLogger(InvokeTest.class);
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(SQSEvent.class, new SQSEventDeserializer())
            .setPrettyPrinting()
            .create();

    public InvokeTest() {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard();
        builder.withSamplingStrategy(new NoSamplingStrategy());
        AWSXRay.setGlobalRecorder(builder.build());
    }

    @Test
    void invokeTest() {
        AWSXRay.beginSegment("checkout-function-test");
        String path = "src/test/resources/event.json";
        String eventString = loadJsonFile(path);
        SQSEvent event = gson.fromJson(eventString, SQSEvent.class);
        Context context = new TestContext();
        String requestId = context.getAwsRequestId();
        Handler handler = new Handler();
        String result = handler.handleRequest(event, context);
        assertNotNull(result);
        AWSXRay.endSegment();
    }

    private static String loadJsonFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
