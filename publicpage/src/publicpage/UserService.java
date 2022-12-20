package publicpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
	public static List<User_test> delete(User_test user) {
		List<User_test> list = UserService.getUsers();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == user.getId()) {		
				list.remove(i);
			}
		}
		return list;
	}

	public static List<User_test> getUsers() {
//		return Arrays.asList(new User_test(101, "FOne", "LOne", "One@gamil.com"),
//				new User_test(102, "FTwo", "LTwo", "Two@gamil.com"),
//				new User_test(103, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(104, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(105, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(106, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(107, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(108, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(109, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(110, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(111, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(112, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(113, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(114, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(115, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(116, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(117, "FThree", "LThree", "Three@gamil.com"),
//				new User_test(118, "FThree", "LThree", "Three@gamil.com"));
		List<User_test> list = new ArrayList<User_test>();
		list.add(new User_test(101, "FOne", "LOne", "One@gamil.com"));
		list.add(new User_test(102, "FTwo", "LTwo", "Two@gamil.com"));
		list.add(new User_test(103, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(104, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(105, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(106, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(107, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(108, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(109, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(110, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(111, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(112, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(113, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(114, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(115, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(116, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(117, "FThree", "LThree", "Three@gamil.com"));
		list.add(new User_test(118, "FThree", "LThree", "Three@gamil.com"));
		return list;
	}

	public static List<User_test> update(User_test user) {
		List<User_test> list = UserService.getUsers();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == user.getId()) {
				list.get(i).setFirstName(user.getFirstName());
				list.get(i).setLastName(user.getLastName());
				list.get(i).setEmail(user.getEmail());
			}
		}
		return list;
	}
	
	
}
