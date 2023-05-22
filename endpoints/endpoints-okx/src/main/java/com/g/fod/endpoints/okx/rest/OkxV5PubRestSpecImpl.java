package com.g.fod.endpoints.okx.rest;

import com.g.fod.endpoints.okx.rest.pub.FundingRateEndpoint;
import com.g.fod.endpoints.okx.rest.pub.FundingRateHisEndpoint;
import com.g.fod.endpoints.okx.rest.pub.InstrumentsEndpoint;
import com.g.fod.endpoints.okx.rest.pub.StatusEndpoint;
import lombok.extern.slf4j.Slf4j;

import com.g.common.endpoints.core.rest.HeadersProducer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OkxV5PubRestSpecImpl extends AbstractRestSpec implements OkxV5PubRestSpec {

  private final FundingRateEndpoint fundingRate;
  private final FundingRateHisEndpoint fundingRateHis;
  private final StatusEndpoint status;
  private final InstrumentsEndpoint instruments;

  public OkxV5PubRestSpecImpl(WebClient webClient, HeadersProducer headersProducer) {
    super(webClient, headersProducer);

    this.fundingRate = new FundingRateEndpoint(webClient, headersProducer);
    this.fundingRateHis = new FundingRateHisEndpoint(webClient, headersProducer);
    this.status = new StatusEndpoint(webClient, headersProducer);
    this.instruments = new InstrumentsEndpoint(webClient, headersProducer);
    ;
  }

  @Override
  public Mono<FundingRateEndpoint.FundingRateResp> fundingRate(FundingRateEndpoint.FundingRateReq req) {
    return fundingRate.exec(req, FundingRateEndpoint.FundingRateResp.class);
  }

  @Override
  public Mono<FundingRateHisEndpoint.FundingRateHisResp> fundingRateHis(FundingRateHisEndpoint.FundingRateHisReq req) {
    return fundingRateHis.exec(req, FundingRateHisEndpoint.FundingRateHisResp.class);
  }

  @Override
  public Mono<StatusEndpoint.StatusResp> status(StatusEndpoint.StatusReq req) {
    return status.exec(req, StatusEndpoint.StatusResp.class);
  }

  @Override
  public Mono<InstrumentsEndpoint.InstrumentsResp> instruments(InstrumentsEndpoint.InstrumentsReq req) {
    return instruments.exec(req, InstrumentsEndpoint.InstrumentsResp.class);
  }
}
