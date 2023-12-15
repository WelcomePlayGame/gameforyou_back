package game.you.service;
import game.you.dto.StatisticsArticleDTOPL;
import game.you.entity.StatisticsArticlePL;
import game.you.repository.StatisticsArticlePLRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsArticlePLService {
    final private StatisticsArticlePLRepository repository;
    final private ModelMapper modelMapper;

    StatisticsArticleDTOPL convertToArticleDTOPL(StatisticsArticlePL statisticsArticlePL) {
        return modelMapper.map(statisticsArticlePL, StatisticsArticleDTOPL.class);
    }

    StatisticsArticlePL convertToArticlePl(StatisticsArticleDTOPL statisticsArticleDTOPL) {
        return modelMapper.map(statisticsArticleDTOPL, StatisticsArticlePL.class);
    }

    public List<StatisticsArticleDTOPL> getAllStatistics() {
        return repository.findAll().stream().map(this::convertToArticleDTOPL).collect(Collectors.toList());
    }


    @Transactional
    public StatisticsArticleDTOPL updateStatisticsEN(StatisticsArticleDTOPL statisticsArticleDTOPL) {
       Optional<StatisticsArticlePL> statisticsEN = repository.findById(statisticsArticleDTOPL.getId());

       if (statisticsEN.get().getAction() != statisticsArticleDTOPL.getAction() && statisticsArticleDTOPL.getAction()!=0) {
           statisticsEN.get().setAction(statisticsArticleDTOPL.getAction());
       } else if (statisticsEN.get().getMore15() != statisticsArticleDTOPL.getMore15() && statisticsArticleDTOPL.getMore15() !=0) {
           statisticsEN.get().setMore15(statisticsArticleDTOPL.getMore15());
       } else if ( statisticsEN.get().getMore30() != statisticsArticleDTOPL.getMore30() && statisticsArticleDTOPL.getMore30() !=0) {
           statisticsEN.get().setMore30(statisticsArticleDTOPL.getMore30());
       } else if ( statisticsEN.get().getMore45() != statisticsArticleDTOPL.getMore45() && statisticsArticleDTOPL.getMore45() !=0) {
           statisticsEN.get().setMore45(statisticsArticleDTOPL.getMore45());
       }






        repository.save(statisticsEN.get());

       StatisticsArticleDTOPL statisticsArticleDTOPLUpdate = convertToArticleDTOPL(statisticsEN.get());
        return statisticsArticleDTOPLUpdate;
    }


}



