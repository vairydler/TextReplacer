package fileaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public abstract class FileAccessorImpl<T> implements FileAccessor<T>{
	protected final String filename;
	protected final Class<T> c;

	public FileAccessorImpl(final String filename,final Class<T> c) {
		this.filename = filename;
		this.c = c;
	}
	public T readFile(){
		T rtn = null;

		try {
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis, "UTF-16LE");
			rtn = JSON.decode(isr,c);
			isr.close();
			fis.close();
		} catch (JSONException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

//		値のチェックと異常値の初期化
		rtn = validate(rtn);

		return rtn;

	}
	public Boolean writeFile(T src) {
		boolean rtn = false;
		try {
			String writestr = new String(new byte[]{-1,-2},"UTF-16LE");
			writestr = writestr.concat(JSON.encode(src,true));
			if(writestr != null){
				FileOutputStream fos = new FileOutputStream(filename);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-16LE");

				osw.write(writestr);
				osw.close();
				fos.close();
				rtn = true;
			}
		} catch (JSONException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return rtn;
	}

	static public void dispCharSets(){
		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		Set<String> keySet = availableCharsets.keySet();
		for(String key : keySet){
			System.out.println(key);
		}
	}

	abstract protected T validate(T src);
}
