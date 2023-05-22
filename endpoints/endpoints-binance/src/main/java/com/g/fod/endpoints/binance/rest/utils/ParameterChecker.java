package com.g.fod.endpoints.binance.rest.utils;

import java.util.LinkedHashMap;

import com.g.fod.endpoints.binance.client.exceptions.BinanceConnectorException;

public class ParameterChecker {

  private ParameterChecker() {
  }

  public static void checkParameter(LinkedHashMap<String, Object> parameters, String parameter, Class t) {
    if (checkRequiredParameter(parameters, parameter)) {
      checkParameterType(parameters.get(parameter), t, parameter);
    }
  }

  public static boolean checkRequiredParameter(LinkedHashMap<String, Object> parameters, String parameter) {
    if (!parameters.containsKey(parameter)) {
      throw new BinanceConnectorException(String.format("\"%s\" is a mandatory parameter!", parameter));
    }
    return true;
  }

  public static boolean checkParameterType(Object parameter, Class t, String name) {
    if (!t.isInstance(parameter)) {
      throw new BinanceConnectorException(String.format("\"%s\" must be of %s type.", name, t));
    }
    return true;
  }
}
