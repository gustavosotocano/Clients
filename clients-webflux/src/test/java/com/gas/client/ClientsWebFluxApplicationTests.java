package com.gas.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringReader;

import static io.netty.handler.codec.http.HttpConstants.COMMA;
import static java.lang.String.join;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


class ClientsWebFluxApplicationTests {

	@Test
	void contextLoads() {

		String ALLO=join(String.valueOf(COMMA),GET.name(), POST.name());

		System.out.println(ALLO);
	}

}
