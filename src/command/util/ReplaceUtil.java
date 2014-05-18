package command.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;

import vairy.io.FileReader;
import vairy.io.FileWriter;

public class ReplaceUtil {


	public static String readTemplateFile(String templateFPath, String returncode) {
		String rtn = null;
		boolean bProcOK = true;
		FileReader templateReader	= null;

		/* テンプレートファイルオープン */
		if(bProcOK){
			templateReader = openReader(templateFPath);
			bProcOK = null != templateReader;
		}

		/* テンプレートファイル読み込み */
		if(bProcOK){
			rtn = createTemplate(templateReader, returncode);
		}

		return rtn;
	}

	/**
	 * 指定したファイルパスでリーダーを開く
	 * @param strFpath ファイルパス
	 * @return ファイルリーダーオブジェクト<br>
	 * または、ファイルが存在しない場合はnull
	 */
	public static FileReader openReader(final String strFpath){
		FileReader readerRet = null;
		try {
			readerRet = new FileReader(strFpath,"MS932");
		} catch (FileNotFoundException e) {
			readerRet = null;
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			readerRet = null;
			e.printStackTrace();
		}

		return readerRet;
	}

	/**
	 * テンプレートファイルを文字列として読み出す。
	 * @param templateReader ファイルリーダー
	 * @param returncode 改行コード
	 * @return 読み込んだテンプレート文字列、読み込み失敗すればnull
	 */
	public static String createTemplate(final FileReader templateReader, String returncode){
		String			strRet;
		StringBuilder	strTemplateBuilder = new StringBuilder();
		String			strReadTemp;
		try {
			while(null != (strReadTemp = templateReader.ReadLine())){
				strTemplateBuilder.append(strReadTemp + getReturnCode(returncode));
			}
			strRet = strTemplateBuilder.toString();
		} catch (NullPointerException e) {
			strRet = null;
			e.printStackTrace();
		} catch (IOException e) {
			strRet = null;
			e.printStackTrace();
		}

		return strRet;
	}
	/**
	 * 改行コードを取得。
	 * @param returncode 改行コード
	 * @return 改行コード。デフォルトは\r\n
	 */
	public static final String getReturnCode(String returncode){

		if("\\r".equals(returncode))
		{
			return "\r";
		}
		else if("\\n".equals(returncode))
		{
			return "\n";
		}
		else
		{
			return "\r\n";
		}
	}

	public static List<String[]> readReplaceList(String replaceFPath) {
		boolean bProcOK = true;
		FileReader replaceReader	= null;
		List<String[]> replaceList = null;

		/* 置換ファイルオープン */
		if(bProcOK){
			replaceReader = openReader(replaceFPath);
			bProcOK = (null != replaceReader);
		}

		/* 置換リスト取得 */
		if(bProcOK){
			replaceList = createReplaceList(replaceReader);
			CsvConfig csvConfig = new CsvConfig();
			csvConfig.setQuoteDisabled(false);
			csvConfig.setEscapeDisabled(false);
			csvConfig.setEscape('"');
			try {
				replaceList = Csv.load(new File(replaceFPath),"MS932", csvConfig, new StringArrayListHandler());
			} catch (IOException e) {
				e.printStackTrace();
			}
			bProcOK = (null != replaceList);
		}

		return replaceList;
	}

	/**
	 * リプレイスファイルを分割文字列として読み出す。
	 * @param replaceReader ファイルリーダー
	 * @return 読み込んだ置換文字列リスト、読み込み失敗すればnull
	 */
	private static List<String[]> createReplaceList(FileReader replaceReader) {
		List<String[]>	listRet = new ArrayList<String[]>();
		String			strReadTemp;
		try {
			while(null != (strReadTemp = replaceReader.ReadLine())){
				listRet.add(strReadTemp.split(","));
			}
		} catch (NullPointerException e) {
			listRet = null;
			e.printStackTrace();
		} catch (IOException e) {
			listRet = null;
			e.printStackTrace();
		}
		return listRet;
	}

	/**
	 * 指定したファイルパスでライターを開く
	 * @param strFpath ファイルパス
	 * @param bIsAppend true:追加モードでファイルを開く。<br>
	 * false:新規モードでファイルを開く。
	 * @return ファイルライターオブジェクト<br>
	 * または、ファイルが存在しない場合はnull
	 */
	public static FileWriter openWriter(final String strFpath,final Boolean bIsAppend){
		FileWriter writerRet = null;
		try {
			writerRet = new FileWriter(strFpath,"MS932", bIsAppend);
		} catch (UnsupportedEncodingException e) {
			writerRet = null;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return writerRet;
	}
}
