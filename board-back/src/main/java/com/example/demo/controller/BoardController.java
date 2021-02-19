package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/api")
@Slf4j
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    @ResponseBody
    public List<Board> getAllBoards() {
        List<Board> info = boardService.getAllBoard();
        log.error(info.toString());
        return info;
    }
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    @ResponseBody
    public Integer createBoard(@RequestBody Board board) {
        log.error(board.toString());
        return boardService.createBoard(board);
    }
}
