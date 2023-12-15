package game.you.service;
import game.you.dto.StatisticsArticleDTOUA;
import game.you.entity.StatisticsArticleUA;
import game.you.repository.StatisticsArticleUARepository;
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
public class StatisticsArticleUAService {
    final private StatisticsArticleUARepository repository;
    final private ModelMapper modelMapper;

    StatisticsArticleDTOUA convertToArticleDTOUA(StatisticsArticleUA statisticsArticleUA) {
        return modelMapper.map(statisticsArticleUA, StatisticsArticleDTOUA.class);
    }

    StatisticsArticleUA convertToArticleUA(StatisticsArticleDTOUA statisticsArticleDTOUA) {
        return modelMapper.map(statisticsArticleDTOUA, StatisticsArticleUA.class);
    }

    public List<StatisticsArticleDTOUA> getAllStatistics() {
        return repository.findAll().stream().map(this::convertToArticleDTOUA).collect(Collectors.toList());
    }


    @Transactional
    public StatisticsArticleDTOUA updateStatisticsUA(StatisticsArticleDTOUA statisticsArticleDTOUA) {
       Optional<StatisticsArticleUA> statisticsUA = repository.findById(statisticsArticleDTOUA.getId());

       if (statisticsUA.get().getAction() != statisticsArticleDTOUA.getAction() && statisticsArticleDTOUA.getAction()!=0) {
           statisticsUA.get().setAction(statisticsArticleDTOUA.getAction());
       } else if (statisticsUA.get().getMore15() != statisticsArticleDTOUA.getMore15() && statisticsArticleDTOUA.getMore15() !=0) {
           statisticsUA.get().setMore15(statisticsArticleDTOUA.getMore15());
       } else if ( statisticsUA.get().getMore30() != statisticsArticleDTOUA.getMore30() && statisticsArticleDTOUA.getMore30() !=0) {
           statisticsUA.get().setMore30(statisticsArticleDTOUA.getMore30());
       } else if ( statisticsUA.get().getMore45() != statisticsArticleDTOUA.getMore45() && statisticsArticleDTOUA.getMore45() !=0) {
           statisticsUA.get().setMore45(statisticsArticleDTOUA.getMore45());
       }






        repository.save(statisticsUA.get());

       StatisticsArticleDTOUA statisticsArticleDTOUAUpdate = convertToArticleDTOUA(statisticsUA.get());
        return statisticsArticleDTOUAUpdate;
    }


}



