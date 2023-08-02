package com.springboot.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.TweetsDto;
import com.springboot.blog.service.TweetsService;

@RestController
@RequestMapping("/api/auth")
public class FeedsController {
	
	private TweetsService tweetsService;

    public FeedsController(TweetsService tweetsService) {
   
        this.tweetsService =tweetsService;
    }
    
    //Start:: TWEETS BY USER AND THOSE THEY FOLLOWED
    
	@GetMapping("/userAndFollowedTweets/{userId}")
	public List<TweetsDto> ResponseEntity(@PathVariable(value="userId") long user_id){
		List<TweetsDto> TweetsList = tweetsService.getAllUserAndFollowedTweets(user_id);	
		 return TweetsList;
		 
	}
	 //End:: TWEETS BY USER AND THOSE THEY FOLLOWED
}
