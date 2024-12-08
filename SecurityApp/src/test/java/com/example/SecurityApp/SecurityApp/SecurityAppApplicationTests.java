package com.example.SecurityApp.SecurityApp;

import com.example.SecurityApp.SecurityApp.Entity.UserEntity;
import com.example.SecurityApp.SecurityApp.Service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@RequiredArgsConstructor
class SecurityAppApplicationTests {
	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		UserEntity userEntity = new UserEntity(1L,"dk@gmail.com","123456");
		if(jwtService !=null){
			System.out.println(jwtService.getUserDetails(jwtService.getToken(userEntity)));
		}


	}

}
