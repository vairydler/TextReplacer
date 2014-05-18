package command.util;

import java.io.File;
import java.io.IOException;

import vairy.io.FileWriter;

public class ReplaceFileManager {
	private final Boolean chainfiles;
	private final String savefpath;
	private final Boolean overwrite;
	private final Integer size;
	private String strFileNameFormat = null;
	private Integer iLoopCnt = 0;
	private FileWriter replaceWriter = null;

	public ReplaceFileManager(String saveFPath,
			Boolean ChainFiles,
			Boolean OverWrite,
			int Size) {
		chainfiles = ChainFiles;
		savefpath = saveFPath;
		overwrite = OverWrite;
		this.size = Size;

		strFileNameFormat = savefpath + "%0" + size + "d";
	}

	public FileWriter getReader(Integer chainnum) throws IOException {
		Boolean bProcOK = true;
		String		strWriteFilePath	= null;

		/* 連番ファイルを開く用に、古いファイルは勝手に閉じる */
		if(chainfiles && null != replaceWriter){
			replaceWriter.close();
			replaceWriter = null;
		}

		/* ファイル名を連番にする。 */
		if (bProcOK) {
			if(chainfiles){
				/* 毎回 */
				strWriteFilePath = String.format(strFileNameFormat, iLoopCnt += chainnum);
				/* ファイルのあるなしを確認 */
				if(!overwrite){
					bProcOK = !(new File(strWriteFilePath).exists());
				}
			}
			else{
				/* 初回のみ */
				if(null == replaceWriter){
					/* ファイルのあるなしを確認 */
					strWriteFilePath = savefpath;
					File writeFile = new File(strWriteFilePath);
					if(writeFile.exists()){
						if(overwrite){
							bProcOK = (writeFile.delete());
						}else{
							bProcOK = false;
						}
					}
				}
			}
		}

		/* 保存ファイルを開く */
		if(bProcOK){
			/* 連続書き込み用のときは新しいのを開かない */
			if(null == replaceWriter){
				/* 連番でファイルを保存することと、ファイルを追記モードで開くことは連動している。 */
				/* 連番で保存する時は新規、 連番でない場合は追記になる。*/
				replaceWriter = ReplaceUtil.openWriter(strWriteFilePath, !chainfiles);
				bProcOK = (null != replaceWriter);
			}
		}

		return replaceWriter;
	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
		if(null != replaceWriter){
			replaceWriter.close();
		}
	}
}
