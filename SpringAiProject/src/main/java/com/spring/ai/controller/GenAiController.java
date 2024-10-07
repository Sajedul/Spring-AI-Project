package com.spring.ai.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ai.services.ChatService;
import com.spring.ai.services.ImageService;
import com.spring.ai.services.RecipeService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GenAiController {
	 private final ChatService chatService;
	 private final ImageService imageService;
	 private final RecipeService recipeService;
	
	 public GenAiController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
		this.chatService = chatService;
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	 
	@GetMapping("ask-ai")
	 public String getResponse(@RequestParam String prompt){
	        return chatService.getResponse(prompt);
	    }
	 @GetMapping("ask-ai-options")
	 public String getResponseOptions(@RequestParam String prompt){
	        return chatService.getResponseOptions(prompt);
	    }
	 
//	 @GetMapping("generate-image")
//	 public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException{
//		 ImageResponse imageResponse = imageService.generateImage(prompt);
//		 String imageUrl = imageResponse.getResult().getOutput().getUrl();
//		 response.sendRedirect(imageUrl);
//	        
//	    }
	 
	 @GetMapping("generate-image")
	 public List<String> generateImage(HttpServletResponse response,
			                           @RequestParam String prompt,
			                           @RequestParam (defaultValue="hd")String quality,
			                           @RequestParam (defaultValue="1")int n,
			                           @RequestParam (defaultValue="1024")int width,
			                           @RequestParam (defaultValue="1024")int height) throws IOException{
		 ImageResponse imageResponse = imageService.generateImage(prompt,quality,n,width,height);
		 
		 
		 //Streams to get urls from Image Response
		 
		 List<String>imageUrls = imageResponse.getResults().stream()
		              .map(result -> result.getOutput().getUrl())
		              .collect(Collectors.toList());
		 return imageUrls;
	    }
	 
	 @GetMapping("recipe-creator")
	 public String recipeCreator(@RequestParam String ingredients,
			                           @RequestParam(defaultValue="any")String cuisine,
			                           @RequestParam(defaultValue="")String dietaryRestrictions
			                           ){
		 return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
	 }
	 
}
