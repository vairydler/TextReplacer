package command.param;

import vairy.invoker.annotation.VCmdParamAnnotation;

public class ParamReplaceMap extends ParamReplace {
	/**
	 * タグ名称の記載された行(1オリジン)
	 */
	private Integer tagline = 1;

	public Integer getTagline() {
		return tagline;
	}

	@VCmdParamAnnotation(num=8)
	public void setTagline(Integer tagline) {
		this.tagline = tagline;
	}

}
