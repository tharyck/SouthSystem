package br.com.southsystem.service;

import br.com.southsystem.dto.input.AbleToVote;
import br.com.southsystem.dto.input.VoteInput;
import br.com.southsystem.exceptions.AssociateAlreadyVotedRuntimeException;
import br.com.southsystem.exceptions.ScheduleNotFoundRuntimeException;
import br.com.southsystem.exceptions.UnableToVoteRuntimeException;
import br.com.southsystem.exceptions.VotingClosedRuntimeException;
import br.com.southsystem.model.Associated;
import br.com.southsystem.model.Schedule;
import br.com.southsystem.model.ScheduleStatus;
import br.com.southsystem.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class ScheduleService extends GenericService<Long, Schedule, ScheduleRepository>{

    private ScheduleRepository scheduleRepository;
    private AssociatedService associatedService;

    public ScheduleService(ScheduleRepository scheduleRepository, AssociatedService associatedService) {
        this.scheduleRepository = scheduleRepository;
        this.associatedService = associatedService;
    }

    @Override
    public  Schedule create(Schedule schedule){
        validateBeforeCreate(schedule);
        schedule.setScheduleStatus(ScheduleStatus.CREATED);
        return createTransaction(schedule);
    }

    public List<Schedule> index() {
        return repository.findAll();
    }

    public Schedule findById(Long id){
        return scheduleRepository.findById(id).orElseThrow(ScheduleNotFoundRuntimeException::new);
    }

    public Schedule vote(VoteInput voteInput) {
        Schedule schedule = scheduleRepository.findById(voteInput.getScheduleId()).orElseThrow(ScheduleNotFoundRuntimeException::new);
        Associated associated = associatedService.findById(voteInput.getAssociatedId());

        if(requestExternalApi(associated.getCpf())) {
            if (schedule.getScheduleStatus().getStatus().equals("STARTED")) {
                Set<Associated> associateds = schedule.getAssociatedVoters();

                if (!associateds.contains(associated)) {
                    associateds.add(associated);
                    schedule.setAssociatedVoters(associateds);

                    //Caso o voto seja a favor da pauta
                    if (voteInput.getVoteInFavor()) {
                        schedule.setVotesInFavor(schedule.getVotesInFavor() + 1);
                    }

                    //Atualiza a lista com o novo voto
                    update(schedule.getId(), schedule);
                } else {
                    throw new AssociateAlreadyVotedRuntimeException();
                }
            } else {
                throw new VotingClosedRuntimeException();
            }
        } else {

        }

        return schedule;
    }

    private Boolean requestExternalApi(String cpf){
        cpf = cpf.replaceAll("[^0-9]", "");

        String url = "https://user-info.herokuapp.com/users/"+ cpf;
        RestTemplate restTemplate = new RestTemplate();

        AbleToVote ableToVote = restTemplate.getForObject(url, AbleToVote.class);

        if(ableToVote.getStatus().equals("ABLE_TO_VOTE")){
            return Boolean.TRUE;
        } else if(ableToVote.getStatus().equals("UNABLE_TO_VOTE")){
            throw  new UnableToVoteRuntimeException();
        } else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Schedule getOne(Long id){
        return repository.getOne(id);
    }
    @Override
    public void validateBeforeCreate(Schedule model) {
    }

    @Override
    public void validateBeforeUpdate(Schedule model) {
    }

    @Override
    public void validateBeforeDelete(Long aLong) {
    }

}
