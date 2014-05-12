package com.tedx.capetown.app.core.converter;

/**
 * Created by andrewpettey on 2014/05/11.
 */
public interface Converter<Tsource, Tdest> {
   Tdest convert(Tsource source);
}
