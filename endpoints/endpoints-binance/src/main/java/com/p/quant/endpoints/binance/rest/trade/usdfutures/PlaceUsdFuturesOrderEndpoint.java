package com.p.quant.endpoints.binance.rest.trade.usdfutures;

import com.p.quant.endpoints.binance.base.BinanceReq;
import com.p.quant.endpoints.binance.enums.RequestType;
import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderReq;

import com.p.quant.endpoints.binance.rest.trade.usdfutures.PlaceUsdFuturesOrderEndpoint.PlaceUsdFuturesOrderResp;

import lombok.Data;

import com.g.common.endpoints.core.rest.Resp;
import com.p.quant.endpoints.binance.base.AbstractBinanceRESTEndpoint;
import com.p.quant.endpoints.binance.common.SignatureProducer;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

/**
 * 下单
 * @author：wanghao
 * @see "https://binance-docs.github.io/apidocs/futures/cn/#user_data-2"
 * @date  ：Created in 2022/7/7
 */
public class PlaceUsdFuturesOrderEndpoint extends AbstractBinanceRESTEndpoint<PlaceUsdFuturesOrderReq, PlaceUsdFuturesOrderResp> {

    public PlaceUsdFuturesOrderEndpoint(WebClient webClient, SignatureProducer signatureProducer) {
        super(webClient, signatureProducer, "/fapi/v1/order", HttpMethod.POST, RequestType.SIGNED);
    }
    @Data
    public static class PlaceUsdFuturesOrderReq extends BinanceReq {
        private  String symbol;//	STRING	YES	交易对
        private  String side;//	ENUM	YES	买卖方向 SELL, BUY
        private  String positionSide;//	ENUM	NO	持仓方向，单向持仓模式下非必填，默认且仅可填BOTH;在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
        private  String type;//	ENUM	YES	订单类型 LIMIT, MARKET, STOP, TAKE_PROFIT, STOP_MARKET, TAKE_PROFIT_MARKET, TRAILING_STOP_MARKET
        private  String  reduceOnly;//	STRING	NO	true, false; 非双开模式下默认false；双开模式下不接受此参数； 使用closePosition不支持此参数。
        private  String quantity;//	DECIMAL	NO	下单数量,使用closePosition不支持此参数。
        private  String price;//	DECIMAL	NO	委托价格
        private  String newClientOrderId;//	STRING	NO	用户自定义的订单号，不可以重复出现在挂单中。如空缺系统会自动赋值。必须满足正则规则 ^[\.A-Z\:/a-z0-9_-]{1,36}$
        private  String  stopPrice;//	DECIMAL	NO	触发价, 仅 STOP, STOP_MARKET, TAKE_PROFIT, TAKE_PROFIT_MARKET 需要此参数
        private  String activationPrice;//	DECIMAL	NO	追踪止损激活价格，仅TRAILING_STOP_MARKET 需要此参数, 默认为下单当前市场价格(支持不同workingType)
        private  String callbackRate;//	DECIMAL	NO	追踪止损回调比例，可取值范围[0.1, 5],其中 1代表1% ,仅TRAILING_STOP_MARKET 需要此参数
        private  String timeInForce	;//ENUM	NO	有效方法
        private  String  workingType;//	ENUM	NO	stopPrice 触发类型: MARK_PRICE(标记价格), CONTRACT_PRICE(合约最新价). 默认 CONTRACT_PRICE
        private  String priceProtect;//	STRING	NO	条件单触发保护："TRUE","FALSE", 默认"FALSE". 仅 STOP, STOP_MARKET, TAKE_PROFIT, TAKE_PROFIT_MARKET 需要此参数
        private  String  newOrderRespType;//	ENUM	NO	"ACK", "RESULT", 默认 "ACK"
        
        private  Long recvWindow;//	LONG	NO
        private  Long timestamp;//	LONG	YES
        private  String closePosition;//	STRING	NO	true, false；触发后全部平仓，仅支持STOP_MARKET和TAKE_PROFIT_MARKET；不与quantity合用；自带只平仓效果，不与reduceOnly 合用

    }
    @Data
    public static class PlaceUsdFuturesOrderResp extends Resp {

        private String clientOrderId;
        private String cumQty;
        private String cumQuote;
        private String executedQty;
        private long orderId;
        private String avgPrice;
        private String origQty;
        private String price;
        private boolean reduceOnly;
        private String side;
        private String positionSide;
        private String status;
        private String stopPrice;
        private boolean closePosition;
        private String symbol;
        private String timeInForce;
        private String type;
        private String origType;
        private String activatePrice;
        private String priceRate;
        private long updateTime;
        private String workingType;
        private boolean priceProtect;
    }

   
}
