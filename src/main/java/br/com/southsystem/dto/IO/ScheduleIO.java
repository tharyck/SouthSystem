package br.com.southsystem.dto.IO;

import br.com.southsystem.dto.input.ScheduleInput;
import br.com.southsystem.model.Schedule;
import br.com.southsystem.model.ScheduleStatus;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Mapper to Schedule.
 */
@Component("scheduleIO")
public class ScheduleIO {
	private ModelMapper modelMapper;

	final Converter<ScheduleInput, Schedule> scheduleConverter = new Converter<ScheduleInput, Schedule>() {
		@Override
		public Schedule convert(MappingContext<ScheduleInput, Schedule> context) {
			ScheduleInput scheduleInput = context.getSource();
			// @formatter:off
			return new Schedule(
					scheduleInput.getName(),
					scheduleInput.getDescription(),
					ScheduleStatus.CREATED);
			// @formatter:on
		}
	};

	public ScheduleIO() {
		modelMapper = new ModelMapper();
		modelMapper.addConverter(scheduleConverter);
	}

	public Schedule mapTo(ScheduleInput scheduleInput) {
		return this.modelMapper.map(scheduleInput, Schedule.class);
	}
}