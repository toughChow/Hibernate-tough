package online.shixun.action;

import java.util.Scanner;

import online.shixun.dao.UserDao;
import online.shixun.model.User;

public class VisitorBlock {

	static UserDao userDao = new UserDao();
	static Scanner sc = new Scanner(System.in);

	/**
	 * 
	 * @return
	 */
	public static int visitor() {
		boolean flag = true;
		int temp = -1;
		int roleCode = 0;// 返回角色編號
		while (flag) {
			System.out.println("欢迎登陆 请选择：");
			System.out.println("1.用户注册");
			System.out.println("2.登陆系统");
			System.out.println("0.退出");
			temp = sc.nextInt();
			if (temp != 1 && temp != 2 && temp != 0) {
				System.out.println("输入有误 请根据提示输入!");
			} else {
				flag = false;
			}
		}
		// 选择选项
		switch (temp) {
		case 1:
			visitorRegist();
		case 2:
			roleCode = login();
			flag = false;
			System.out.println("roleCode:"+roleCode);
			break;
		case 0:
			System.exit(0);
		default:
		}
		return roleCode;
	}

	private static int login() {
		while (true) {
			System.out.println("请输入用户名：");
			String username = sc.next();
			System.out.println("请输入密码：");
			String password = sc.next();
			if (username != null && password != null) {
				int temp = userDao.login(username, password);
				if (temp != -1) {
					System.out.println("登陆成功");
					return temp;
				} else {
					System.out.println("用户名或密码有误 请重新输入");
				}
			}
		}
	}

	public static void visitorRegist() {
		boolean flag = true;
		String username = null;
		String password1 = null;
		while (flag) {
			System.out.println("请输入用户名:");
			username = sc.next();
			System.out.println("请输入密码:");
			password1 = sc.next();
			System.out.println("请输入确认密码:");
			String password2 = sc.next();
			if (!password1.equals(password2)) {
				System.out.println("两次密码不匹配 请重新输入!");
			} else {
				flag = false;
			}
		}
		int judge = 0;
		if (username != null && password1 != null) {
			User user = new User(username, password1, "普通用户");
			judge = userDao.registUser(user);
		}
		if (judge == 1) {
			System.out.println("注册成功");
		} else if (judge == -1) {
			System.out.println("用户名已存在");
		}
		// 调用visitor主界面
		visitor();
	}
}
