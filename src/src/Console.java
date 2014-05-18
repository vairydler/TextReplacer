package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import vairy.debug.DebugMethod;
import vairy.debug.DebugWriter;
import vairy.invoker.VCommandParam;
import vairy.invoker.VInvoker;
import vairy.invoker.exeption.IllegalParamException;
import command.param.ParamReplace;

public class Console implements Resource{
	private final VInvoker invoker;
	public Console(final VInvoker invoker) {
		this.invoker = invoker;
	}
	public void execute(String[] args){
		Boolean bProcOk = true;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DebugWriter dbgWriter = new DebugWriter(this.getClass().getName(), "MS932");
		DebugMethod dbgMethod = new DebugMethod(dbgWriter);
		WatchDog watchDog = new WatchDog();
//		なんかjarをそのまま起動すると超高速で回り出すのでその対策。
//		10回ぐらい計測して、明らかに異常スピードだったら強制終了。
//		異常かどうかは割と雰囲気。

		Boolean bCmdLine = false;
		if(args.length > 0){
			bCmdLine = true;
		}

		while(true)
		{
			boolean notifyCycle = watchDog.notifyCycle();
			if(!notifyCycle){
				dbgMethod.chkTrue(false);
				System.exit(-1);
			}

			try {
				String[] splitparam = null;
				String command = null;
				/* 引数付き起動の場合は省略 */
				if(bCmdLine){
					splitparam = args;
				}else{
					dispTopText();

					/* 標準入出力からコマンドとパラメータを取得 */
					String strReadTemp = "";
					if(bProcOk)
					{
						try {
							strReadTemp = br.readLine();
						} catch (IOException e) {
							e.printStackTrace();
							bProcOk = dbgMethod.chkTrue(false);
						}
					}

					/* 文字数がある */
					if(bProcOk){
						splitparam = splitDQ(strReadTemp," ");
						bProcOk = dbgMethod.chkTrue(splitparam.length > 0);
					}
				}

				/* パラメータをオブジェクトに格納する。 */
				Object param = null;
				if(bProcOk){
					param = createCmdParam(splitparam);
					bProcOk = dbgMethod.chkTrue(null != param);
				}

				/* コマンドを呼び出す。 */
				if(bProcOk){
					command = splitparam[0];
					bProcOk = dbgMethod.chkTrue(invoker.invoke(command,param));
				}
			} catch (Exception e) {
				e.printStackTrace();
				bProcOk = dbgMethod.chkTrue(false);
			}

			if(!bProcOk){
				System.out.println("err");
			}
			/* 無限ループなので、ミスっても再開可能にする */
			bProcOk = true;

			/* コマンドライン起動の場合は1発抜け。 */
			if(bCmdLine){
				break;
			}
		}
	}

	private void dispTopText(){
		System.out.println("コマンドを入力してください。(/?でコマンド表示)");
	}

	/**
	 * パラメータをコマンドと対応するオブジェクトに格納する。
	 * @param split 入力文字列(コマンド、パラメータ)
	 * @return パラメータオブジェクトかnull
	 */
	private Object createCmdParam(final String[] split){
		Object oRet = null;
		En_Command command = En_Command.toCommand(split[0]);
		String[] copyOfRange = Arrays.copyOfRange(split, 1, split.length);

		try {
			oRet = command.getParamclass().newInstance();
			if(oRet instanceof VCommandParam ){
				((VCommandParam) oRet).setParam(copyOfRange);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalParamException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalClassFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return oRet;
	}

	/**
	 * ダブルクォートでくくられた文字列を分割しないスプリット。<br>
	 * ダブルクォートは出力対象としない。
	 * @param base 分割対象の文字列
	 * @param regex 分割指定文字列
	 * @return スプリットした文字列
	 */
	private String[] splitDQ(final String base,final String regex){
		ArrayList<String> listRet = new ArrayList<String>();

		String[] strSplit;
		StringBuilder mergeBuilder = null;

		strSplit = base.split(regex);
		for(String temp : strSplit){
			if(temp.length() > 0){
				Character cFirst = new Character(temp.charAt(0));
				/* 開始用のDQを見つけたら開始 */
				if(null == mergeBuilder && cFirst.equals('\"')){
					mergeBuilder = new StringBuilder(temp);
				}
				/* 開始のDQ発見済みの場合はマージしていく */
				else if(null != mergeBuilder){
					mergeBuilder.append(regex + temp);
				}
				else{
					listRet.add(temp);
				}

				/* 終了用のDQを見つけたら終了 */
				if(null != mergeBuilder){
					Character cLast = new Character(mergeBuilder.charAt(mergeBuilder.length()-1));
					if(cLast.equals('\"')){
						String strWriteTemp = mergeBuilder.substring(1, mergeBuilder.length()-1);
						listRet.add(strWriteTemp);
						mergeBuilder = null;
					}
				}
			}
		}

		return listRet.toArray(new String[0]);
	}
}
