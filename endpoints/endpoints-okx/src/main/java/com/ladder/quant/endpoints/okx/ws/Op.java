package com.ladder.quant.endpoints.okx.ws;

import java.util.List;

import com.ladder.quant.endpoints.core.ws.TextMessageable;

import com.ladder.quant.endpoints.okx.ws.args.Arg;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.socket.TextMessage;

import com.p.common.base.json.JsonPrinter;

@Setter
@Getter
@Accessors(chain = true)
public class Op implements TextMessageable {

    private List<Arg> args;
    private OpType op;

    @JsonIgnore
    private TextMessage delegate;

    @JsonIgnore
    public TextMessage of() {
        if (delegate == null) {delegate = new TextMessage(JsonPrinter.jsonPrint(this));}
        return delegate;
    }
}
