package com.llmone.LLMOne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = {OpenAiChatAutoConfiguration.class})
public class LlmOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(LlmOneApplication.class, args);
	}

}
