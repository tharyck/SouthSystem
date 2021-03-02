package br.com.southsystem.service;

import br.com.southsystem.dto.input.VotingSessionInput;
import br.com.southsystem.model.Schedule;
import br.com.southsystem.model.ScheduleStatus;
import br.com.southsystem.model.VotingSession;
import br.com.southsystem.model.VotingSessionStatus;
import br.com.southsystem.repository.VotingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingSessionService extends GenericService<Long, VotingSession, VotingSessionRepository> {

	private ScheduleService scheduleService;
	private VotingSessionRepository votingSessionRepository;

	public VotingSessionService(ScheduleService scheduleService, VotingSessionRepository votingSessionRepository) {
		this.scheduleService = scheduleService;
		this.votingSessionRepository = votingSessionRepository;
	}

	public List<VotingSession> index() {
		return repository.findAll();
	}

	public VotingSession create(VotingSessionInput votingSessionInput) {
		VotingSession votingSession = votingSessionRepository.findByScheduleId(votingSessionInput.getScheduleId());
		Schedule schedule = scheduleService.findById(votingSessionInput.getScheduleId());

		try {
			//Caso a sessão já exista, reinicia a sessão com o tempo inicial
			if (votingSession != null && !votingSession.getVotingSessionStatus().getStatus().equals("FINISHED") && schedule.getScheduleStatus().getStatus().equals("STARTED")) {
				Thread.sleep(votingSession.getTimeSession());

			} else {
				schedule.setScheduleStatus(ScheduleStatus.STARTED);
				scheduleService.update(schedule.getId(), schedule);


				if(votingSessionInput.getTimeSession() == null){
					votingSessionInput.setTimeSession(1);
				}
				votingSession = create(new VotingSession(schedule, votingSessionInput.getTimeSession()*60000));

				Thread.sleep(votingSession.getTimeSession());
			}
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
		return votingSession;
	}

	public void updateAfterVotingSession(Long id, VotingSession votingSession){
		//Recupera e atualiza no objeto em questão
		Schedule schedule = scheduleService.findById(id);

		//Encerra a pauta e atualiza
		if(schedule.getAssociatedVoters().size() == 0){
			schedule.setScheduleStatus(ScheduleStatus.NO_VOTES);
		}else if((schedule.getAssociatedVoters().size() - schedule.getVotesInFavor()) <= schedule.getVotesInFavor()){
			schedule.setScheduleStatus(ScheduleStatus.APPROVED);
		} else {
			schedule.setScheduleStatus(ScheduleStatus.REPROVED);
		}

		scheduleService.update(schedule.getId(), schedule);

		//Encerra a votação e atualiza
		votingSession.setVotingSessionStatus(VotingSessionStatus.FINISHED);
		update(votingSession.getId(), votingSession);
	}

	@Override
	public void validateBeforeCreate(VotingSession model) {

	}

	@Override
	public void validateBeforeUpdate(VotingSession model) {

	}

	@Override
	public void validateBeforeDelete(Long aLong) {

	}
}
