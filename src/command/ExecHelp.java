package command;

import src.Resource;
import vairy.invoker.VCommand;

public class ExecHelp implements VCommand,Resource{

	@Override
	public Boolean execute(Object value) {
		En_Command[] values = En_Command.values();

		for(En_Command command : values){
			System.out.println(command.getCommand() + ":" + command.getHelp());
		}

		return true;
	}
}
