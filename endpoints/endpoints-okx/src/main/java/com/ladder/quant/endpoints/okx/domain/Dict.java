package com.ladder.quant.endpoints.okx.domain;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;

import com.g.common.endpoints.core.domain.IEnum;

import com.ladder.quant.endpoints.okx.ws.args.ChannelType;
import lombok.Getter;
import org.apache.commons.codec.digest.HmacUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.Base64Utils;

public interface Dict {

    String V5_WS_PRIVATE = "wss://ws.okx.com:8443/ws/v5/private";
    String V5_WS_PUBLIC = "wss://ws.okx.com:8443/ws/v5/public";

    static String signature(String inputStr, Mac mac) {
        return Base64Utils.encodeToString(mac.doFinal(inputStr.getBytes(StandardCharsets.UTF_8)));
    }

    static Mac sha256(String sec) {
        return HmacUtils.getInitializedMac("HmacSHA256", sec.getBytes(StandardCharsets.UTF_8));
    }

    enum TradeMode implements IEnum<String> {
        ISOLATED("isolated"),
        CROSS("cross"),
        CASH("cash"),
        ;
        @Getter
        private final String code;

        TradeMode(String code) {
            this.code = code;
        }
    }

    enum Side implements IEnum<String> {
        BUY("buy"), SELL("sell"),
        ;
        @Getter
        private final String code;

        Side(String code) {
            this.code = code;
        }
    }

    enum PosiSide implements IEnum<String> {
        NET("net"),
        LONG("long"),
        SHORT("short"),
        ;
        @Getter
        private final String code;

        PosiSide(String code) {
            this.code = code;
        }
    }

    enum OrdType implements IEnum<String> {
        MARKET("market"),
        LIMIT("limit"),
        POST_ONLY("post_only"),
        FOK("fok"),
        IOC("ioc"),
        OPTIMAL_LIMIT_IOC("optimal_limit_ioc"),
        ;
        @Getter
        private final String code;

        OrdType(String code) {
            this.code = code;
        }
    }

    enum BookType implements ChannelType {
        BOOKS("books"),
        BOOKS_5("books5"),
        BOOKS_L_2_TBT("books-l2-tbt"),
        BOOKS_50_L_2_TBT("books50-l2-tbt"),
        ;

        @Getter
        private String code;

        BookType(String code) {
            this.code = code;
        }

        private static final Map<String, BookType> INDEX = new HashMap<>();

        static {
            final Map<String, BookType> tmp = new HashMap<>();
            for (BookType type : BookType.values()) {
                tmp.put(type.getCode(), type);
            }
        }

        @JsonCreator
        public static BookType of(String code) {
            return INDEX.get(code);
        }
    }

    enum InstType implements IEnum<String> {
        SPOT,
        MARGIN,
        SWAP,
        FUTURES,
        OPTION,
        ANY,
        ;

        @Override
        public String getCode() {
            return name();
        }
    }

    enum CandleType implements ChannelType {
        MIN1("candle1m"),
        MIN3("candle3m"),
        MIN5("candle5m"),
        MIN15("candle15m"),
        MIN30("candle30m"),

        H1("candle1H"),
        H2("candle2H"),
        H4("candle4H"),
        H6("candle6H"),
        H12("candle12H"),

        D1("candle1D"),
        D2("candle2D"),
        D3("candle3D"),
        D5("candle5D"),

        W1("candle1W"),

        MON1("candle1M"),
        MON3("candle3M"),
        MON6("candle6M"),

        Y1("candle1Y"),

        UTC6H("candle6Hutc"),
        UTC12H("candle12Hutc"),

        UTC1D("candle1Dutc"),
        UTC2D("candle2Dutc"),
        UTC3D("candle3Dutc"),
        UTC5D("candle5Dutc"),

        UTC1W("candle1Wutc"),

        UTC1M("candle1Mutc"),
        UTC3M("candle3Mutc"),

        UTC1Y("candle1Yutc"),
        ;
        @Getter
        private final String code;

        CandleType(String code) {
            this.code = code;
        }

        private static final Map<String, CandleType> INDEX = new HashMap<>();

        static {
            final Map<String, CandleType> tmp = new HashMap<>();
            for (CandleType candleType : CandleType.values()) {
                tmp.put(candleType.getCode(), candleType);
            }
        }

        @JsonCreator
        public static CandleType of(String code) {
            return INDEX.get(code);
        }
    }
}
