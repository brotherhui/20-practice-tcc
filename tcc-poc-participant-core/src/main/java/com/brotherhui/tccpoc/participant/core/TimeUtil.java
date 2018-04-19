package com.brotherhui.tccpoc.participant.core;

import java.sql.Timestamp;

public class TimeUtil {
	
	/**
	 * Get expired time which is 5 minutes after current time
	 * @return
	 */
	public static Timestamp getExpiredTime() {
		long currentTime = System.currentTimeMillis() ;
		currentTime +=5*60*1000;
		Timestamp newTime = new Timestamp(currentTime);
		return newTime;
	}

}
