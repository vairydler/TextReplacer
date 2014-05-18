package command;

import src.Resource;
import vairy.invoker.VCommand;

public class ExecVersion implements VCommand,Resource {
	@Override
	public Boolean execute(Object value) {
		System.out.println(VERSION);
		return true;
	}
}
