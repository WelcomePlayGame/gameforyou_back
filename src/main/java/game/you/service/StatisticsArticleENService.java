package game.you.service;
import game.you.dto.StatisticsArticleDTOEN;
import game.you.entity.ArticleEN;
import game.you.entity.StatisticsArticleEN;
import game.you.repository.ArticleRepositoryEN;
import game.you.repository.StatisticsArticleENRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class StatisticsArticleENService {
    final private StatisticsArticleENRepository repository;
    final  private ArticleRepositoryEN repository_article;
    final private ModelMapper modelMapper;

    StatisticsArticleDTOEN convertToArticleDTOEN(StatisticsArticleEN statisticsArticleEN) {
        return modelMapper.map(statisticsArticleEN, StatisticsArticleDTOEN.class);
    }

    StatisticsArticleEN convertToArticleEN(StatisticsArticleDTOEN statisticsArticleDTOEN) {
        return modelMapper.map(statisticsArticleDTOEN, StatisticsArticleEN.class);
    }

    public List<StatisticsArticleDTOEN> getAllStatistics() {
        return repository.findAll().stream().map(this::convertToArticleDTOEN).collect(Collectors.toList());
    }


    @Transactional
    public StatisticsArticleDTOEN updateStatisticsEN(StatisticsArticleDTOEN statisticsArticleDTOEN) {
       Optional<StatisticsArticleEN> statisticsEN = repository.findById(statisticsArticleDTOEN.getId());

       if (statisticsEN.get().getAction() != statisticsArticleDTOEN.getAction() && statisticsArticleDTOEN.getAction()!=0) {
           statisticsEN.get().setAction(statisticsArticleDTOEN.getAction());
       } else if (statisticsEN.get().getMore15() != statisticsArticleDTOEN.getMore15() && statisticsArticleDTOEN.getMore15() !=0) {
           statisticsEN.get().setMore15(statisticsArticleDTOEN.getMore15());
       } else if ( statisticsEN.get().getMore30() != statisticsArticleDTOEN.getMore30() && statisticsArticleDTOEN.getMore30() !=0) {
           statisticsEN.get().setMore30(statisticsArticleDTOEN.getMore30());
       } else if ( statisticsEN.get().getMore45() != statisticsArticleDTOEN.getMore45() && statisticsArticleDTOEN.getMore45() !=0) {
           statisticsEN.get().setMore45(statisticsArticleDTOEN.getMore45());
       }






        repository.save(statisticsEN.get());

       StatisticsArticleDTOEN statisticsArticleDTOENUpdate = convertToArticleDTOEN(statisticsEN.get());
        return statisticsArticleDTOENUpdate;
    }


}



