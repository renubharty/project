package com.intcomcorp.intcomcorpApplication.utils;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.context.SecurityContextHolder;

public class Util {

	public static boolean hasRole(String roleName) {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}

	public static String currentUserName(Principal principal) {

		return principal.getName();
	}

	public static String parseDate(String timestamp) {
		Date date = new Date(Long.parseLong(timestamp) * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("Etc/UTC"));
		return sdf.format(date);
	}

	public static String calculatePeriod(String clock) {
		
	Date endDate = new Date(Long.parseLong(clock)*1000);
    Date startDate = new Date();
    long diffInMilliSec = startDate.getTime() - endDate.getTime() ;
    final   long MINUTES = TimeUnit.MILLISECONDS.toMinutes(diffInMilliSec) % 60;
    final   long HOURS = TimeUnit.MILLISECONDS.toHours(diffInMilliSec) % 24;
    final   long TOTALDAYS = TimeUnit.MILLISECONDS.toDays(diffInMilliSec) % 365;
    final   long MONTHS = TOTALDAYS/30;
    final   long DAYS = TOTALDAYS%30;
	 String formattedDate = MONTHS +"m "+ DAYS +"d " + HOURS+"h ";
		return formattedDate;

	}
}