package test3.web.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.options.Option;
import com.mashape.unirest.http.options.Options;
import com.mashape.unirest.http.utils.SyncIdleConnectionMonitorThread;

@RestController
public class AsyncController extends WebApplicationObjectSupport {

	@RequestMapping(value = "/Async1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String async1() throws Exception {

		String yql = "";
		String url = "";
		String params = "&format=json&diagnostics=true&env=store://datatables.org/alltableswithkeys&callback=";

		ExecutorService a = Executors.newFixedThreadPool(10);
		SyncIdleConnectionMonitorThread monitor = (SyncIdleConnectionMonitorThread) Options.getOption(Option.SYNC_MONITOR);
		monitor.interrupt();

		Future<String> submit = a.submit(() -> {
			return "hoge";
		});

		ArrayList<Future<HttpResponse<String>>> list = new ArrayList<>();
		// YQL: select * from html where url='http://kancolle.doorblog.jp/?p=1' and xpath='//h2[@class="article-title entry-title"]//a'
		for (int idx = 1; idx < 100; idx++) {
			yql = String.format(
					"select * from html where url='http://kancolle.doorblog.jp/?p=%d' and xpath='//h2[@class=\"article-title entry-title\"]//a'",
					idx);
			url = "https://query.yahooapis.com/v1/public/yql?q=" + URLEncoder.encode(yql, "UTF-8") + params;

			Future<HttpResponse<String>> asStringAsync = Unirest.get(url).asStringAsync();
			list.add(asStringAsync);
		}

		String string = submit.get();

		for (Future<HttpResponse<String>> future : list) {
			HttpResponse<String> aa = future.get();
			System.err.println(aa.getBody());
		}

		return string;
	}
}
