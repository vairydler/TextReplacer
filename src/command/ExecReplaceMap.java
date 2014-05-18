package command;

import java.util.List;

import textconverter.IteratableConverter;
import textconverter.MappingConverter;
import vairy.debug.DebugMethod;
import vairy.debug.DebugWriter;
import vairy.io.FileWriter;
import vairy.tagreplacer.TagReplacer;
import command.param.ParamReplace;
import command.param.ParamReplaceMap;
import command.util.ReplaceFileManager;
import command.util.ReplaceUtil;

public class ExecReplaceMap extends ExecReplace{
	private DebugWriter		dbgwriter;
	private DebugMethod		dbgmethod;
	private ParamReplaceMap 	param;

	public ExecReplaceMap() {
		this.dbgwriter = new DebugWriter(this.getClass().getName(), "MS932");
		this.dbgmethod = new DebugMethod(dbgwriter);
	}

	@Override
	public Boolean execute(Object value) {
		Boolean bProcOK = true;
		FileWriter replaceWriter	= null;
		ReplaceFileManager replaceFileManager = null;

		try {
			/* パラメータ型チェック */
			if(bProcOK){
				bProcOK = dbgmethod.chkTrue(ParamReplaceMap.class == value.getClass());
			}

			/* テンプレートの取得 */
			String strTemplate = null;
			if (bProcOK) {
				this.param = (ParamReplaceMap)value;
				strTemplate = ReplaceUtil.readTemplateFile(this.param.getTemplateFPath(),this.param.getReturncode());
				bProcOK = dbgmethod.chkTrue(null != strTemplate);
			}

			/* 置換リストの取得 */
			List<String[]> replaceList = null;
			if (bProcOK) {
				replaceList = ReplaceUtil.readReplaceList(this.param.getReplaceFPath());
				bProcOK = dbgmethod.chkTrue(null != replaceList);
			}

			/* ヘッダ行の作成 */
			String[] headerline = null;
			if(bProcOK){
				Integer idx = param.getTagline()-1;
				bProcOK &= dbgmethod.chkTrue(((0 <= idx) & (idx < replaceList.size())));

				if (bProcOK) {
					headerline = replaceList.get(param.getTagline()-1);
				}
			}

			/* サブリストの作成 */
			if(bProcOK){
				replaceList = replaceList.subList(param.getSkipheader(), replaceList.size() - param.getSkipfooter());
			}

			/* 置換実行 */
			if(bProcOK){
				String	strReplace = null;

				/* ファイルマネージャーの作成 */
				replaceFileManager = new ReplaceFileManager(
						this.param.getSaveFPath(),
						this.param.getbIsChainFiles(),
						this.param.getbIsOverWrite(),
						replaceList.size());

				for(String[] line : replaceList){
					if(bProcOK){
						replaceWriter = replaceFileManager.getReader(1);
						bProcOK = dbgmethod.chkTrue(null != replaceWriter);
					}

					/* ファイルを変換、書き込み */
					if(bProcOK){
						MappingConverter mappingConverter = new MappingConverter(headerline,line);
						TagReplacer			tagReplacer		= new TagReplacer(strTemplate, mappingConverter);

						strReplace	= tagReplacer.doReplace();

						bProcOK = dbgmethod.chkTrue(null != strReplace);
					}

					if (bProcOK) {
						bProcOK = dbgmethod.chkTrue(replaceWriter.writeLine(strReplace));
					}

					/* 途中で異常があったら抜ける */
					if(!bProcOK){
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != replaceFileManager){
					replaceFileManager.finalize();
				}
			}catch (Throwable e1) {
				e1.printStackTrace();
			}
		}

		return bProcOK;
	}
}
