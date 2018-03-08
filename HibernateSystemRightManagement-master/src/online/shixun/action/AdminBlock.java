package online.shixun.action;

import java.util.Scanner;

import online.shixun.dao.ResourceDao;
import online.shixun.dao.RoleDao;
import online.shixun.dao.UserDao;

public class AdminBlock {

	private static Scanner sc = new Scanner(System.in);
	private static UserDao userDao = new UserDao();
	private static RoleDao roleDao = new RoleDao();
	private static ResourceDao resourceDao = new ResourceDao();

	public static void adminUser(int userID) {
		int num = 0;
		boolean flag = true;
		while (flag) {

			System.out.println("歡迎登陸 請選擇");
			System.out.println("1.修改信息功能");
			System.out.println("2.角色信息列表");
			System.out.println("3.可用資源");
			System.out.println("4.用戶對應角色及對應資源");
			System.out.println("5.修改");
			System.out.println("6.瀏覽");
			System.out.println("7.刪除");
			num = sc.nextInt();
			if (num >= 1 && num <= 7) {
				flag = false;
			} else {
				System.out.println("您選擇有誤 請重新選擇");
				continue;
			}
		}
		switch (num) {
		case 1:
			alterMessage(userID);
			break;
		case 2:
			roleDao.getMyRoleList(userID);
			break;
		case 3:
			resourceDao.myResourceList(userID);
			break;
		case 4:
			viewUserRoleRes();
			break;
		case 5:
			changeMessage();
			break;

		case 6:
			break;
		case 7:
			break;
		default:
			break;
		}
	}

	private static void changeMessage() {
		int num = 0;
		while (true) {
			System.out.println("请选择修改内容");
			System.out.println("1.资源信息功能");
			System.out.println("2.角色信息功能");
			System.out.println("3.用户信息功能");
			num = sc.nextInt();
			if (num >= 1 && num <= 3) {
				break;
			} else {
				System.out.println("您选择有误 请重新选择");
				continue;
			}
		}
		switch (num) {
		case 1:
			resourceDao.updateResMessage();
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			break;
		}
	}

	private static void viewUserRoleRes() {
		userDao.getAllMessage();
	}

	private static void alterMessage(int userID) {
		boolean flag = true;
		int num = 0;
		while (flag) {
			System.out.println("請選擇所要修改信息");
			System.out.println("1.修改賬號");
			System.out.println("2.修改密碼");
			num = sc.nextInt();
			if (num >= 1 && num <= 2) {
				flag = false;
			} else {
				System.out.println("您選擇有誤 請重新選擇");
				continue;
			}
		}
		switch (num) {
		case 1:
			alterUsername(userID);
			break;
		case 2:
			alterPassword(userID);
			break;
		default:
			break;
		}
	}

	private static void alterPassword(int userID) {
		String password1;
		while (true) {
			System.out.println("请输入密码");
			password1 = sc.next();
			System.out.println("请输入确认密码");
			String password2 = sc.next();
			if (password1.equals(password2)) {
				break;
			} else {
				System.out.println("两次密码不一致");
				continue;
			}
		}
		userDao.changePassword(userID, password1);
	}

	private static void alterUsername(int userID) {
		while (true) {
			System.out.println("請輸入您要更改的用戶名:");
			String username = sc.next();
			int judge = userDao.changeUsername(userID, username);
			if (judge == 1) {
				System.out.println("修改成功");
				break;
			} else {
				System.out.println("当前用户名已存在 请重新选择");
				continue;
			}
		}
	}

}
