package test3;

import org.springframework.transaction.annotation.Transactional;

public class TestService {

	@Transactional
	boolean service() {
		return true;
	}
}
