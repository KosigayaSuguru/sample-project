package test3.web.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;

@RestController
public class AsyncController extends WebApplicationObjectSupport {

	@RequestMapping(value = "/Async1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String async1() throws Exception {

		ExecutorService requestExecutor = Executors.newFixedThreadPool(40);

		ArrayList<FutureTask<String>> list = new ArrayList<>();

		Logger logger = LoggerFactory.getLogger("Async1");

		// idxページ分のリクエストを非同期に投げる
		// ※ Executor分だけ同時にリクエストし、OkHttpのプールが生成される
		OkHttpClient okHttpClient = new OkHttpClient();
		// YQL: select * from html where url='http://kancolle.doorblog.jp/?p=1' and xpath='//h2[@class="article-title entry-title"]//a'
		for (int idx = 1; idx < 200; idx++) {

			final int idx_ = idx;
			FutureTask<String> aa = new FutureTask<>(() -> {
				String yql = String.format(
						"select * from html where url='http://kancolle.doorblog.jp/?p=%d' and xpath='//h2[@class=\"article-title entry-title\"]//a'",
						idx_);
				String params = "&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=";
				String url = "https://query.yahooapis.com/v1/public/yql?q=" + URLEncoder.encode(yql, "UTF-8") + params;

				Request req = new Request.Builder().url(url).build();
				okhttp3.Response res = okHttpClient.newCall(req).execute();

				logger.debug(String.format("pool %d, url:%s", okHttpClient.connectionPool().connectionCount(), url));

				return res.body().string();
			});
			requestExecutor.submit(aa);
			list.add(aa);
		}

		ObjectMapper jackson = new ObjectMapper();
		LinkedHashMap<String, Object> ret = new LinkedHashMap<>();
		ArrayList<Object> retval = new ArrayList<>();

		// リクエスト数分の結果を取得してjson返却用のマップを生成する
		for (FutureTask<String> futureTask : list) {
			String tmp = futureTask.get();

			LinkedHashMap<String, Object> tmpmap = new LinkedHashMap<>();

			tmpmap = jackson.readValue(tmp, LinkedHashMap.class);
			@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
			List<LinkedHashMap<String, String>> result = (List) ((Map) ((Map) tmpmap.get("query")).get("results")).get("a");

			result.forEach((item) -> {
				item.remove("title");
				item.remove("rel");
				retval.add(item);
			});
		}

		ret.put("urls", retval);
		return jackson.writeValueAsString(ret);
	}
}
