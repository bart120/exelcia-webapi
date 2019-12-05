package com.exelcia.webapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignupRequest {
	
	@NotBlank
	@Size(min = 4, max = 40)
	private String name;
	
	@NotBlank
	private String username;
	
	@Size(max=40)
	@NotBlank
	private String email;
	
	@Size(min=6, max = 20)
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Mot de passe invlide")
	private String password;
	

}
