package oving5REST.server.oving5REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import oving5REST.server.oving5REST.model.SourceCode;
import oving5REST.server.oving5REST.service.CompilerService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.IOException;

@RestController
@RequestMapping("/compile")
@EnableAutoConfiguration
@CrossOrigin
public class CompilerController {

    private static final Logger LOGGER = LogManager.getLogger(CompilerController.class);

    @Autowired
    private CompilerService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public SourceCode runCompile(@RequestBody SourceCode sourceCode) throws IOException {
        LOGGER.info("Compiling and running..." + sourceCode.getSourceCode());

        sourceCode.setOutput(service.doCompile(sourceCode));

        return sourceCode;
    }
}