package com.rbat;

import com.rbat.api.PlayerParticipantAPI;
import com.rbat.service.PlayerParticipantService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(PlayerParticipantAPI.class)
class ApplicationTests {

	@MockBean
	private PlayerParticipantService playerParticipantService;

	@Test
	void contextLoads() {
	}

}
