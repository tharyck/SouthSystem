package br.com.southsystem.dto.IO;

import br.com.southsystem.dto.input.VotingSessionInput;
import br.com.southsystem.model.Schedule;
import br.com.southsystem.model.VotingSession;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * Mapper to Voting Session.
 */
@Component("votingSessionIO")
public class VotingSessionIO {

    private ModelMapper modelMapper;

    final Converter<VotingSessionInput, VotingSession> votingSessionConverter = new Converter<VotingSessionInput, VotingSession>() {
        @Override
        public VotingSession convert(MappingContext<VotingSessionInput, VotingSession> context) {
            VotingSessionInput votingSessionInput = context.getSource();
            // @formatter:off

            if(votingSessionInput.getTimeSession() == null || votingSessionInput.getTimeSession() == 0){
                votingSessionInput.setTimeSession(60000);
            }

            return new VotingSession(
                    new Schedule(votingSessionInput.getScheduleId()),
                    votingSessionInput.getTimeSession());
            // @formatter:on
        }
    };

    public VotingSessionIO() {
        modelMapper = new ModelMapper();
        modelMapper.addConverter(votingSessionConverter);
    }

    public VotingSession mapTo(VotingSessionInput votingSessionInput) {
        return this.modelMapper.map(votingSessionInput, VotingSession.class);
    }
}
