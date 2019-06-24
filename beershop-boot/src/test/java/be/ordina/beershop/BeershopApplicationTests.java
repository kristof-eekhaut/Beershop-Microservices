package be.ordina.beershop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BeershopApplication.class)
@AutoConfigureMockMvc
class BeershopApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void healthCheck() throws Exception {
		mockMvc.perform(get("/actuator/health"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
