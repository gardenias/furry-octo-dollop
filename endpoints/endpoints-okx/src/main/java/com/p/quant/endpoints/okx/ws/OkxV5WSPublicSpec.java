package com.p.quant.endpoints.okx.ws;

import com.p.quant.endpoints.okx.domain.Dict.BookType;
import com.p.quant.endpoints.okx.domain.Dict.CandleType;
import com.p.quant.endpoints.okx.domain.Dict.InstType;
import com.p.quant.endpoints.okx.domain.InstId;

public interface OkxV5WSPublicSpec {

    void subInstruments(InstType instType);

    void ticker(InstId instId);

    void candle(InstId instId, CandleType type);

    void trades(InstId instId);

    void fundingRate(InstId instId);

    void status();

    void books(InstId instId, BookType type);
}
