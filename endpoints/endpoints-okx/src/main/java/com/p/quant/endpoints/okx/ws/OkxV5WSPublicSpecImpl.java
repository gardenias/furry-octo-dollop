package com.p.quant.endpoints.okx.ws;

import com.p.quant.endpoints.okx.domain.Dict;
import com.p.quant.endpoints.okx.domain.InstId;

import com.p.quant.endpoints.okx.ws.args.ChannelType;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;

@Slf4j
@Component
public class OkxV5WSPublicSpecImpl extends AbstractOkxV5WSSpec implements OkxV5WSPublicSpec {

    public OkxV5WSPublicSpecImpl(WebSocketHandler handler, long timeoutEpochMills, Object... uriVariables) {
        super(handler, Dict.V5_WS_PUBLIC, timeoutEpochMills, uriVariables);
    }

    @Override
    public void subInstruments(Dict.InstType instType) {
        subscribe(ChannelType.Types.INSTRUMENTS.arg().setInstType(instType));
    }

    @Override
    public void ticker(InstId instId) {
        subscribe(ChannelType.Types.TICKERS.arg().setInstId(instId));
    }

    @Override
    public void candle(InstId instId, Dict.CandleType type) {
        subscribe(type.arg().setInstId(instId));
    }

    @Override
    public void trades(InstId instId) {
        subscribe(ChannelType.Types.TRADES.arg().setInstId(instId));
    }

    @Override
    public void fundingRate(InstId instId) {
        subscribe(ChannelType.Types.FUNDING_RATE.arg().setInstId(instId));
    }

    @Override
    public void status() {
        subscribe(ChannelType.Types.STATUS.arg());
    }

    @Override
    public void books(InstId instId, Dict.BookType type) {
        subscribe(type.arg().setInstId(instId));
    }
}
