package be.faros.controller;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SSEController {

	/*
	 * Servlet 3, Async Support
	 * https://spring.io/blog/2012/05/07/spring-mvc-3-2-preview-introducing-
	 * servlet-3-async-support
	 */

	/*
	 * http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/http_streaming/
	 * 
	 * Returns ResponseBodyEmitter can be used for HTTP Streaming
	 * 
	 * Voorloper van reactive programming ? Heeft error handling & onComplete zoals observer/reactive programming
	 */
	@RequestMapping("/httpstreaming")
	public ResponseBodyEmitter httpStreaming() {
		final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(() -> {
			for (int i = 0; i < 1000; i++) {
				try {
					emitter.send(i + " - ", MediaType.TEXT_PLAIN);

					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
					emitter.completeWithError(e);
					return;
				}
			}

			emitter.complete();
		});

		return emitter;
	}

	/*
	 * http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/sse-emitter/
	 * 
	 * A variation of the "HTTP Streaming" technique except that events pushed from the 
	 * server are formatted according to the W3C Server-Sent Events specification
	 */
	@RequestMapping("/httpStreamingWithSSE")
	public ResponseBodyEmitter httpStreamingWithSSE() {
		final SseEmitter emitter = new SseEmitter();

		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(() -> {
			for (int i = 0; i < 20; i++) {
				try {
					emitter.send(LocalTime.now().toString(), MediaType.TEXT_PLAIN);

					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
					emitter.completeWithError(e);
					return;
				}
			}

			emitter.complete();
		});

		return emitter;
	}
}
