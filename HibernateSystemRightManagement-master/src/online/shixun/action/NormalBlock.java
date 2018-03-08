package online.shixun.action;

import java.util.Scanner;

import online.shixun.dao.ResourceDao;
import online.shixun.dao.RoleDao;
import online.shixun.dao.UserDao;

public class NormalBlock {

	private static int num = -1;
	private static Scanner sc = new Scanner(System.in);
	private static UserDao userDao = new UserDao();
	private static RoleDao roleDao = new RoleDao();
	private static ResourceDao resourceDao = new ResourceDao();

	public static int normalUser(int userID) {
		while (true) {
			System.out.println("歡迎登陸 請選擇：");
			System.out.println("1.修改賬號名");
			System.out.println("2.修改密碼");
			System.out.println("3.角色信息列表");
			System.out.println("4.可用資源");
			System.out.println("0.退出");
			num = sc.nextInt();
			if (num == 1 || num == 2 || num == 3 || num == 4 || num == 0) {
				break;
			}
		}
		switch (num) {
		case 1:
			System.out.println("请输入要更改的用户名：");
			String username = sc.next();
			userDao.changeUsername(userID, username);
			System.out.println("更改用户名成功");
			break;
		case 2:
			while (true) {
				System.out.println("请输入要更改的密码：");
				String password1 = sc.next();
				System.out.println("请输入要更改的密码：");
				String password2 = sc.next();
				if (password1.equals(password2)) {
					userDao.changePassword(userID, password1);
					System.out.println("更改用户名成功");
					break;
				} else {
					System.out.println("两次密码不一致 请再次输入");
				}
			}
		case 3:
			roleDao.getMyRoleList(userID);
			break;
		case 4:
			resourceDao.myResourceList(userID);
			break;
		case 0:
			VisitorBlock.visitor(); // 返回上一級
		default:
			break;
		}

		return 0;
	}
}
