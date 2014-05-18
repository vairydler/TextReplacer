package command;

import vairy.invoker.VCommand;

public class ExecExit implements VCommand {
	@Override
	public Boolean execute(Object value) {
		System.exit(0);
		return true;
	}
}
