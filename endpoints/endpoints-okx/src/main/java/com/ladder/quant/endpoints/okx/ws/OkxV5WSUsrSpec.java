package com.ladder.quant.endpoints.okx.ws;

import lombok.NonNull;

import com.ladder.quant.endpoints.okx.domain.Dict.InstType;
import com.ladder.quant.endpoints.okx.domain.InstId;
import com.ladder.quant.endpoints.okx.ws.args.AmendOrdArg;
import com.ladder.quant.endpoints.okx.ws.args.CancelOrdArg;
import com.ladder.quant.endpoints.okx.ws.args.PlaceOrdArg;

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
