package game.you.service;
import game.you.dto.StatisticsArticleDTORU;
import game.you.entity.StatisticsArticleRU;
import game.you.repository.StatisticsArticleRURepository;
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
public class StatisticsArticleRUService {
    final private StatisticsArticleRURepository repository;
    final private ModelMapper modelMapper;

    StatisticsArticleDTORU convertToArticleDTORU(StatisticsArticleRU statisticsArticleRU) {
        return modelMapper.map(statisticsArticleRU, StatisticsArticleDTORU.class);
    }

    StatisticsArticleRU convertToArticleRU(StatisticsArticleDTORU statisticsArticleDTORU) {
        return modelMapper.map(statisticsArticleDTORU, StatisticsArticleRU.class);
    }

    public List<StatisticsArticleDTORU> getAllStatistics() {
        return repository.findAll().stream().map(this::convertToArticleDTORU).collect(Collectors.toList());
    }


    @Transactional
    public StatisticsArticleDTORU updateStatisticsRU(StatisticsArticleDTORU statisticsArticleDTORU) {
       Optional<StatisticsArticleRU> statisticsRU = repository.findById(statisticsArticleDTORU.getId());

       if (statisticsRU.get().getAction() != statisticsArticleDTORU.getAction() && statisticsArticleDTORU.getAction()!=0) {
           statisticsRU.get().setAction(statisticsArticleDTORU.getAction());
       } else if (statisticsRU.get().getMore15() != statisticsArticleDTORU.getMore15() && statisticsArticleDTORU.getMore15() !=0) {
           statisticsRU.get().setMore15(statisticsArticleDTORU.getMore15());
       } else if ( statisticsRU.get().getMore30() != statisticsArticleDTORU.getMore30() && statisticsArticleDTORU.getMore30() !=0) {
           statisticsRU.get().setMore30(statisticsArticleDTORU.getMore30());
       } else if ( statisticsRU.get().getMore45() != statisticsArticleDTORU.getMore45() && statisticsArticleDTORU.getMore45() !=0) {
           statisticsRU.get().setMore45(statisticsArticleDTORU.getMore45());
       }






        repository.save(statisticsRU.get());

       StatisticsArticleDTORU statisticsArticleDTORUUpdate = convertToArticleDTORU(statisticsRU.get());
        return statisticsArticleDTORUUpdate;
    }


}



