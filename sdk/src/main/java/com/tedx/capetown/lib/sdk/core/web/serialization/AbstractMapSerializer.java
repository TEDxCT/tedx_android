/**
 * 
 */
package com.tedx.capetown.lib.sdk.core.web.serialization;

import java.util.Iterator;
import java.util.Map;

/**
 * @author richardpj
 * 
 */
public abstract class AbstractMapSerializer {

	private final String prefix;
	private final String keyValueSeparator;
	private final String itemSeparator;
	private final String suffix;

	public AbstractMapSerializer(String prefix, String keyValueSeparator, String itemSeparator, String suffix) {

		this.prefix = prefix;
		this.keyValueSeparator = keyValueSeparator;
		this.itemSeparator = itemSeparator;
		this.suffix = suffix;
	}

	public final String serialize(Map<String, String> map) {

		StringBuilder sb = new StringBuilder();
		if (prefix != null) {
			sb.append(prefix);
		}
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> item = iterator.next();
			sb.append(item.getKey());
			sb.append(keyValueSeparator);
			sb.append(item.getValue());
			if (iterator.hasNext()) {
				sb.append(itemSeparator);
			}
			else {
				if (suffix != null) {
					sb.append(suffix);
				}
			}
		}
		return sb.toString();
	}

}
