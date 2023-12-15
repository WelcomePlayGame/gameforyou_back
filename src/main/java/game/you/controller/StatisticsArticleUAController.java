package game.you.controller;

import game.you.dto.StatisticsArticleDTOUA;
import game.you.service.StatisticsArticleUAService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/statistics/ua")
@RequiredArgsConstructor
@Slf4j
public class StatisticsArticleUAController {
    private  final StatisticsArticleUAService service;
    @GetMapping
    ResponseEntity<List<StatisticsArticleDTOUA>> getAllStatistics() {
        try {
            return  ResponseEntity.ok().body(service.getAllStatistics());
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/update")
    ResponseEntity<StatisticsArticleDTOUA> updateStatistics(@RequestBody StatisticsArticleDTOUA statisticsArticleDTOUA) {
        return  ResponseEntity.ok().body(service.updateStatisticsUA(statisticsArticleDTOUA));
    }
}
