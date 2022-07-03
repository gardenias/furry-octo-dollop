package com.ladder.quant.endpoints.okx.ws.args;

import javax.crypto.Mac;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ladder.quant.endpoints.okx.domain.Dict;

@Setter
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LoginArg extends Arg {

    private final String apiKey;
    private final String passphrase;

    private long timestamp;
    private String sign;
    @JsonIgnore
    private final transient Mac mac;

    public LoginArg(String apiKey, String passphrase, String sec) {
        this.apiKey = apiKey;
        this.passphrase = passphrase;
        this.mac = Dict.sha256(sec);
    }

    public LoginArg sign() {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.sign = Dict.signature(timestamp + "GET/users/self/verify", this.mac);
        return this;
    }
}
