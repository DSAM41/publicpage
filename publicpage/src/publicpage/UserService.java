package publicpage;

import java.util.Arrays;
import java.util.List;

public class UserService {
	public static List<User_test> getUsers(){
		return Arrays.asList(new User_test(101, "FOne", "LOne", "One@gamil.com"),
				new User_test(102, "FTwo", "LTwo", "Two@gamil.com"),
				new User_test(103, "FThree", "LThree", "Three@gamil.com"));
	}
}
