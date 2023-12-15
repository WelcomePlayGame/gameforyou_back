package game.you.controller;
import game.you.dto.StatisticsArticleDTOPL;
import game.you.service.StatisticsArticlePLService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/statistics/pl")
@RequiredArgsConstructor
@Slf4j
public class StatisticsArticlePLController {
    private  final StatisticsArticlePLService service;
    @GetMapping
    ResponseEntity<List<StatisticsArticleDTOPL>> getAllStatistics() {
        try {
            return  ResponseEntity.ok().body(service.getAllStatistics());
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/update")
    ResponseEntity<StatisticsArticleDTOPL> updateStatisticsEN(@RequestBody StatisticsArticleDTOPL statisticsArticleDTOPL) {
        log.info(statisticsArticleDTOPL.toString());
        return  ResponseEntity.ok().body(service.updateStatisticsEN(statisticsArticleDTOPL));
    }
}
