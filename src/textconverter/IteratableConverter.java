package textconverter;

import java.util.Arrays;
import java.util.List;

import vairy.tagreplacer.TagConverter;

public class IteratableConverter implements TagConverter{
	private final String tag = "%rep%";
	private final List<String> replacelist;
	private Integer listcnt;

	public IteratableConverter(String[] replacelist) {
		this(Arrays.asList(replacelist));
	}

	public IteratableConverter(List<String> replacelist) {
		this.replacelist = replacelist;
		listcnt = 0;
	}

	public void cntreset(){
		listcnt = 0;
	}
	public String getFirstMark() {
		return "%";
	}

	public String getLastMark() {
		return "%";
	}

	public String getReplaceString(String tag) {
		String rtn = null;

		if(this.tag.equals(tag)){
			if( listcnt < replacelist.size() ){
				rtn = replacelist.get(listcnt);
				listcnt++;
			}
		}

		return rtn;
	}
}
