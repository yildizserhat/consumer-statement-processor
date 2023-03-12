package yildiz.serhat.consumerstatementprocessor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldUploadFile() throws Exception {
        String url = "/v1/transactions/reports";
        MockMultipartFile file = new MockMultipartFile("file", "records.csv",
                "text/csv", "Records".getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart(url)
                        .file(file))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetRequestFromXMLFile() throws Exception {
        String url = "/v1/transactions/reports";
        String request = """
                <records>
                  <record reference="187997">
                    <accountNumber>NL91RABO0315273637</accountNumber>
                    <description>Clothes for Rik King</description>
                    <startBalance>57.6</startBalance>
                    <mutation>-32.98</mutation>
                    <endBalance>24.62</endBalance>
                  </record>
                  </records>
                """;
        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_XML_VALUE)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}