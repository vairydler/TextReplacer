package src;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WatchDog {
	Integer iLoopCnt = 10;
	Calendar calStartTime = new GregorianCalendar();
	public boolean notifyCycle(){
		boolean rtn = true;
		if(0 == iLoopCnt){
			Long l = new GregorianCalendar().getTimeInMillis() - calStartTime.getTimeInMillis();

//			1秒きってたらさすがにおかしい。こっそり終了。
			if(1000 > l)
			{
				rtn = false;
			}

			iLoopCnt = 10;
			calStartTime = new GregorianCalendar();
		}
		iLoopCnt--;

		return rtn;
	}
}
