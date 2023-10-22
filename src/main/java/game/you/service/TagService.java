package game.you.service;

import game.you.entity.Tag;
import game.you.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {
    final private TagRepository repository;

    public List<Tag> getListTag() {
        return repository.findAll();
    }
}
