package com.springboot.blog.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Likes;
import com.springboot.blog.entity.Tweets;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.LikesDto;
import com.springboot.blog.repository.LikesRepository;
import com.springboot.blog.repository.TweetsRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.LikesService;


@Service
public class LikesServiceImpl implements LikesService{
	
	  private LikesRepository likesRepository;
	  private UserRepository userRepository;
	  private TweetsRepository tweetsRepository;
	  private ModelMapper modelMapper;

	  public LikesServiceImpl(LikesRepository likesRepository, UserRepository userRepository,TweetsRepository tweetsRepository,
				ModelMapper modelMapper) {
			super();
			this.likesRepository = likesRepository;
			this.userRepository = userRepository;
			this.tweetsRepository = tweetsRepository;
			this.modelMapper = modelMapper;
	   }
	   
	  
	    //START :: LIKE A TWEET REST API
    	 @Override
    	 public LikesDto likeTweet(long tweetId,long userId, LikesDto likesDto) {

    	        Likes likes = mapToEntity(likesDto);

    	        // retrieve TWEET entity by id
    	        Tweets tweet = tweetsRepository.findById(tweetId).orElseThrow(
    	                () -> new ResourceNotFoundException("Tweet", "id", tweetId));
    	        System.out.println(tweet);
    	        
    	     // retrieve user entity by id
    	        User user = userRepository.findById(userId).orElseThrow(
    	                () -> new ResourceNotFoundException("User", "id", userId));
    	        System.out.println(user);
    	        // like entity
    	        likes.setLiked(true);
    	        likes.setUser(user);
    	        likes.setTweet(tweet);

    	        // LIKE entity to DB
    	        Likes newLike =  likesRepository.save(likes);

    	        return mapToDTO(newLike);
    	  }
    	  //END :: LIKE A TWEET REST API
    	 
    	 
    	 
    	  //START :: LIKE COUNT ON TWEET REST API
    	  @Override
    	    public Long getLikesCount(long tweetId) {
    	        // retrieve likes by tweetId
    	        List<Likes> likes = likesRepository.findByTweetId(tweetId);
    	        return likes.stream().count();
    	    }
    	    //END :: LIKE COUNT ON TWEET REST API
	    	  
	
		    private LikesDto mapToDTO(Likes likes){
		    	LikesDto likesDto = modelMapper.map(likes, LikesDto.class);
		
		        return  likesDto;
		    }
		
		    private Likes mapToEntity(LikesDto likesDto){
		    	Likes likes = modelMapper.map(likesDto, Likes.class);
		        return  likes;
		    }



}
