/**
 * 
 */
package com.tedx.capetown.app.core.service.storage;

/**
 * @author AndrewPettey
 * 
 */
public interface Storage {

	public boolean save(String key, String value);

	public Object read(String key);

	public void remove(String key);

}
