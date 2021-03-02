package br.com.southsystem.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Util class for Mapper Model operations.
 */
@Component("mapperUtil")
public final class MapperUtil {

	/**
	 * Model mapper.
	 */
	protected ModelMapper modelMapper;

	/**
	 * Default Constructor.
	 */
	public MapperUtil() {
		this.modelMapper = new ModelMapper();
	}

	/**
	 * Maps the source to destination class.
	 * 
	 * @param source    Source.
	 * @param destClass Destination class.
	 * @return Instance of destination class.
	 */
	public <S, D> D mapTo(S source, Class<D> destClass) {
		return this.modelMapper.map(source, destClass);
	}

	/**
	 * Converts the source list to a destination list mapping the source items to
	 * destination type items.
	 * 
	 * @param items     Source items.
	 * @param destClass Destination class.
	 * @return List of instances of destination type.
	 */
	public <S, D> List<D> toList(List<S> items, Class<D> destClass) {
		List<D> dests = new LinkedList<>();
		if (items != null) {
			items.forEach(item -> {
				dests.add(mapTo(item, destClass));
			});
		}
		return dests;
	}

}
