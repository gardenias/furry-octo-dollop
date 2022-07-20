package com.p.quant.endpoints.binance.rest.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class UrlBuilder {
    private static DecimalFormat df;

    private UrlBuilder() {
    }

    public static final String buildFullUrl(String baseUrl, String urlPath, LinkedHashMap<String,Object> parameters, String signature) {
        if (parameters != null && !parameters.isEmpty()) {
            StringBuilder sb = new StringBuilder(baseUrl);
            sb.append(urlPath).append('?');
            joinQueryParameters(sb, parameters);
            if (null != signature) {
                sb.append("&signature=").append(signature);
            }
            return sb.toString();
        } else {
            return baseUrl + urlPath;
        }
    }

    public static final String buildStreamUrl(String baseUrl, ArrayList<String> streams) {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("?streams=");
        return joinStreamUrls(sb, streams);
    }

    //concatenate query parameters
    public static final String joinQueryParameters(Map<String,Object> parameters) {
        return joinQueryParameters(new StringBuilder(), parameters).toString();
    }

    public static final StringBuilder joinQueryParameters(StringBuilder urlPath, Map<String,Object> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return urlPath;
        }

        boolean isFirst = true;
        for (Map.Entry<String,Object> mapElement : parameters.entrySet()) {

            if (mapElement.getValue() instanceof Double) {
                parameters.replace(mapElement.getKey(), getFormatter().format(mapElement.getValue()));
            } else if (mapElement.getValue() instanceof ArrayList) {
                if (((ArrayList<?>) mapElement.getValue()).isEmpty()) {
                    continue;
                }
                String key = mapElement.getKey();
                joinArrayListParameters(key, urlPath, (ArrayList<?>) mapElement.getValue(), isFirst);
                isFirst = false;
                continue;
            }

            if (isFirst) {
                isFirst = false;
            } else {
                urlPath.append('&');
            }

            urlPath.append(mapElement.getKey())
                .append('=')
                .append(urlEncode(mapElement.getValue().toString()));
        }
        return urlPath;
    }

    private static void joinArrayListParameters(String key, StringBuilder urlPath, ArrayList<?> values, boolean isFirst) {
        for(Object value: values) {
            if (isFirst) {
                isFirst = false;
            } else {
                urlPath.append('&');
            }

            urlPath.append(key)
                    .append('=')
                    .append(urlEncode(value.toString()));
        }
    }

    private static String joinStreamUrls(StringBuilder urlPath, ArrayList<String> streams) {
        boolean isFirst = true;
        for (String stream: streams) {
            if (isFirst) {
                isFirst = false;
            } else {
                urlPath.append('/');
            }
            urlPath.append(stream);
        }
        return urlPath.toString();
    }


    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // UTF-8 being unsuppored is unlikely
            // Replace with a unchecked exception to tidy up exception handling
            throw new RuntimeException(StandardCharsets.UTF_8.name() + " is unsupported", e);
        }
    }

    private static DecimalFormat getFormatter() {
        if (null == df) {
            df = new DecimalFormat();
            df.setMaximumFractionDigits(30);
            df.setGroupingUsed(false);
        }
        return df;
    }

    public static final String buildTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }
}
