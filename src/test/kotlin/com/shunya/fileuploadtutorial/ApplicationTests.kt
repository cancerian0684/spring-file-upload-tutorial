package com.shunya.fileuploadtutorial

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@AutoConfigureMockMvc
@SpringBootTest
class ApplicationTests {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun shouldUploadFile() {
        val multiPartFile = MockMultipartFile("file", "test.txt", "text/plain", "javacodemonk".toByteArray())
        this.mvc.perform(multipart("/fileupload").file(multiPartFile))
				.andExpect(status().isOk)
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("\$.success").value("true"))
    }

}
