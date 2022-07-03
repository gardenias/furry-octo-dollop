package com.ladder.quant.endpoints.okx.ws;

import java.util.concurrent.TimeUnit;

import com.ladder.quant.endpoints.okx.domain.Dict;
import com.ladder.quant.endpoints.okx.domain.InstId;
import com.ladder.quant.endpoints.okx.ws.args.AmendOrdArg;
import com.ladder.quant.endpoints.okx.ws.args.CancelOrdArg;
import com.ladder.quant.endpoints.okx.ws.args.ChannelType;
import com.ladder.quant.endpoints.okx.ws.args.LoginArg;
import com.ladder.quant.endpoints.okx.ws.args.PlaceOrdArg;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;

@Slf4j
@Component
public class OkxV5WSPrivateSpecImpl extends AbstractOkxV5WSSpec implements OkxV5WSPrivateSpec {

    public OkxV5WSPrivateSpecImpl(WebSocketHandler handler) {
        this(handler, TimeUnit.SECONDS.toMillis(5));
    }

    public OkxV5WSPrivateSpecImpl(WebSocketHandler handler, long timeoutEpochMills, Object... uriVariables) {
        super(handler, Dict.V5_WS_PRIVATE, timeoutEpochMills, uriVariables);
    }

    @Override
    public void login(String key, String sec, String passphrase) {
        exec(OpType.LOGIN.args(new LoginArg(key, passphrase, sec).sign()));
    }

    @Override
    public void account() {
        subscribe(ChannelType.Types.ACCOUNT.arg());
    }

    @Override
    public void account(@NonNull String ccy) {
        subscribe(ChannelType.Types.ACCOUNT.arg().setCcy(ccy));
    }

    @Override
    public void positions(@NonNull Dict.InstType type) {
        subscribe(ChannelType.Types.POSITIONS.arg().setInstType(type));
    }

    @Override
    public void positions(@NonNull Dict.InstType type, InstId instId) {
        subscribe(ChannelType.Types.POSITIONS.arg().setInstType(type).setInstId(instId));
    }

    @Override
    public void orders(@NonNull Dict.InstType type) {
        subscribe(ChannelType.Types.ORDERS.arg().setInstType(type));
    }

    @Override
    public void orders(@NonNull Dict.InstType type, InstId instId) {
        subscribe(ChannelType.Types.ORDERS.arg().setInstType(type).setInstId(instId));
    }

    @Override
    public void ordersAlgo(@NonNull Dict.InstType type) {
        subscribe(ChannelType.Types.ORDERS_ALGO.arg().setInstType(type));
    }

    @Override
    public void ordersAlgo(@NonNull Dict.InstType type, InstId instId) {
        subscribe(ChannelType.Types.ORDERS_ALGO.arg().setInstType(type).setInstId(instId));
    }

    @Override
    public void algoAdvance(@NonNull Dict.InstType type) {
        subscribe(ChannelType.Types.ALGO_ADVANCE.arg().setInstType(type));
    }

    @Override
    public void algoAdvance(@NonNull Dict.InstType type, InstId instId) {
        subscribe(ChannelType.Types.ALGO_ADVANCE.arg().setInstType(type).setInstId(instId));
    }

    @Override
    public void liquidationWarning(@NonNull Dict.InstType type) {
        subscribe(ChannelType.Types.LIQUIDATION_WARNING.arg().setInstType(type));
    }

    @Override
    public void balanceAndPosition() {
        subscribe(ChannelType.Types.BALANCE_AND_POSITION.arg());
    }

    @Override
    public void submit(PlaceOrdArg... args) {
        if (args.length == 1) {
            exec(OpType.ORDER.args(args));
        } else {exec(OpType.B_ORDERS.args(args));}
    }

    @Override
    public void cancel(@NonNull CancelOrdArg... args) {
        if (args.length == 1) {
            exec(OpType.CANCEL_ORDER.args(args));
        } else {exec(OpType.B_CANCEL_ORDERS.args(args));}
    }

    @Override
    public void amend(@NonNull AmendOrdArg... args) {
        if (args.length == 1) {
            exec(OpType.AMEND_ORDER.args(args));
        } else {exec(OpType.B_AMEND_ORDERS.args(args));}
    }
}
