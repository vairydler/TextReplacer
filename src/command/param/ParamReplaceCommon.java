package command.param;

import vairy.invoker.VCommandParam;
import vairy.invoker.annotation.VCmdParamAnnotation;

public abstract class ParamReplaceCommon extends VCommandParam {

	/**
	 * テンプレートファイルパス
	 */
	private String templateFPath = "";
	/**
	 * リプレイスファイルパス
	 */
	private String replaceFPath = "";
	/**
	 * 保存ファイルパス
	 */
	private String saveFPath = "";
	/**
	 * 置換結果を連番ファイルで別名保存する。
	 */
	private Boolean bIsChainFiles = false;
	/**
	 * 出力ファイル名がある場合は上書きする。
	 */
	private Boolean bIsOverWrite = false;
	/**
	 * 改行コード
	 */
	private String returncode = "\\r\\n";

	public ParamReplaceCommon() {
		super();
	}

	public final String getTemplateFPath() {
		return templateFPath;
	}

	@VCmdParamAnnotation(num = 0)
	public final void setTemplateFPath(String templateFPath) {
		this.templateFPath = templateFPath;
	}

	public final String getReplaceFPath() {
		return replaceFPath;
	}

	@VCmdParamAnnotation(num = 1)
	public final void setReplaceFPath(String replaceFPath) {
		this.replaceFPath = replaceFPath;
	}

	public final String getSaveFPath() {
		return saveFPath;
	}

	@VCmdParamAnnotation(num = 2)
	public final void setSaveFPath(String saveFPath) {
		this.saveFPath = saveFPath;
	}

	public final Boolean getbIsChainFiles() {
		return bIsChainFiles;
	}

	@VCmdParamAnnotation(num = 3)
	public final void setbIsChainFiles(Boolean bIsChainFiles) {
		this.bIsChainFiles = bIsChainFiles;
	}

	public final Boolean getbIsOverWrite() {
		return bIsOverWrite;
	}

	@VCmdParamAnnotation(num = 4)
	public final void setbIsOverWrite(Boolean bIsOverWrite) {
		this.bIsOverWrite = bIsOverWrite;
	}

	public final String getReturncode() {
		return returncode;
	}

	@VCmdParamAnnotation(num = 5)
	public final void setReturncode(String returncode) {
		this.returncode = returncode;
	}

}