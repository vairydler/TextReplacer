package src;

import vairy.invoker.VCommand;
import command.ExecExit;
import command.ExecHelp;
import command.ExecReplace;
import command.ExecReplaceMap;
import command.ExecVersion;
import command.param.ParamReplace;
import command.param.ParamReplaceMap;

public interface Resource {
	public final String VERSION = "2.0.0";
	public enum En_Command{
		HELP("/?",		ExecHelp.class,		Object.class,		"コマンド一覧を表示します。"),
		REPLACE("/rep", ExecReplace.class,	ParamReplace.class,	"置換の実行をします。\n" +
				"　第一引数：テンプレートファイルパス(String)\n" +
				"　第二引数：リプレイスファイルパス(String)\n" +
				"　第三引数：セーブファイルパス(String)\n" +
				"　第四引数：連番ファイル保存(Boolean)\n" +
				"　第五引数：強制上書き(Boolean)\n" +
				"　第六引数：改行コード(String)\\r|\\n|\\r\\n\n" +
				"　第七引数：先頭読み飛ばし行数(Integer)\n" +
				"　第八引数：末尾読み飛ばし行数(Integer)\n"
				),
		REPLACEMAP("/repmap", ExecReplaceMap.class,	ParamReplaceMap.class,	"置換の実行をします。\n" +
				"　第一引数：テンプレートファイルパス(String)\n" +
				"　第二引数：リプレイスファイルパス(String)\n" +
				"　第三引数：セーブファイルパス(String)\n" +
				"　第四引数：連番ファイル保存(Boolean)\n" +
				"　第五引数：強制上書き(Boolean)\n" +
				"　第六引数：改行コード(String)\\r|\\n|\\r\\n\n" +
				"　第七引数：先頭読み飛ばし行数(Integer)\n" +
				"　第八引数：末尾読み飛ばし行数(Integer)\n" +
				"　第九引数：タグ名記載行番号(Integer)\n"
				),
		VERSION("/v",		ExecVersion.class,		Object.class,		"バージョンを表示します。"),
		EXIT("/q",		ExecExit.class,		Object.class,		"終了します。")

		;
		private final String	command;
		private final Class<?>	commandclass;
		private final Class<?>	paramclass;
		private final String	help;
		private En_Command(final String command,final Class<? extends VCommand> commandclass,final Class<?> paramclass, final String help){
			this.command		= command;
			this.commandclass	= commandclass;
			this.paramclass		= paramclass;
			this.help			= help;
		}

		public final String getCommand() {
			return command;
		}
		public final Class<?> getCommandclass() {
			return commandclass;
		}
		public final Class<?> getParamclass() {
			return paramclass;
		}
		public final String getHelp() {
			return help;
		}
		public static En_Command toCommand(String value){
			En_Command[] values = values();
			for (En_Command en_Command : values) {
				if(en_Command.getCommand().equals(value)){
					return en_Command;
				}
			}
			return null;
		}
	};

}
