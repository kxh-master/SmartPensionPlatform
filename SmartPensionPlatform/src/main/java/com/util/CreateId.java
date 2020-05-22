package com.util;

import java.util.Date;
import java.util.Random;

/**
 * 生成uuid
 * @author kxh
 *
 */
public class CreateId {
	// private static CreateId instance = null;
	public static int count = 0;

	public static synchronized String getid() throws Exception {
		if (CreateId.count > 998) {
			CreateId.count = 5;
		}
		CreateId.count++;
		// Thread.sleep(5);

		if (CreateId.count == 4 || CreateId.count == 44 || CreateId.count == 444) {
			CreateId.count++;
		}

		String counts = "";
		if ((CreateId.count + "").length() == 1) {
			counts = "00" + CreateId.count;
		} else if ((CreateId.count + "").length() == 2) {
			counts = "0" + CreateId.count;
		} else {
			counts = "" + CreateId.count;
		}

		return new Date().getTime() + counts;
	}

	public static String getNumber3FromRandom() {
		Random r = new Random();
		int xx = r.nextInt(100);
		while (xx < 10) {
			xx = r.nextInt(100);
		}
		return String.valueOf(xx);
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 20; i++) {
			String id = CreateId.getid();
			System.out.println(id);
		}

	}

}
