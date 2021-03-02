package br.com.southsystem.dto.IO;

import br.com.southsystem.dto.input.AssociatedInput;
import br.com.southsystem.model.Associated;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Mapper to Associated.
 */
@Component("associadoIO")
public class AssociatedIO {

    private ModelMapper modelMapper;

    final Converter<AssociatedInput, Associated> associadoConverter = new Converter<AssociatedInput, Associated>() {
        @Override
        public Associated convert(MappingContext<AssociatedInput, Associated> context) {
            AssociatedInput associatedInput = context.getSource();
            // @formatter:off
            return new Associated(
                    associatedInput.getName(),
                    associatedInput.getCpf(),
                    associatedInput.getPhone(),
                    associatedInput.getAddress());
            // @formatter:on
        }
    };

    public AssociatedIO() {
        modelMapper = new ModelMapper();
        modelMapper.addConverter(associadoConverter);
    }

    public Associated mapTo(AssociatedInput associatedInput) {
        return this.modelMapper.map(associatedInput, Associated.class);
    }
}
