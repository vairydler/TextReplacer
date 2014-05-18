package fileaccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class FileReader {
	private FileInputStream fisRead;
	private InputStreamReader isrRead;
	private BufferedReader brRead;
	private boolean readR = false;
	private boolean readN = false;
	private String charset = null;
	private long pos = 0;

	/**
	 * 読み込むファイル
	 */
	private File ReadFile;

	/**
	 * 特に動作しない。
	 */
	public FileReader(){};
	/**
	 * 読み込むファイルを指定し、開く。
	 * @param FileName 読み込むファイルの名前
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public FileReader(String FileName) throws FileNotFoundException, UnsupportedEncodingException
	{
		this(new File(FileName));
	}
	/**
	 * 読み込むファイルを指定し、開く。
	 * @param file 読み込むファイル
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public FileReader(final File file) throws FileNotFoundException, UnsupportedEncodingException{
		this(file,(String)null);
	}
	/**
	 * 読み込むファイルを指定し、開く。
	 * @param FileName 読み込むファイルの名前
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 *
	 */
	public FileReader(final String FileName,final String charset) throws FileNotFoundException, UnsupportedEncodingException
	{
		this(new File(FileName),charset);
	}
	/**
	 * 読み込むファイルを指定し、開く。
	 * @param file 読み込むファイル
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public FileReader(final File file,final String charset) throws FileNotFoundException, UnsupportedEncodingException
	{
		setReadFile(file,charset);
	}
	
	/**
	 * 読み込むファイルを指定し、開く。
	 * 既に開かれている場合は、閉じてから開く。
	 * @param FileName 読み込むファイルの名前
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public void setReadFile(final String FileName) throws FileNotFoundException, UnsupportedEncodingException
	{
		setReadFile(new File(FileName),null);
	}
	/**
	 * 読み込むファイルを指定し、開く。
	 * 既に開かれている場合は、閉じてから開く。
	 * @param FileName 読み込むファイルの名前
	 * @param charsetname 文字セットの名前
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public void setReadFile(String FileName,String charsetname) throws FileNotFoundException, UnsupportedEncodingException 
	{
		setReadFile(new File(FileName),charsetname);
	}
	/**
	 * 読み込むファイルを指定し、開く。
	 * 既に開かれている場合は、閉じてから開く。
	 * @param file 
	 * @param charsetname 文字セットの名前
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void setReadFile(final File file,String charsetname) throws FileNotFoundException,UnsupportedEncodingException
	{
		close();
		/*バッファ等の設定*/
		this.ReadFile = file;
		if(charsetname == null){
			open();
		}else{
			open(charsetname);
		}
	}

	/**
	 * リーダーを開く。setReadFileから呼び出される。
	 * @throws FileNotFoundException
	 */
	private void open() throws FileNotFoundException
	{
		fisRead= new FileInputStream(this.ReadFile);
		isrRead = new InputStreamReader(fisRead);
		brRead = new BufferedReader(isrRead);
	}
	/**
	 * リーダーを開く。setReadFileから呼び出される。
	 * @param charsetname 文字セットの名前
	 * @throws FileNotFoundException
	 */
	private void open(String charsetname) throws FileNotFoundException, UnsupportedEncodingException{
		fisRead= new FileInputStream(this.ReadFile);
		isrRead = new InputStreamReader(fisRead,charsetname);
		brRead = new BufferedReader(isrRead);
		this.charset = charsetname;
	}
	/**
	 * リーダーを閉じる。
	 * @throws IOException
	 */
	public void close()
	{
		try {
			if(brRead != null)brRead.close();
			if(isrRead != null)isrRead.close();
			if(fisRead != null)fisRead.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * ファイルから一行データを読み込み、ポインタを次の行へ移動させる。
	 * ファイルが終わりに達している場合、nullを返す。
	 * @return 読み込んだ行データ,最終行ならばnull
	 * @throws IOException
	 */
	public final String ReadLine() throws IOException,NullPointerException
	{
//		return brRead.readLine();
//		return ReadLinePointer();
 		return ReadLineRAF();
	}
	private final String ReadLineRAF() throws IOException{
		String rtn;
		rtn = brRead.readLine();
		if(rtn == null){
//			ファイルの終わりまで読んだのでファイルサイズを設定
//			ramfile.seek(ramfile.getChannel().size());
		}else{
////			多分読み込んだサイズを加算設定して、次の改行まで読みこめば割とリアルな数値がでるはず。
////			ファイルのバイトサイズを設定
//			int readsize = rtn.getBytes().length;
//			ramfile.skipBytes(readsize);
////			ファイルの終わりか改行をみつけるまで続ける。
//			int readbyte;
//			while((readbyte = ramfile.read()) != -1){
//				if(readbyte == '\n'){
//					break;
//				}
//			}
		}
//		ramfile.skipBytes(rtn.length());
		if(rtn != null){
//			読み込んだ文字数と\n\rの2バイトを加算
			pos += rtn.length()+2;
		}
		return rtn;
	}
	
	@SuppressWarnings("unused")
	private final String ReadLinePointer() throws IOException,NullPointerException{
 		Integer readdata;
		Byte b;
		LinkedList<Byte> bList = new LinkedList<Byte>();
		while((readdata = fisRead.read()) != -1){
			b = readdata.byteValue();
			
			if(b == '\n' && readR){
//				リターンコードの次の改行は読み飛ばす。
//				リターンコードの次じゃない改行は普通に読む。
				readN = true;
				readR = false;
			}
			else if(b == '\r'){
				readR = true;
//				改行が無効行にならないように隠し文字追加
//				bList.add((byte) 0);
//				bList.add((byte) 0);
				break;
			}
			
			else{
//				改行コードかリターンコードの次の0文字文字は読み飛ばす。
				if((readR || readN) && b == 0){
				}
				else{
					bList.add(b);
				}
//				改行コードは一回読み飛ばしたらOK
//				リターンコードを戻すと次の改行が読み飛ばせなくなる
				if(readN){
					readN = false;
				}
			}
		}

//		UTF-16のヘッダがついてないと変換失敗するのでつける。
//		ファイルの頭は普通についてるので、わざわざつけないようにする。
		addHeader(bList);
		
//		最後まで読み込んで、UTF-16用のヘッダもつけたら、それをバイト配列に変換してString化する。
		return convertBtoStr(bList);
	}
	
	/**
	 * 文字列のヘッダが必要な型があるので、ヘッダをつける。
	 * @param bArray
	 */
	private void addHeader(LinkedList<Byte> bArray){
		if(charset != null){
			if(charset.compareTo("UTF-16") == 0){
				if(bArray.size() > 2 ){
					Byte b0 = bArray.get(0);
					Byte b1 = bArray.get(1);
					Byte x0 = (byte) 0xFF;
					Byte x1 = (byte) 0xFE;
					if(b0 == x0 && b1 == x1){
					}else{
						bArray.addFirst((byte) 0xFE);
						bArray.addFirst((byte) 0xFF);
					}
				}else if(bArray.isEmpty() == false){
					bArray.addFirst((byte) 0xFE);
					bArray.addFirst((byte) 0xFF);
				}
			}
		}
	}
	/**
	 * バイトのリストを文字列化する。
	 * @param bArray バイトのリスト
	 * @return 文字列。諸事情で作成できない場合はnull
	 * @throws UnsupportedEncodingException 
	 */
	private final String convertBtoStr(LinkedList<Byte> bArray) throws UnsupportedEncodingException{
		String rtnStr = null;
		if(bArray.isEmpty() != true){
			byte[] rtnBArray = new byte[bArray.size()];
			
			for(int i = 0; i < rtnBArray.length;i++){
				rtnBArray[i] = bArray.get(i);
			}
			
			if(charset == null){
				rtnStr = new String(rtnBArray);
			}else{
				rtnStr = new String(rtnBArray,charset);
			}
		}
		return rtnStr;
	}

	/**
	 * ファイルの読み込み位置を指定位置に設定する。
	 * @param pos ファイルの読み込み位置
	 * @throws IOException
	 */
	public final void setFilePos(long pos) throws IOException{
		close();
		if(charset == null){
			open();
		}else{
			open(charset);
		}
//		brRead.read();
		if(pos >= 0){
//			fisRead.getChannel().position(pos);
//			ramfile.seek(pos);
			this.pos = pos;
			brRead.skip(pos);
			brRead.ready();
		}
	}
	/**
	 * ファイルの次の読み込み位置を取得する。
	 * @return ファイルの次の読み込み位置
	 * @throws IOException
	 */
	public final long getFilePos() throws IOException{
//		return fisRead.getChannel().position();
//		return ramfile.getFilePointer();
		return pos;
	}
	
	public final File getFile(){
		return ReadFile;
	}
}