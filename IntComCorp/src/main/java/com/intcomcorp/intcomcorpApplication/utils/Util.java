package com.intcomcorp.intcomcorpApplication.utils;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate olddate = LocalDate.parse(parseDate(clock), formatter);
		Period period = Period.between(olddate, java.time.LocalDate.now());
	
		return period.getMonths() + "m " + period.getDays() + " d";

	}
}