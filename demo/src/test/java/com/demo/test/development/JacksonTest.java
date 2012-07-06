package com.demo.test.development;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.demo.app.entity.JsonObject;
import com.demo.app.entity.User;

/**
 * @author Administrator
 *
 */
public class JacksonTest {
	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	private User user = null;
	private List users = new ArrayList();

	@Before
	public void init() {
		for(int i=0;i<2;i++){
		user = new User();
		user.setName("china-Guangzhou");
		user.setPassword("hoojo_@126.com");
		user.setId("uabajeowivhonhwoevnh123");
		user.setDate(new Date());
		users.add(new JsonObject(user.getId(),user));
		}
		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void writeEntityJSON() {

		try {
			StringWriter sw = new StringWriter();
			objectMapper.writeValue(sw, users);
			String json = sw.toString();
			sw.close();
			//JsonNode jsonNode = objectMapper.readTree(json);
			System.out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destory() {
		try {
			if (jsonGenerator != null) {
				jsonGenerator.flush();
			}
			if (!jsonGenerator.isClosed()) {
				jsonGenerator.close();
			}
			jsonGenerator = null;
			objectMapper = null;
			user = null;
			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
