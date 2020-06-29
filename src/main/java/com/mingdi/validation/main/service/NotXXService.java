package com.mingdi.validation.main.service;

import javax.validation.Valid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.mingdi.validation.main.model.NotXXTestVo;

@Service
public class NotXXService  implements CommandLineRunner{


	public void test(@Valid NotXXTestVo vo) {
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		NotXXTestVo vo = new NotXXTestVo();
		
		test(vo);
	}

}
