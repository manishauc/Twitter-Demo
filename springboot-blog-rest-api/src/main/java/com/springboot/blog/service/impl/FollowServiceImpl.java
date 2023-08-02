package com.springboot.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.FollowUser;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.FollowUserDto;
import com.springboot.blog.repository.FollowRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.FollowService;


@Service
public  class FollowServiceImpl implements FollowService{
	
	  
	  private UserRepository userRepository;
	  private FollowRepository followRepository;
	  private ModelMapper modelMapper;

	  
	
	    public FollowServiceImpl( UserRepository userRepository,FollowRepository followRepository,
				ModelMapper modelMapper) {
			super();
		
			this.userRepository = userRepository;
			this.modelMapper = modelMapper;
			this.followRepository=followRepository;
		}


    	//START :: FOLLOW  AND UNFOLLOW THE OTHER USER
		@Override
		public String followUser(long fromUserId, long toUserId,FollowUserDto followUserDto) {
			
				FollowUser followUser = mapToEntity(followUserDto);
				
			 	User fromUserId1 = userRepository.findById(fromUserId).orElseThrow(
		                () -> new ResourceNotFoundException("User", "id", fromUserId));
			 	User toUserId1 = userRepository.findById(toUserId).orElseThrow(
		                () -> new ResourceNotFoundException("User", "id", toUserId));
			 
				//check if already followed or not
				Long checkAlreadyExist = followRepository.CheckIfAlreadyFollowed(fromUserId,toUserId);
				
				if(checkAlreadyExist < 1) {
				
						// set follower data to entity
						 followUser.setFollowedBy(fromUserId1);
						 followUser.setFollowedTo(toUserId1);
				         followRepository.save(followUser);
				         return "followed";   
			     }else {
				
						//UNFOLLOW 
						Long CheckIfAlreadyFollowedId = followRepository.CheckIfAlreadyFollowedId(fromUserId, toUserId);
						followRepository.deleteById(CheckIfAlreadyFollowedId);
				        return "unfollowed";
			     }
			 
			}

		   //END :: FOLLOW  AND UNFOLLOW THE OTHER USER
   
	
//		    private FollowUserDto mapToDTO(FollowUser followUser){
//		    	FollowUserDto followUserDto = modelMapper.map(followUser, FollowUserDto.class);
//		
//		        return  followUserDto;
//		    }
		
		    private FollowUser mapToEntity(FollowUserDto followUserDto){
		    	FollowUser followUser = modelMapper.map(followUserDto, FollowUser.class);
		        return  followUser;
		    }


}
