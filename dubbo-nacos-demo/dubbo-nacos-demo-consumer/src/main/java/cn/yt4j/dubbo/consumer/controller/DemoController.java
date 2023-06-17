package cn.yt4j.dubbo.consumer.controller;

import cn.yt4j.dubbo.api.DemoApiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

	@DubboReference
	private DemoApiService demoApiService;

	@GetMapping("test")
	public String test() {
		return demoApiService.sayHi("ddd");
	}

}
