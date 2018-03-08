package online.shixun.common;

import org.hibernate.dialect.MySQL5Dialect;

public class MYSQL5MyISAMUTF8Dialect extends MySQL5Dialect {

	@Override
	public String getTableTypeString() {
		return "ENGINE=MyISAM CHARSET=utf8";
	}
	
}
