package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.RetweetsDto;
import com.springboot.blog.repository.RetweetsRepository;
import com.springboot.blog.service.RetweetsService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class RetweetsController {
	
	RetweetsRepository retweetsRepository;
	RetweetsService  retweetsService;
	
	
	public RetweetsController(RetweetsRepository retweetsRepository, RetweetsService retweetsService) {
		super();
		this.retweetsRepository = retweetsRepository;
		this.retweetsService = retweetsService;
	}

	//START :: RETWEET AN OTHER USERS TWEET
	@PostMapping("retweet/{userId}/{tweetId}")
	public  ResponseEntity<RetweetsDto> createRetweet(@Valid @PathVariable(value="userId") long user_id,
			@PathVariable(value="tweetId") long tweet_id,
			@RequestBody RetweetsDto retweetsDto){
		
		 return new ResponseEntity<>(retweetsService.createRetweet(user_id, tweet_id,retweetsDto), HttpStatus.CREATED);
	}
	//END :: RETWEET AN OTHER USERS TWEET
	
	//START :: GET RETWEET COUNT BY TWEET ID
	@GetMapping("retweetCount/{tweetId}")
	public ResponseEntity<Long> getRetweetsCount(@Valid @PathVariable(value="tweetId") long tweet_id,
			@RequestBody RetweetsDto retweetsDto
			){
		
		 Long noOfRetweets = retweetsService.getRetweetsCount(tweet_id,retweetsDto);
		 return new ResponseEntity<Long>(noOfRetweets, HttpStatus.OK);
		
	}
	//END :: GET RETWEET COUNT BY TWEET ID
}
