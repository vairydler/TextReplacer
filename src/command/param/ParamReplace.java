package command.param;

import vairy.invoker.annotation.VCmdParamAnnotation;

public class ParamReplace extends ParamReplaceCommon{
	/**
	 * 最初の読み飛ばし行数
	 */
	private Integer skipheader = 0;
	/**
	 * 最後の読み飛ばし行数
	 */
	private Integer skipfooter = 0;

	public Integer getSkipheader() {
		return skipheader;
	}
	@VCmdParamAnnotation(num=6)
	public void setSkipheader(Integer skipheader) {
		this.skipheader = skipheader;
	}
	public Integer getSkipfooter() {
		return skipfooter;
	}
	@VCmdParamAnnotation(num=7)
	public void setSkipfooter(Integer skipfooter) {
		this.skipfooter = skipfooter;
	}
}
