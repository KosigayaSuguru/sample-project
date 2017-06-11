package test3.web.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * よくわからない設定
 * https://fasterxml.github.io/jackson-databind/javadoc/2.8/com/fasterxml/jackson/databind/MapperFeature.html
 *
 * obj→stringの設定
 * https://fasterxml.github.io/jackson-databind/javadoc/2.8/com/fasterxml/jackson/databind/SerializationFeature.html
 *
 * string→objの設定
 * https://fasterxml.github.io/jackson-databind/javadoc/2.8/com/fasterxml/jackson/databind/DeserializationFeature.html
 *
 */
@RestController
public class JacksonController extends WebApplicationObjectSupport {

	/**
	 * デシリアライズで生成したMapをキー名順にソートして、文字列にシリアライズする
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/JacksonTest1")
	public String jackson1() throws Exception {
		String aa = "{\"aaaa\":\"aaaa\", \"bbbb\":\"bbbb\", \"cccc\":{\"eeee\":\"eeee\", \"dddd\":\"dddd\"} }";

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

		@SuppressWarnings("unchecked")
		HashMap<String, Object> value = objectMapper.readValue(aa, HashMap.class);

		return objectMapper.writeValueAsString(value);

	}

}
