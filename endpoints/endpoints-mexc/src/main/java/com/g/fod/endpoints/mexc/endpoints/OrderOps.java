package com.g.fod.endpoints.mexc.endpoints;

import com.g.fod.endpoints.mexc.endpoints.OrderSubmitEndpoint.SubmitReq;

public enum OrderOps {
  LIMIT {
    @Override
    protected SubmitReq construct(String symbol) {
      return new SubmitReq(symbol).limit();
    }
  },
  MARKET {
    @Override
    protected SubmitReq construct(String symbol) {
      return new SubmitReq(symbol).market();
    }
  },
  LIMIT_IOC {
    @Override
    protected SubmitReq construct(String symbol) {
      return new SubmitReq(symbol).limit();
    }
  },
  CANCEL {
    @Override
    protected CancelByOrderIdEndpoint.CancelReq construct(String symbol) {
      return new CancelByOrderIdEndpoint.CancelReq();
    }
  },

  CANCEL_ALL {
    @Override
    protected CancelAllEndpoint.CancelAllReq construct(String symbol) {
      return new CancelAllEndpoint.CancelAllReq(symbol);
    }
  };

  public <T> T emit(String symbol, ReqPostProcess<T> reqPostProcess) {
    return reqPostProcess.apply(construct(symbol));
  }

  protected abstract <T> T construct(String symbol);

  @FunctionalInterface
  public interface ReqPostProcess<T> {

    T apply(T req);
  }
}
