package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.persistence.StoryworldRepository;

import java.util.Collection;

/**
 * Created by nvaughan on 10/29/2016.
 */
@Service
public class StoryworldService {

    @Autowired
    StoryworldRepository storyworldRepository;
    public Collection<Storyworld> list() {
        return storyworldRepository.findAll();
    }

}
