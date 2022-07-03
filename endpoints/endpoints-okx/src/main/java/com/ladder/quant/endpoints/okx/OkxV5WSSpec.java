package com.ladder.quant.endpoints.okx;

import com.ladder.quant.endpoints.okx.ws.OkxV5WSPrivateSpec;
import com.ladder.quant.endpoints.okx.ws.OkxV5WSPublicSpec;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import com.ladder.quant.endpoints.okx.ws.OkxV5WSPrivateSpecImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class OkxV5WSSpec {

    @Accessors(fluent = true)
    private OkxV5WSPrivateSpec priv;
    @Accessors(fluent = true)
    private final OkxV5WSPublicSpec pub;

    public OkxV5WSSpec(OkxV5WSPrivateSpec privateSpec, OkxV5WSPublicSpec publicSpec) {
        this.priv = privateSpec;
        this.pub = publicSpec;
    }

    public void priv(String key, String sec, String passphrase) {
        if (priv == null) {
            priv = new OkxV5WSPrivateSpecImpl(new LoggingWebSocketHandler(log));
        }
        priv.login(key, sec, passphrase);
    }
}
