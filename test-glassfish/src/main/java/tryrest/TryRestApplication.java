package tryrest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class TryRestApplication extends ResourceConfig {

	public TryRestApplication() {
		packages(TryRestApplication.class.getPackage().getName());

		// URL拡張子とコンテンツタイプのマッピング
		Map<String, MediaType> mappings = new HashMap<>();
		mappings.put("xml", MediaType.APPLICATION_XML_TYPE);
		mappings.put("json", MediaType.APPLICATION_JSON_TYPE);
		property(ServerProperties.MEDIA_TYPE_MAPPINGS, mappings);
	}
}
