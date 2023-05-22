package com.g.fod.apps.main.bench.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import com.g.fod.endpoints.mexc.endpoints.OrderOps;
import com.g.fod.endpoints.mexc.endpoints.OrderOps.ReqPostProcess;
import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitReq;
import com.lmax.disruptor.EventHandler;
import com.g.fod.endpoints.hb.ws.BBO;
import com.g.fod.endpoints.hb.ws.TradeDetail;
import com.p.common.base.domain.Quantity;
import com.p.common.base.domain.Side;

public class ReqProduceHandler implements EventHandler<MarketEvent> {

  @Override
  public void onEvent(MarketEvent event, long sequence, boolean endOfBatch) {
    BBO bbo = event.getBbo();
    TradeDetail tradeDetail = event.getTradeDetail();
    if (bbo != null) {
      event.setReqs(List.of(
        OrderOps.LIMIT.emit(bbo.getSymbol(),
          (ReqPostProcess<SubmitReq>) req -> req.openLong()
            .setPrice(bbo.getBid())
//                        .ordQty(getOrdQty(bbo.getBidSize().decimal(), 4))
        ),
        OrderOps.LIMIT.emit(bbo.getSymbol(),
          (ReqPostProcess<SubmitReq>) req -> req.closeLong()
            .setPrice(bbo.getAsk())
//                        .ordQty(getOrdQty(bbo.getAskSize().decimal(), 4))
        )
      ));
    } else if (tradeDetail != null) {
      String symbol = tradeDetail.getSymbol();
      event.setReqs(
        tradeDetail.getData().stream()
          .map(t -> OrderOps.MARKET.emit(symbol, (ReqPostProcess<SubmitReq>) req -> {

            if (Side.BUY == t.getSide()) {
//                            req.openLong().vol(Amount.of(
//                                t.getAmount().decimal().multiply(t.getPrice().decimal())
//                                    .setScale(t.getPrice().decimal().scale(),
//                                        RoundingMode.HALF_EVEN)));
            } else if (Side.SELL == t.getSide()) {
//                            req.closeLong().vol(getOrdQty(t.getAmount().decimal(), 4));
            }

            return req;
          })).collect(Collectors.toList()));
    }
  }

  private Quantity getOrdQty(BigDecimal decimal, int newScale) {
    BigDecimal value = decimal.setScale(newScale, RoundingMode.HALF_EVEN);
    if (value.signum() == 0) {value = BigDecimal.ONE;}
    return Quantity.of(value);
  }
}
