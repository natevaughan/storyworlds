package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.model.Player;
import storyworlds.model.implementation.persistence.PlayerRepo;

/**
 * Created by nvaughan on 10/28/2016.
 */
@Service
public class PlayerService {

    @Autowired
    PlayerRepo playerRepo;

    public Player get(String id) {
        return playerRepo.findOne(id);
    }
}
