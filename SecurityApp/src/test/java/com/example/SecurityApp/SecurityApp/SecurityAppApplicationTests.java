package com.example.SecurityApp.SecurityApp;

import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Service.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@RequiredArgsConstructor
class SecurityAppApplicationTests {
	@Autowired
	private  JwtFilter jwtFilter;

	@Test
	void contextLoads() {
		UserEntity userEntity = new UserEntity(1L,"dk@gmail.com","123456");
		if(jwtFilter!=null){
			System.out.println(jwtFilter.getUserDetails(jwtFilter.getToken(userEntity)));
		}


	}

}
