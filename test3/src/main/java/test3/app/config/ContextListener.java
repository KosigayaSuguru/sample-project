package test3.app.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// do nothing
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			AbandonedConnectionCleanupThread.shutdown();
		} catch (final InterruptedException e) {
		}
	}
}
