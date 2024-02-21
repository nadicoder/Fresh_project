package com.project.ElectronicStore.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserDto {
	
	
	@NotBlank
	private String userId;
	
	@Size(min=3,max=15,message="give the message in the descripted form")
	private String userName;
	private String email;
	@NotBlank(message="Password should not be empty")
	private String password;
	@Size(message="ensure the size of the gender")
	private String gender;
	private String about;
	private String imageName;
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", about=" + about + ", imageName=" + imageName + "]";
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(String userId, String userName, String email, String password, String gender, String about,
			String imageName) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.about = about;
		this.imageName = imageName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	

}
