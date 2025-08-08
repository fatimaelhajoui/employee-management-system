package com.employee.mangment;

import com.employee.mangment.enteties.Employee;
import com.employee.mangment.repository.EmployeeRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MangmentApplication implements CommandLineRunner{
        @Autowired
        private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MangmentApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Employee emp1= Employee.builder()
                .fullname("Fatima Zahrae El hajoui")
                .cin("DF524163A")
                .email("elhajoui@gmail.com")
                .phone("02415367")
                .salary(6500)
                .hiredate(new Date())
                .active(false)
                .build();
        
        Employee emp2= Employee.builder()
                .fullname("Imane Elwali")
                .cin("D25416")
                .email("elwali@gmail.com")
                .phone("03625416")
                .salary(5200)
                .hiredate(new Date())
                .active(true)
                .build();
        
        Employee emp3= Employee.builder()
                .fullname("Ahmed Jwahir")
                .cin("KJ582")
                .email("jwahir@gmail.com")
                .phone("0566206")
                .salary(4500)
                .hiredate(new Date())
                .active(true)
                .build();
        
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
        
}
