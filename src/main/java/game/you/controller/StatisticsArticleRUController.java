package game.you.controller;

import game.you.dto.StatisticsArticleDTORU;
import game.you.service.StatisticsArticleRUService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/statistics/ru")
@RequiredArgsConstructor
@Slf4j
public class StatisticsArticleRUController {
    private  final StatisticsArticleRUService service;
    @GetMapping
    ResponseEntity<List<StatisticsArticleDTORU>> getAllStatistics() {
        try {
            return  ResponseEntity.ok().body(service.getAllStatistics());
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/update")
    ResponseEntity<StatisticsArticleDTORU> updateStatistics(@RequestBody StatisticsArticleDTORU statisticsArticleDTORU) {
        return  ResponseEntity.ok().body(service.updateStatisticsRU(statisticsArticleDTORU));
    }
}
