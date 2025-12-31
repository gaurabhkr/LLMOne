package com.llmone.LLMOne.Configuration;

import org.springframework.ai.model.openai.autoconfigure.OpenAiConnectionProperties;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMOneConfiguration {
	
//OpenAiConnection will fetch baseurl and api from application propert
//Qualifiers used to identify different objects using id 
	
	private final String API_KEY;
	private final String BASE_URL;

	LLMOneConfiguration(OpenAiConnectionProperties con){
		this.API_KEY=con.getApiKey();this.BASE_URL=con.getBaseUrl();
		System.out.println(API_KEY+"  "+BASE_URL);
		}
	
	@Bean
	@Qualifier("mistralai")
	public OpenAiChatModel mistralaimodel() {
		
		var options=OpenAiChatOptions.builder().model("mistralai/devstral-2512:free").build();
		var openai=OpenAiApi.builder().baseUrl(BASE_URL).apiKey(API_KEY).build();
		
		return OpenAiChatModel
				.builder()
				.openAiApi(openai)
				.defaultOptions(options)
				.build();
	}
	
	@Bean
	@Qualifier("gpt")
	public OpenAiChatModel gptmodel() {
		
		var options=OpenAiChatOptions.builder().model("openai/gpt-oss-120b").build();
		var openai=OpenAiApi.builder().baseUrl(BASE_URL).apiKey(API_KEY).build();
		
		return OpenAiChatModel
				.builder()
				.openAiApi(openai)
				.defaultOptions(options)
				.build();
	}
	
	@Bean
	@Qualifier("nvidia")
	public OpenAiChatModel nvidiamodel() {
		
		var options=OpenAiChatOptions.builder().model("nvidia/nemotron-3-nano-30b-a3b:free").build();
		var openai=OpenAiApi.builder().baseUrl(BASE_URL).apiKey(API_KEY).build();
		
		return OpenAiChatModel
				.builder()
				.openAiApi(openai)
				.defaultOptions(options)
				.build();
	}
	
	@Bean
	@Qualifier("deepseek")
	public OpenAiChatModel deepseekmodel() {
		
		var options=OpenAiChatOptions.builder().model("tngtech/deepseek-r1t2-chimera:free").build();
		var openai=OpenAiApi.builder().baseUrl(BASE_URL).apiKey(API_KEY).build();
		
		return OpenAiChatModel
				.builder()
				.openAiApi(openai)
				.defaultOptions(options)
				.build();
	}
}
