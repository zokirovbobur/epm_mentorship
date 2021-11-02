package com.epam.mvc.dto;

import com.epam.mvc.dao.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private String fullName;
	private String userName;
	private String password;

	public UserEntity toEntity(){
		return new UserEntity(fullName, userName, password);
	}
}
