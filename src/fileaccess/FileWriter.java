package fileaccess;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriter {

	private FileOutputStream fosWrite;
	private OutputStreamWriter oswWrite;
	private BufferedWriter bwWrite;

	private String WriteFileName;

	public FileWriter(){
	}
	public FileWriter(final String order_FileName_S) throws IOException
	{
		setWriteFile(order_FileName_S, false);
	}
	public FileWriter(final String order_FileName_S, final boolean order_append_b) throws IOException
	{
		setWriteFile(order_FileName_S, order_append_b);
	}
	/**
	 * 書き込むファイルを指定し、開く。
	 * 既に別のファイルが開かれている場合は、閉じてから開く。
	 * @param FileName 書き込み対象のファイル名、ファイルパス。
	 * @param append trueの場合、上書きでなく、追記。
	 * @throws IOException
	 */
	public void setWriteFile(String FileName,boolean append) throws IOException
	{
		closeWrite();
		this.WriteFileName = FileName;
		openWrite(append);
	}

	/**
	 * 書き込むファイルを開く。
	 * @param append trueの場合、上書きでなく、追記。
	 * @throws FileNotFoundException
	 */
	private void openWrite(boolean append) throws FileNotFoundException
	{
		fosWrite = new FileOutputStream(WriteFileName,append);
		oswWrite = new OutputStreamWriter(fosWrite);
		bwWrite = new BufferedWriter(oswWrite);
	}

	/**
	 * 現在書き込み中のファイルを閉じる。
	 * @throws IOException
	 */
	public void closeWrite() throws IOException
	{
		if(fosWrite != null)fosWrite.close();
		if(oswWrite != null)oswWrite.close();
		if(bwWrite != null)bwWrite.close();
	}

	/**
	 * 対象ファイルに文字列を書き込む。
	 * @param order 書き込む文字列
	 * @return true:書き込み成功<br>
	 * false:書き込み失敗
	 */
	public boolean writeLine(String order)
	{
		try {
			bwWrite.write(order);
			bwWrite.flush();
			return true;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			return false;
		}
	}
}