package ro.papetti.livrari;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.papetti.livrari.configs.formaters.DateFormater;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivrariWebApplicationTests {
	@Autowired
	private DateFormater dateFormater;

	@Test
	void contextLoads() {
	}

	@Test
	void testIncarcareDateFormater(){
		assertNotNull(dateFormater);
		System.out.println(dateFormater);

	}

}


//ss