package com.llmone.LLMOne.Controller;

import java.util.Arrays;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.BasedOptionsHolder.Options;

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
	
	//For markdown to html readable format we use flexmark (parser,htmlrendere,mutabledataset,options)
	
	public final Parser parser;
	public final HtmlRenderer renderer;
	public MutableDataSet options=new MutableDataSet();
	
	private final ChatClient MistralaiChatClient;
	private final ChatClient deepseekChatClient;
	private final ChatClient nvidiaChatClient;
	private final ChatClient gptChatClient;
	
	
	public LLMOneController(
			@Qualifier("nvidia") OpenAiChatModel nvidiamodel,  @Qualifier("mistralai") OpenAiChatModel mistralaimodel,
			@Qualifier("deepseek") OpenAiChatModel deepseekmodel, @Qualifier("gpt") OpenAiChatModel gptmodel) {
		
		this.MistralaiChatClient=ChatClient.create(mistralaimodel);
		this.deepseekChatClient=ChatClient.create(deepseekmodel);
		this.nvidiaChatClient=ChatClient.create(nvidiamodel);
		this.gptChatClient=ChatClient.create(gptmodel);
		
		options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(),EmojiExtension.create()));
		this.parser=Parser.builder(options).build();
		this.renderer=HtmlRenderer.builder(options).build();
	}
	
	
	@GetMapping("/mistralai/{message}")
	public ResponseEntity<String>  mistralaianswer(@PathVariable String message){
		try {
		String response=MistralaiChatClient.prompt(message).call().content();
		System.out.println("Mistralai's Response:");
		//it convert markdown format to html format
		response=renderer.render(parser.parse(response));
		System.out.println(response);
		return ResponseEntity.ok(response);
		}
		catch(Exception e) {return ResponseEntity.badRequest().body(e.getMessage()); }
	}
	
	
	@GetMapping("/deepseek/{message}")
	public ResponseEntity<String>  deepseekanswer(@PathVariable String message){
		try {
		String response=deepseekChatClient.prompt(message).call().content();
		System.out.println("DeepSeek's Response:");
		response=renderer.render(parser.parse(response));
		System.out.println(response);
		return ResponseEntity.ok(response);
		}		
		catch(Exception e) {return ResponseEntity.badRequest().body(e.getMessage()); }
	}
	
	
	@GetMapping("/nvidia/{message}")
	public ResponseEntity<String> nvidiaanswer(@PathVariable String message){
		try {
		String response=nvidiaChatClient.prompt(message).call().content();
		System.out.println("Nvidia Nemotron's Response:");
		response=renderer.render(parser.parse(response));
		System.out.println(response);
		return ResponseEntity.ok(response);
		}
		catch(Exception e) {return ResponseEntity.badRequest().body(e.getMessage()); }
	}
	
	
	@GetMapping("/gpt/{message}")
	public ResponseEntity<String>  gptanswer(@PathVariable String message){
		try {
		String response=gptChatClient.prompt(message).call().content();
		System.out.println("GPT's Response:");
		response=renderer.render(parser.parse(response));
		System.out.println(response);
		return ResponseEntity.ok(response);
		}
		catch(Exception e) {return ResponseEntity.badRequest().body(e.getMessage()); }
	}
	
	
	
}
