package edu.nju.desserthouse.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.nju.desserthouse.model.Account;
import edu.nju.desserthouse.model.User;

public class CheckUser {
	User user;
	Account account;

	public CheckUser(User user, Account account) {
		this.user = user;
		this.account = account;
	}

	public User CheckUser() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date user_cardtime = sdf.parse(user.getCardtime());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		Date now_time = sdf.parse(now);

		int days = daysBetween(user_cardtime, now_time);

		String status = user.getStatus();

		Double payment = account.getPayment();

		if (days >= 365 && status.equals("已激活") && payment < 0) {
			user.setStatus("已暂停");
			user.setCardtime(now);
			return user;
		}
		if (days >= 365 && status.equals("已暂停") && payment < 0) {
			user.setStatus("已停止");
			user.setCardtime(now);
			return user;
		}
		return user;
	}

	public int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}
