package cn.yt4j.dubbo.provider.dubbo;

import cn.yt4j.dubbo.api.DemoApiService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author gyv12345@163.com
 */
@Service
@DubboService
public class DemoApiServiceImpl implements DemoApiService {

	@Override
	public String sayHi(String flow) {
		return "123456";
	}

}
