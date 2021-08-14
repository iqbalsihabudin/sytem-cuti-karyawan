package uas.kel2.sytemcutikaryawan;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SytemCutiKaryawanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SytemCutiKaryawanApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
