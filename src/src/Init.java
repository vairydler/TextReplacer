package src;

import java.util.Calendar;
import java.util.GregorianCalendar;

import src.Resource.En_Command;
import vairy.debug.DebugMethod;
import vairy.debug.DebugWriter;
import vairy.debug.DebugMethod.EN_LogingType;
import vairy.invoker.VCommand;
import vairy.invoker.VInvoker;
import command.ExecHelp;

public class Init {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DebugMethod.setEloging(EN_LogingType.ONLYFALSE);
		DebugWriter.setDebugOn(true);
		DebugWriter.setDebugfolder("debuglog/");
		DebugWriter.setDebugFileHeader(createFileDate());

		VInvoker invoker = new VInvoker();
		En_Command[] values = En_Command.values();
		for(En_Command value : values){
			 invoker.regCommand(value.getCommand(),createCommand(value));
		}
		
		Console cons = new Console(invoker);
		
		cons.execute(args);
	}
	
	private static VCommand createCommand(final En_Command command){
		VCommand commandRet = new ExecHelp();
		try {
			commandRet = (VCommand) command.getCommandclass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return commandRet;
	}
	
	private static String createFileDate(){
		String			strRet;
		StringBuilder 	strBuilder = new StringBuilder();
		GregorianCalendar cal = new GregorianCalendar();
		
		strBuilder.append(String.format("%04d", cal.get(Calendar.YEAR)));
		strBuilder.append(String.format("%02d", cal.get(Calendar.MONTH) + 1));
		strBuilder.append(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
		strBuilder.append(String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)));
		strBuilder.append(String.format("%02d", cal.get(Calendar.MINUTE)));
		
		strRet = strBuilder.toString();
		return strRet;
	}
}
