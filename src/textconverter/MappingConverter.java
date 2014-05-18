package textconverter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import vairy.tagreplacer.TagConverter;

public class MappingConverter implements TagConverter {
	private final Map<String, String> replacelist = new HashMap<String, String>();

	public MappingConverter(String[] header, String[] value) {
		for (int i = 0; i < header.length; i++) {
			String key = "%"+header[i]+"%";

			replacelist.put(key, value[i]);
		}
	}

	@Override
	public String getFirstMark() {
		return "%";
	}

	@Override
	public String getLastMark() {
		return "%";
	}

	@Override
	public String getReplaceString(String tag) {
		return replacelist.get(tag);
	}

}
