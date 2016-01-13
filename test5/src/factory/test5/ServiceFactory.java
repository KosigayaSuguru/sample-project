package factory.test5;

import impl.test5.Test1Service;
import impl.test5.Test2Service;
import inter.test5.Service;

public class ServiceFactory {

	public static Service create(String hoge){

		if(hoge.equals("TEST1")){
			return new Test1Service();

		} else if(hoge.equals("TEST2")){
			return new Test2Service();
		}

		return null;
	}
}
