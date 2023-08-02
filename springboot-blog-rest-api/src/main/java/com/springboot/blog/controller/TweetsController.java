package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.TweetsDto;
import com.springboot.blog.service.TweetsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class TweetsController {
	
	TweetsService tweetsService;
	
	 public TweetsController(TweetsService tweetsService) {
		super();
		this.tweetsService = tweetsService;
	}

	   //START :: CREATE TWEET BY USER 
	   @PostMapping("/users/{userId}/tweets")
	    public ResponseEntity<TweetsDto> createTweet(@PathVariable(value = "userId") long user_id,
	                                                    @Valid @RequestBody TweetsDto tweetsDto){
	        return new ResponseEntity<>(tweetsService.createTweet(user_id, tweetsDto), HttpStatus.CREATED);
	    }
	   //END :: CREATE TWEET BY USER 
	   
	   
	   //START :: GET ALL TWEETS BY USER ID
	  @GetMapping("/users/{userId}/tweets")
	    public List<TweetsDto> getTweetsByUserId(@PathVariable(value = "userId") Long user_id){
	        return tweetsService.getTweetsByUserId(user_id);
	    }
	    //END :: GET ALL TWEETS BY USER ID
	 
	 
	   //START :: GET TWEETS BY ID
	   @GetMapping("/users/{userId}/tweets/{tweetId}")
	    public ResponseEntity<TweetsDto> getTweetById(@PathVariable(value = "userId") Long user_id,
	                                                     @PathVariable(value = "tweetId") Long tweetId){
		 TweetsDto tweetsDto = tweetsService.getTweetById(user_id, tweetId);
	        return new ResponseEntity<>(tweetsDto, HttpStatus.OK);
	    }
	    //END :: GET TWEETS BY ID
	 
	 
//	    @PutMapping("/users/{user_id}/tweets/{id}")
//	    public ResponseEntity<TweetsDto> updateTweet(@PathVariable(value = "user_id") Long user_id,
//	                                                    @PathVariable(value = "id") Long tweetId,
//	                                                    @Valid @RequestBody TweetsDto tweetsDto){
//	    	TweetsDto updatedTweet = tweetsService.updateTweet(user_id, tweetId, tweetsDto);
//	        return new ResponseEntity<>(updatedTweet, HttpStatus.OK);
//	    }

//	    @DeleteMapping("/users/{user_id}/tweets/{id}")
//	    public ResponseEntity<String> deleteTweet(@PathVariable(value = "user_id") Long user_id,
//	                                                @PathVariable(value = "id") Long tweetId){
//	    	tweetsService.deleteTweet(user_id, tweetId);
//	        return new ResponseEntity<>("Tweet deleted successfully", HttpStatus.OK);
//	    }
}
