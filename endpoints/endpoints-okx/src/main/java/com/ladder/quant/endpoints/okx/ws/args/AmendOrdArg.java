package com.ladder.quant.endpoints.okx.ws.args;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AmendOrdArg extends InstIdArg {

    // 否	当订单修改失败时，该订单是否需要自动撤销 false：不自动撤单 true：自动撤单 ，默认为 false
    private Boolean cxlOnFail;
    // 可选	订单IDordId和clOrdId必须传一个，若传两个，以 ordId 为主
    private String ordId;
    // 可选	用户提供的订单ID
    private String clOrdId;
    // 否	用户提供的reqId如果提供，那在返回参数中返回reqId，方便找到相应的修改请求。字母（区分大小写）与数字的组合，可以是纯字母、纯数字且长度要在1-32位之间。
    private String reqId;
    // 可选	请求修改的新数量，newSz和newPx不可同时为空。对于部分成交订单，该数量应包含已成交数量。
    private String newSz;
    // 可选	请求修改的新价格
    private String newPx;

}
