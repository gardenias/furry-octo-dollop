package com.ladder.quant.endpoints.okx.ws.args;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CancelOrdArg extends InstIdArg {

    // 	可选	订单IDordId和clOrdId必须传一个，若传两个，以 ordId 为主
    private String ordId;
    // 	可选	用户提供的订单ID字母（区分大小写）与数字的组合，可以是纯字母、纯数字，且长度要在1-32位之间。
    private String clOrdId;
}
