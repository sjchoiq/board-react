package com.example.demo.controller;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Board;
import com.example.demo.service.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
    private static Logger logger = LoggerFactory.getLogger(BoardController.class);
    @Autowired
    private BoardService boardService;

    // get all board
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
        if (p_num == null || p_num <= 0) p_num = 1;

        return boardService.getPagingBoard(p_num);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Page<Board> getAllBoard1231(Pageable pageable) {

        return boardService.findAll(pageable);
    }

    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public Board getAllBoards(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

//    @RequestMapping(value = "/board/{no}", method = RequestMethod.GET)
//    public ResponseEntity<Board> getAllBoards(@PathVariable Integer no) {
//        return boardService.getBoard(no);
//    }
    @RequestMapping(value = "/board/{no}", method = RequestMethod.PUT)
    public ResponseEntity<Board> updateBoard(@PathVariable Integer no, @RequestBody Board board) {
        return boardService.updateBoard(no, board);
    }

    @RequestMapping(value = "/board/{no}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(@PathVariable Integer no) {
        return boardService.deleteBoard(no);
    }

}
