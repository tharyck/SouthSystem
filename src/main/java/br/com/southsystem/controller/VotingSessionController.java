package br.com.southsystem.controller;

import br.com.southsystem.appcontroller.AppControllerBase;
import br.com.southsystem.dto.IO.VotingSessionIO;
import br.com.southsystem.dto.input.VotingSessionInput;
import br.com.southsystem.dto.output.AssociatedOutput;
import br.com.southsystem.dto.output.VotingSessionOutput;
import br.com.southsystem.model.VotingSession;
import br.com.southsystem.service.VotingSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/voting_session")
@Api(tags = "VotingSession")
@CrossOrigin
public class VotingSessionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotingSessionController.class.getSimpleName());

    private VotingSessionService votingSessionService;

    private AppControllerBase appControllerBase;

    private EntityManager entityManager;

    @Autowired
    public VotingSessionController(
            VotingSessionService votingSessionService,
            AppControllerBase appControllerBase,
            VotingSessionIO votingSessionIO,
            EntityManager entityManager
    ) {
        this.votingSessionService = votingSessionService;
        this.appControllerBase = appControllerBase;
        this.entityManager = entityManager;
    }

    @ApiOperation(value = "Get All Voting Session")
    @GetMapping({"", ""})
    // @formatter:off
    public ResponseEntity<?> indexVotingSession() {
        LOGGER.info("index voting session");
        Type type = new TypeToken<List<VotingSessionOutput>>() {}.getType();
        List<AssociatedOutput> result = appControllerBase.toList(votingSessionService.index(), type);
        return ResponseEntity.ok(result);
    }

    @PatchMapping({"", ""})
    @ApiOperation(value = "Create a vote Session")
    public ResponseEntity<Object> startVoting(@Valid @RequestBody VotingSessionInput votingSessionInput) {
        LOGGER.info("trying create new Vote Session " + votingSessionInput.getScheduleId());
        VotingSession createdVoteSession = votingSessionService.create(votingSessionInput);
        entityManager.clear();

        LOGGER.info("update Scheduling after Voting Session " + votingSessionInput.getScheduleId());
        votingSessionService.updateAfterVotingSession(votingSessionInput.getScheduleId(), createdVoteSession);
        // @formatter:off
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVoteSession)
                .toUri();
        LOGGER.info("Vote Session " + votingSessionInput.getScheduleId() + " started at " + location);
        return ResponseEntity.created(location).build();
        // @formatter:on
    }

    @ApiOperation(value = "Get an only voting session")
    @GetMapping({"/{id}", "/{id}"})
    public VotingSessionOutput showVotingSession(@PathVariable("id") Long id) {
        LOGGER.info("show voting session");
        return appControllerBase.mapTo(votingSessionService.show(id), VotingSessionOutput.class);
    }
}