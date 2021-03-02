package br.com.southsystem.appcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component("appControllerBase")
public class AppControllerBase {

	private ModelMapper modelMapper;

	public AppControllerBase() {
		this.modelMapper = new ModelMapper();
	}

	public <S, D> D mapTo(S source, Class<D> dest) {
		return modelMapper.map(source, dest);
	}

	public <S, D> List<D> toList(List<S> source, Type dest) {
		return modelMapper.map(source, dest);
	}

}
