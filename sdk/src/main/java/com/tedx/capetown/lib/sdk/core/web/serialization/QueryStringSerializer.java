/**
 * 
 */
package com.tedx.capetown.lib.sdk.core.web.serialization;


/**
 * @author andrewpettey
 * 
 */
public class QueryStringSerializer extends AbstractMapSerializer {

	public QueryStringSerializer() {

		super("?", "=", "&", null);
	}
}
