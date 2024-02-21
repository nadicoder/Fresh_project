package com.project.ElectronicStore.Servicelayer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Dto.UserDto;
import com.project.ElectronicStore.Entities.User;
import com.project.ElectronicStore.Exceptions.ResourceNotFoundException;
import com.project.ElectronicStore.Helper.Helper;
import com.project.ElectronicStore.Repository.UserRepository;
@Service
public class UserServiceImpel implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpel.class);
	

	@Override
	public UserDto create(UserDto userDto) {
		// TODO Auto-generated method stub
		
		
		//conversion of dto to entitie
		
		User user=mapper.map(userDto,User.class);
		
		User userf=userRepository.save(user);
		
		
		
		//conversion of entitie to userrepo
		return mapper.map(userf, UserDto.class);
	}

	@Override
	public UserDto update(String userId, UserDto userDto) {
		// TODO Auto-generated method stub
		//getting
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException());
		logger.info("here we are getting him "+user);
		//updating via sent data
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setImageName(userDto.getImageName());
		
		//sending back to repository
		
		
		User user2=userRepository.save(user);
		
		return mapper.map(user2, UserDto.class);
	}

	@Override
	public UserDto getSingleUser(String userId) {
		// TODO Auto-generated method stub
		
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException());
		
		
		return mapper.map(user, UserDto.class);
	}

	@Override
	public PageableResponse<UserDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		//for direction terinary operator ()?():();
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
		PageRequest pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<User> page=userRepository.findAll(pageable);
		//List<User> user=page.getContent();
		
//		List<UserDto> userDtos=user.stream().map(i->mapper.map(i, UserDto.class)).collect(Collectors.toList());
//		
//		PageableResponse<UserDto> response=new PageableResponse<>();
//		response.setContent(userDtos);
//		response.setPageNumber(page.getNumber());
//		response.setPageSize(page.getSize());
//		response.setTotalElements(page.getTotalElements());
//		response.setTotalPages(page.getTotalPages());
//		response.setLastPage(page.isLast());
//		return response;
		
		
		PageableResponse<UserDto> response=Helper.getPageableResponse(page, UserDto.class);
		
		return response;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException());
		userRepository.delete(user);
	}

	@Override
	public UserDto searchByEmail(String email) {
		// TODO Auto-generated method stub
		
		User user=((Optional<User>) userRepository.findByEmail(email)).orElseThrow(()->new ResourceNotFoundException());
		
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> searchByKeyword(String keyword) {
		// TODO Auto-generated method stub
		
		List<User> users=userRepository.findByUserNameContaining(keyword);
		
		List<UserDto> userDtos=users.stream().map(i->mapper.map(i,UserDto.class)).collect(Collectors.toList());
		
		
		
		return userDtos;
	}

}
