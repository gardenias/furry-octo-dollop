package com.ladder.quant.endpoints.okx.rest;

import reactor.core.publisher.Mono;

import com.ladder.quant.endpoints.okx.rest.pub.FundingRateEndpoint;
import com.ladder.quant.endpoints.okx.rest.pub.FundingRateHisEndpoint;
import com.ladder.quant.endpoints.okx.rest.pub.InstrumentsEndpoint;
import com.ladder.quant.endpoints.okx.rest.pub.StatusEndpoint;

public interface OkxV5PubRestSpec {

  Mono<FundingRateEndpoint.FundingRateResp> fundingRate(FundingRateEndpoint.FundingRateReq req);

  Mono<FundingRateHisEndpoint.FundingRateHisResp> fundingRateHis(FundingRateHisEndpoint.FundingRateHisReq req);

  Mono<StatusEndpoint.StatusResp> status(StatusEndpoint.StatusReq req);

  Mono<InstrumentsEndpoint.InstrumentsResp> instruments(InstrumentsEndpoint.InstrumentsReq req);
}
