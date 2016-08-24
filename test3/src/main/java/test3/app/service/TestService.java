package test3.app.service;

import org.springframework.transaction.annotation.Transactional;

public class TestService {

	@Transactional
	public boolean service() {
		return true;
	}
}
