package br.com.southsystem.controller;

import br.com.southsystem.appcontroller.AppControllerBase;
import br.com.southsystem.dto.IO.ScheduleIO;
import br.com.southsystem.dto.input.ScheduleInput;
import br.com.southsystem.dto.input.VoteInput;
import br.com.southsystem.dto.output.ScheduleOutput;
import br.com.southsystem.model.Schedule;
import br.com.southsystem.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/schedule")
@Api(tags = "Schedule")
@CrossOrigin
public class ScheduleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class.getSimpleName());

    private ScheduleService scheduleService;

    private AppControllerBase appControllerBase;

    private ScheduleIO scheduleIO;

    @Autowired
    public ScheduleController(
            ScheduleService scheduleService,
            AppControllerBase appControllerBase,
            ScheduleIO scheduleIO) {
        this.scheduleService = scheduleService;
        this.appControllerBase = appControllerBase;
        this.scheduleIO = scheduleIO;
    }

    @ApiOperation(value = "Get All Schedule")
    @GetMapping({"", ""})
    // @formatter:off
    public ResponseEntity<?> indexSchedule() {
        LOGGER.info("index Schedule");
        Type type = new TypeToken<List<ScheduleOutput>>() {}.getType();
        List<ScheduleOutput> result = appControllerBase.toList(scheduleService.index(), type);
        return ResponseEntity.ok(result);
    }

    @PostMapping({"", ""})
    @ApiOperation(value = "Create an Schedule", notes = "Also returns a link to retrieve the saved Schedule in the location header")
    public ResponseEntity<Object> createSchedule(@Valid @RequestBody ScheduleInput scheduleInput) {
        Schedule schedule = scheduleIO.mapTo(scheduleInput);
        LOGGER.info("trying create new Schedule " + schedule.getName());
        Schedule savedSchedule = scheduleService.create(schedule);
        // @formatter:off
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSchedule.getId())
                .toUri();
        LOGGER.info("Schedule " + schedule.getName() + " create at " + location);
        return ResponseEntity.created(location).build();
        // @formatter:on
    }

    @PutMapping({"/{id}", "/{id}"})
    @ApiOperation(value = "Updates an Schedule")
    public ResponseEntity<?> updateSchedule(
            // @formatter:off
            @Min(value = 1) @PathVariable("id") Long id,
            @Valid @RequestBody ScheduleInput scheduleInput) {
        Schedule schedule = scheduleIO.mapTo(scheduleInput);
        LOGGER.info("trying update Schedule " + schedule.getName());
        scheduleService.update(id, schedule);
        LOGGER.info("Schedule " + schedule.getName() + " updated");
        return ResponseEntity.noContent().build();
    }
    // @formatter:on

    @ApiOperation(value = "Get an only Schedule")
    @GetMapping({"/{id}", "/{id}"})
    public ScheduleOutput showSchedule(@PathVariable("id") Long id) {
        LOGGER.info("show Schedule");
        return appControllerBase.mapTo(scheduleService.show(id), ScheduleOutput.class);
    }

    @DeleteMapping({"/{id}", "/{id}"})
    @ApiOperation(value = "Delete an Schedule")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
        LOGGER.info("trying deleting Schedule " + id);
        scheduleService.delete(id);
        LOGGER.info("Schedule " + id + " deleted");
        return ResponseEntity.ok().build();
    }

    @PostMapping({"/vote", "/vote"})
    @ApiOperation(value = "Registre vote in Schedule", notes = "Also returns a link to retrieve the saved Schedule in the location header")
    public ResponseEntity<Object> voteSchedule(@Valid @RequestBody VoteInput voteInput) {
        LOGGER.info("trying vote in Schedule " + voteInput.getScheduleId());
        Schedule savedSchedule = scheduleService.vote(voteInput);
        // @formatter:off
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSchedule.getId())
                .toUri();
        LOGGER.info("Schedule " + voteInput.getScheduleId() + " create at " + location);
        return ResponseEntity.created(location).build();
        // @formatter:on
    }
}
