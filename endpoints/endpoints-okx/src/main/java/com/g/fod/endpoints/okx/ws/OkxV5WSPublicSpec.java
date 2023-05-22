package com.g.fod.endpoints.okx.ws;

import com.g.fod.openapi.spec.domain.Dict.InstType;

import com.g.fod.endpoints.okx.domain.Dict.BookType;
import com.g.fod.endpoints.okx.domain.Dict.CandleType;
import com.g.fod.endpoints.okx.domain.InstId;

public interface OkxV5WSPublicSpec {

  void subInstruments(InstType instType);

  void ticker(InstId instId);

  void candle(InstId instId, CandleType type);

  void trades(InstId instId);

  void fundingRate(InstId instId);

  void status();

  void books(InstId instId, BookType type);
}
