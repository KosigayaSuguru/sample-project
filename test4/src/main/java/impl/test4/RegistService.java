package impl.test4;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import inter.test4.IfBean;
import inter.test4.IfService;

@Component
@Scope(value = "prototype")
public class RegistService implements IfService {

	@Autowired
	public IfBean beana;

	@Autowired
	public RegistRepository regist;

	@Autowired
	public Properties properties;

	@Autowired
	public MessageSource messages;

	@Value(value = "${aaa.bbb}")
	public String v1;

	@Value(value = "${aaa.ccc}")
	public String v2;

	public void run() {
		System.out.println("test");
		System.out.println("properties="+properties.getProperty("aaa.bbb"));
		System.out.println("properties="+properties.getProperty("aaa.ccc"));
		System.out.println("messages="+messages.getMessage("aaa.bbb", null, Locale.getDefault()));
		System.out.println("messages="+messages.getMessage("aaa.ccc", null, Locale.getDefault()));
	}
}
