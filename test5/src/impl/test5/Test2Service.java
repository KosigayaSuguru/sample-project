package impl.test5;

import ab.test5.AbstractService;
import inter.test5.Service;

public class Test2Service extends AbstractService implements Service {

	@Override
	public void startup() {
		System.out.println("Test2Startup");

	}

	@Override
	public void shutdown() {
		System.out.println("Test2Shutdown");

	}
}
