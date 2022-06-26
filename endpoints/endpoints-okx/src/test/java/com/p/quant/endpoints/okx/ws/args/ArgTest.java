package com.p.quant.endpoints.okx.ws.args;

import lombok.val;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import com.p.common.base.json.JsonPrinter;

import static org.assertj.core.api.Assertions.assertThat;

class ArgTest {

    @Test
    void mapSerializedAsField() {
        val arg = ChannelType.Types.STATUS.arg();
        arg.set("more_1", 1);
        arg.set("uly", null);

        val jsonPrint = JsonPrinter.jsonPrint(arg);
        System.out.println(jsonPrint);
        val context = JsonPath.parse(jsonPrint);

        val length = context.read("$.length()", Integer.class);
        assertThat(length).isEqualTo(2);

        val integer = context.read("$.more_1", Integer.class);
        assertThat(integer).isEqualTo(1);
    }
}
