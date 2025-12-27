package com.llmone.LLMOne.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.openai.autoconfigure.OpenAiConnectionProperties;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class LLMOneController {

	//To interact with LLM Model:  Using Chat Client(it has more method in response)
	
	//	private ChatClient chatclient;
	//	public LLMOneController(OpenAiChatModel chatmodel) {
	//		this.chatclient=ChatClient.create(chatmodel);
	//	} 
	//chatclient.prompt(message).call().content();
	//chatresponse=chatcleint.prompt(message).call().chatresponse();  return chatresponse.getresult().getoutput().gettext();
	
	
	//To interact with LLM Model: Using ChatModel (less methods in response)	
	
	//private OpenAiChatModel GeminiChatModel;
	//public LLMOneController(OpenAiChatModel chatmodel) {
	//this.GeminiChatModel=chatmodel;
	//}

	private final ChatClient GeminiChatClient;
	private final ChatClient deepseekChatClient;
	private final ChatClient nvidiaChatClient;
	private final ChatClient gptChatClient;
	
	public LLMOneController(
			@Qualifier("nvidia") OpenAiChatModel nvidiamodel,  @Qualifier("gemini") OpenAiChatModel geminimodel,
			@Qualifier("deepseek") OpenAiChatModel deepseekmodel, @Qualifier("gpt") OpenAiChatModel gptmodel) {
		
		this.GeminiChatClient=ChatClient.create(geminimodel);
		this.deepseekChatClient=ChatClient.create(deepseekmodel);
		this.nvidiaChatClient=ChatClient.create(nvidiamodel);
		this.gptChatClient=ChatClient.create(gptmodel);
		
	}
	
	
	@GetMapping("/gemini/{message}")
	public ResponseEntity<String>  geminianswer(@PathVariable String message){
		try {
		String response=GeminiChatClient.prompt(message).call().content();
		System.out.println("Gemini's Response:");
		System.out.println(response);
		
	return ResponseEntity.ok(response);}
		catch(Exception e) {return ResponseEntity.badRequest().body(e.getMessage()); }
	}
	
	@GetMapping("/deepseek/{message}")
	public ResponseEntity<String>  deepseekanswer(@PathVariable String message){
		
		String response=deepseekChatClient.prompt(message).call().content();
		System.out.println("DeepSeek's Response:");
		System.out.println(response);
		
	return ResponseEntity.ok(response);
	}
	
	@GetMapping("/nvidia/{message}")
	public ResponseEntity<String> nvidiaanswer(@PathVariable String message){
		
		String response=nvidiaChatClient.prompt(message).call().content();
		System.out.println("Nvidia Nemotron's Response:");
		System.out.println(response);
		
	return ResponseEntity.ok(response);
	}
	
	@GetMapping("/gpt/{message}")
	public ResponseEntity<String>  gptanswer(@PathVariable String message){
		
		String response=gptChatClient.prompt(message).call().content();
		System.out.println("GPT's Response:");
		System.out.println(response);
		
	return ResponseEntity.ok(response);
	}
	
}
