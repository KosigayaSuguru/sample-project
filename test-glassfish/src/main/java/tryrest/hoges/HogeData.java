package tryrest.hoges;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * ほげデータ。
 */
@Data
@XmlRootElement
public class HogeData {

	/** 数値。 */
	@FormParam("integer")
	private Integer integer;

	/** 文字列。 */
	@FormParam("string")
	private String string;

	/** 文字列リスト。 */
	@FormParam("strings")
	private List<String> strings;

}
