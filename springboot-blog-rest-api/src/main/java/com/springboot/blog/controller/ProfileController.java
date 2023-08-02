package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.ProfileDto;
import com.springboot.blog.payload.TweetsDto;
import com.springboot.blog.service.ProfileService;
import com.springboot.blog.service.TweetsService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/profile")
@Tag(
        name = "CRUD REST APIs for Profile"
)
public class ProfileController {

	private ProfileService profileService;
	private TweetsService tweetsService;

    public ProfileController(ProfileService profileService,TweetsService tweetsService) {
        this.profileService = profileService;
        this.tweetsService =tweetsService;
    }
    
    
    //START :: GET PROFILE BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable(name = "userId") long id){
    	
        return ResponseEntity.ok(profileService.getProfileById(id));
    }
    //END :: GET PROFILE BY ID

    
   //START :: GET USER FOLLOWERS BY USER ID
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Long> getUserFollowersById(@PathVariable(name = "userId") long id){
    	
        return ResponseEntity.ok(profileService.getUserFollowersById(id));
    }
    //END :: GET USER FOLLOWERS BY USER ID
    
    //START :: GET TWEETS LIST BY USER ID
    @GetMapping("/{userId}/ListOfOwnTweets")
    public List<TweetsDto> getTweetsByUserId(@PathVariable(value = "userId") Long user_id){
    	
        return tweetsService.getTweetsByUserId(user_id);
    }
    //END :: GET TWEETS LIST BY USER ID
    
    //START :: GET LIST OF OWN , LIKED  AND FOLLOWED TWEETS
    @GetMapping("/{userId}/OwnLikedRetweetedTweets")
    public List<TweetsDto> getAllOwnLikedAndRetweetedList(@PathVariable(value = "userId") Long user_id){
    	
        return tweetsService.getAllOwnLikedAndRetweetedList(user_id);
    }
    //END :: GET LIST OF OWN , LIKED  AND FOLLOWED TWEETS
    
    
   //START :: UPDATE PROFILE OF THE USER
    @PutMapping("/{userId}")
    public ResponseEntity<ProfileDto> updateProfile(@Valid @RequestBody ProfileDto profileDto, @PathVariable(name = "userId") long id){

       ProfileDto profileResponse = profileService.updateProfile(profileDto, id);

       return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }
    //END :: UPDATE PROFILE OF THE USER
}
