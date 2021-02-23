package com.example.demo.controller.vue;

import java.net.URI;
import java.util.List;

import com.example.demo.entity.VueBoard;
import com.example.demo.service.VueBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueBoardController {

    @Autowired
    private VueBoardService service;

    @RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
    public ResponseEntity<VueBoard> read(@PathVariable("boardNo") Long boardNo) throws Exception {

        log.info("VueBoard read()");

        VueBoard board = service.read(boardNo);

        return new ResponseEntity<VueBoard>(board, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<VueBoard>> list() throws Exception {
        log.info("VueBoard list()");

        return new ResponseEntity<List<VueBoard>>(service.list(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Validated @RequestBody VueBoard board, UriComponentsBuilder uriBuilder)
            throws Exception {

        log.info("VueBoard register");

        service.register(board);

        log.info("Register board.getBoardNo() = " + board.getBoardNo());

        URI resourceURI = uriBuilder.path("boards/{boardNo}").buildAndExpand(board.getBoardNo()).encode().toUri();

        return ResponseEntity.created(resourceURI).build();
    }

    @RequestMapping(value = "/{boardNo}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remove(@PathVariable("boardNo") Long boardNo) throws Exception {

        log.info("remove");

        service.remove(boardNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{boardNo}", method = RequestMethod.PUT)
    public ResponseEntity<Void> modify(@PathVariable("boardNo") Long boardNo, @Validated @RequestBody VueBoard board)
            throws Exception {

        log.info("modify");

        board.setBoardNo(boardNo);
        service.modify(board);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}