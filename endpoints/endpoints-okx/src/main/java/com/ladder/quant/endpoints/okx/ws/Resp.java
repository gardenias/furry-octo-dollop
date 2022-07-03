package com.ladder.quant.endpoints.okx.ws;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode
public class Resp<T> implements Serializable {

    //	消息的唯一标识
    private String id;
    //	业务操作
    private Op op;
    //	代码
    private int code;
    //	消息
    private String msg;
    //	请求成功后返回的数据
    private List<T> data;

    @Setter
    @Getter
    @Accessors(chain = true)
    @EqualsAndHashCode
    static class OrdResp implements Serializable {

        // 	订单ID
        private String ordId;
        // 	由用户设置的订单ID
        private String clOrdId;
        // 	用户提供的请求ID 如果用户在请求中提供reqId，则返回相应reqId
        private String reqId;
        // 订单标签
        private String tag;
        // 	订单状态码，0 代表成功
        private String sCode;
        // 	订单状态消息
        private String sMsg;
    }

}
