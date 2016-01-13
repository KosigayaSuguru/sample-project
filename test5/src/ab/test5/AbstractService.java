package ab.test5;

import inter.test5.Service;

public abstract class AbstractService implements Service {

	public void run(){

		startup();

		System.out.println("main process");

		shutdown();
	}

	public abstract void startup();
	public abstract void shutdown();
}
