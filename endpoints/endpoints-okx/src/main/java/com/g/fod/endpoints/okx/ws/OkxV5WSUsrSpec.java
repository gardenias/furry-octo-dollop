package com.g.fod.endpoints.okx.ws;

import com.g.fod.endpoints.okx.ws.args.AmendOrdArg;
import com.g.fod.endpoints.okx.ws.args.CancelOrdArg;
import com.g.fod.endpoints.okx.ws.args.PlaceOrdArg;
import lombok.NonNull;

import com.g.fod.openapi.spec.domain.Dict.InstType;

import com.g.fod.endpoints.okx.domain.InstId;

public interface OkxV5WSUsrSpec {

  void login(String key, String sec, String passphrase);

  void account();

  void account(String ccy);

  void positions(InstType type);

  void positions(InstType type, InstId instId);

  void orders(InstType type);

  void orders(InstType type, InstId instId);

  void ordersAlgo(InstType type);

  void ordersAlgo(InstType type, InstId instId);

  void algoAdvance(InstType type);

  void algoAdvance(InstType type, InstId instId);

  void liquidationWarning(InstType type);

  void balanceAndPosition();

  void submit(PlaceOrdArg... args);

  void cancel(@NonNull CancelOrdArg... args);

  void amend(@NonNull AmendOrdArg... args);
}
