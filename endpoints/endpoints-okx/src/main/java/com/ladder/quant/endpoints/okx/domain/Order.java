package com.ladder.quant.endpoints.okx.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order implements Serializable {

    // 		产品类型 SPOT：币币 MARGIN：币币杠杆 SWAP：永续合约 FUTURES：交割合约 OPTION：期权
    private String instType;
    // 		产品ID
    private String instId;
    // 		市价单委托数量的类型 base_ccy: 交易货币 ；quote_ccy：计价货币
    private String tgtCcy;
    // 	保证金币种，仅适用于单币种保证金模式下的全仓币币杠杆订单
    private String ccy;
    // 	订单ID
    private String ordId;
    // 	客户自定义订单ID
    private String clOrdId;
    // 	订单标签
    private String tag;
    // 		委托价格
    private String px;
    // 		委托数量
    private String sz;
    // 	收益
    private String pnl;
    // 	订单类型 market：市价单 limit：限价单 post_only：只做maker单 fok：全部成交或立即取消 ioc：立即成交并取消剩余 optimal_limit_ioc：市价委托立即成交并取消剩余（仅适用交割、永续）
    private String ordType;
    // 		订单方向
    private String side;
    // 	持仓方向
    private String posSide;
    // 		交易模式
    private String tdMode;
    // 	累计成交数量
    private String accFillSz;
    // 		最新成交价格
    private String fillPx;
    // 	最新成交ID
    private String tradeId;
    // 		最新成交数量
    private String fillSz;
    // 		最新成交时间
    private String fillTime;
    // 	成交均价
    private String avgPx;
    // 	订单状态 canceled：撤单成功 live：等待成交 partially_filled：部分成交 filled：完全成交
    private String state;
    // 	杠杆倍数，0.01到125之间的数值，仅适用于 币币杠杆/交割/永续
    private String lever;
    // 	止盈触发价
    private String tpTriggerPx;
    // 	止盈触发价类型 last：最新价格 index：指数价格 mark：标记价格
    private String tpTriggerPxType;
    // 	止盈委托价
    private String tpOrdPx;
    // 	止损触发价
    private String slTriggerPx;
    // 	止损触发价类型 last：最新价格 index：指数价格 mark：标记价格
    private String slTriggerPxType;
    // 	止损委托价
    private String slOrdPx;
    // 		交易手续费币种
    private String feeCcy;
    // 	订单交易手续费，平台向用户收取的交易手续费，手续费扣除为负数。如： -0.01
    private String fee;
    // 	返佣金币种
    private String rebateCcy;
    // 		订单来源 13:策略委托单触发后的生成的限价单
    private String source;
    // 		返佣金额，平台向达到指定lv交易等级的用户支付的挂单奖励（返佣），如果没有返佣金，该字段为“”。手续费返佣为正数，如：0.01
    private String rebate;
    // 		订单种类 normal：普通委托 twap：TWAP自动换币 adl：ADL自动减仓 full_liquidation：强制平仓 partial_liquidation：强制减仓 delivery：交割 ddh：对冲减仓类型订单
    private String category;
    // 	订单状态更新时间，Unix时间戳的毫秒数格式，如：1597026383085
    private String uTime;
    // 	订单创建时间，Unix时间戳的毫秒数格式， 如 ：1597026383085
    private String cTime;

}
