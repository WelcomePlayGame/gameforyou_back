package game.you.controller;

import game.you.dto.StatisticsArticleDTOEN;
import game.you.entity.StatisticsArticleEN;
import game.you.service.StatisticsArticleENService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/statistics/en")
@RequiredArgsConstructor
@Slf4j
public class StatisticsArticleENController {
    private  final StatisticsArticleENService service;
    @GetMapping
    ResponseEntity<List<StatisticsArticleDTOEN>> getAllStatistics() {
        try {
            return  ResponseEntity.ok().body(service.getAllStatistics());
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/update")
    ResponseEntity<StatisticsArticleDTOEN> updateStatisticsEN(@RequestBody StatisticsArticleDTOEN statisticsArticleDTOEN) {
        log.info(statisticsArticleDTOEN.toString());
        return  ResponseEntity.ok().body(service.updateStatisticsEN(statisticsArticleDTOEN));
    }
}
