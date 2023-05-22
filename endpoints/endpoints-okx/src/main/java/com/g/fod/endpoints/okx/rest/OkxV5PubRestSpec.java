package com.g.fod.endpoints.okx.rest;

import com.g.fod.endpoints.okx.rest.pub.FundingRateEndpoint;
import com.g.fod.endpoints.okx.rest.pub.FundingRateHisEndpoint;
import com.g.fod.endpoints.okx.rest.pub.InstrumentsEndpoint;
import com.g.fod.endpoints.okx.rest.pub.StatusEndpoint;
import reactor.core.publisher.Mono;

public interface OkxV5PubRestSpec {

  Mono<FundingRateEndpoint.FundingRateResp> fundingRate(FundingRateEndpoint.FundingRateReq req);

  Mono<FundingRateHisEndpoint.FundingRateHisResp> fundingRateHis(FundingRateHisEndpoint.FundingRateHisReq req);

  Mono<StatusEndpoint.StatusResp> status(StatusEndpoint.StatusReq req);

  Mono<InstrumentsEndpoint.InstrumentsResp> instruments(InstrumentsEndpoint.InstrumentsReq req);
}
