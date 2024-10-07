package com.spring.ai.services;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	private final OpenAiImageModel openImageModel;

	public ImageService(OpenAiImageModel openImageModel) {
		this.openImageModel = openImageModel;
	}
	
	public ImageResponse generateImage(String prompt,String quality,int n,int width,int height) {
//		ImageResponse imageResponse= openImageModel.call(
//				new ImagePrompt(prompt)
//				);
		
//		ImageResponse imageResponse= openImageModel.call(
//				new ImagePrompt(prompt,OpenAiImageOptions.builder()
//						.withModel("dall-e-2")
//		                .withQuality("hd")
//		                .withN(2)
//		                .withHeight(1024)
//		                .withWidth(1024).build())
//				);
		
		ImageResponse imageResponse= openImageModel.call(
				new ImagePrompt(prompt,OpenAiImageOptions.builder()
						.withModel("dall-e-2")
		                .withQuality(quality)
		                .withN(n)
		                .withHeight(height)
		                .withWidth(width).build())
				);
		return imageResponse;
		
	}
	
	
}
