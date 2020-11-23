package ru.rtmis.melfor.camel.route.log;

public final class RouteLoggingMessages {
    public static final String TRUE = "true";
    public static final String READ_MESSAGE_FROM_REST_BODY = "Read Message from Rest ${body}";
    public static final String UNMARSHALLED_BODY_IS_BODY = "Unmarshalled body is ${body}";
    public static final String ENRICHED_BODY_IS_BODY = "Enriched body is ${body}";
    public static final String PROCESSED_BODY_IS_BODY = "Processed body is ${body}";
    public static final String MARSHALLED_BODY_IS_BODY = "Marshalled body is ${body}";
    public static final String SENT_BODY_TO_KAFKA_SUBTRACTION_TOPIC = "Sent ${body} to Kafka Subtraction topic";
    public static final String POLL_ENRICHED_RESULT_BODY = "Poll enriched Result ${body}";

    private RouteLoggingMessages() {
    }
}
