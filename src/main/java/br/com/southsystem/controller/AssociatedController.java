package br.com.southsystem.controller;

import br.com.southsystem.appcontroller.AppControllerBase;
import br.com.southsystem.dto.IO.AssociatedIO;
import br.com.southsystem.dto.input.AssociatedInput;
import br.com.southsystem.dto.output.AssociatedOutput;
import br.com.southsystem.model.Associated;
import br.com.southsystem.service.AssociatedService;
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
@RequestMapping(path = "/associated")
@Api(tags = "Associated")
@CrossOrigin
public class AssociatedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociatedController.class.getSimpleName());

    private AssociatedService associatedService;

    private AppControllerBase appControllerBase;

    private AssociatedIO associatedIO;

    @Autowired
    public AssociatedController(
            AssociatedService associatedService,
            AppControllerBase appControllerBase,
            AssociatedIO associatedIO) {
        this.associatedService = associatedService;
        this.appControllerBase = appControllerBase;
        this.associatedIO = associatedIO;
    }

    @ApiOperation(value = "Get All Associated")
    @GetMapping({"", ""})
    public ResponseEntity<?> indexAssociated() {
        LOGGER.info("index associated");
        Type type = new TypeToken<List<AssociatedOutput>>() {}.getType();
        List<AssociatedOutput> result = appControllerBase.toList(associatedService.index(), type);
        return ResponseEntity.ok(result);
    }

    @PostMapping({"", ""})
    @ApiOperation(value = "Create an Associated", notes = "Also returns a link to retrieve the saved associated in the location header")
    public ResponseEntity<Object> createAssociated(@Valid @RequestBody AssociatedInput associatedInput) {
        Associated associated = associatedIO.mapTo(associatedInput);
        LOGGER.info("trying create new associated " + associated.getName());
        Associated savedAssociated = associatedService.create(associated);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAssociated.getId())
                .toUri();
        LOGGER.info("associated " + associated.getName() + " create at " + location);
        return ResponseEntity.created(location).build();
    }

    @PutMapping({"/{id}", "/{id}"})
    @ApiOperation(value = "Updates an Associated")
    public ResponseEntity<?> updateAssociated(@Min(value = 1) @PathVariable("id") Long id,
        @Valid @RequestBody AssociatedInput associatedInput) {
            Associated associated = associatedIO.mapTo(associatedInput);
            LOGGER.info("trying update associated " + associated.getName());
            associatedService.update(id, associated);
            LOGGER.info("associated " + associated.getName() + " updated");
            return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Get an only associated")
    @GetMapping({"/{id}", "/{id}"})
    public AssociatedOutput showAssociated(@PathVariable("id") Long id) {
        LOGGER.info("show associated");
        return appControllerBase.mapTo(associatedService.show(id), AssociatedOutput.class);
    }

    @DeleteMapping({"/{id}", "/{id}"})
    @ApiOperation(value = "Delete an Associated")
    public ResponseEntity<?> deleteAssociated(@PathVariable("id") Long id) {
        LOGGER.info("trying deleting associated " + id);
        associatedService.delete(id);
        LOGGER.info("associated " + id + " deleted");
        return ResponseEntity.ok().build();
    }
}