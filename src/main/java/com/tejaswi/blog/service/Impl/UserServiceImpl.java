package com.tejaswi.blog.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.tejaswi.blog.exception.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejaswi.blog.entity.User;
import com.tejaswi.blog.entity.UserDto;
import com.tejaswi.blog.repo.UserRepo;
import com.tejaswi.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		// converting user dto to user
		User savedUser = userRepo.save(dtoToUsers(userDto));
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User userByIdUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userByIdUser.setName(userDto.getName());
		userByIdUser.setEmail(userDto.getEmail());
		userByIdUser.setPassword(userDto.getPassword());
		userByIdUser.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(userByIdUser);
		UserDto userDto2 = this.userToDto(updatedUser);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : users) {
			userDtos.add(userToDto(user));
		}
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

		User usertoDeleteUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(usertoDeleteUser);
	}

	public User dtoToUsers(UserDto userDto) {
		// few other class object o/p doesn't show some time so used this below line
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		User users = new User();
		users = modelMapper.map(userDto, User.class);
		return users;

	}

	public UserDto userToDto(User user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserDto userDto = new UserDto();
		userDto = modelMapper.map(user, UserDto.class);
		return userDto;

	}

}
