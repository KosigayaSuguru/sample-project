package impl.test5;

import ab.test5.AbstractService;
import inter.test5.Service;

public class Test1Service extends AbstractService implements Service {

	@Override
	public void startup() {
		System.out.println("Test1Startup");

	}

	@Override
	public void shutdown() {
		System.out.println("Test1Shutdown");

	}

}
