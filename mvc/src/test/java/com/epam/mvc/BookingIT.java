package com.epam.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingIT {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateRetrieveWithMockMVC() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/booking/events"))
				.andExpect(status().is2xxSuccessful());
	}
}
