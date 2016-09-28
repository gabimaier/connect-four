package gm.connectfour.domain.repository;

import gm.connectfour.domain.entity.Game;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends PagingAndSortingRepository<Game, String>{
}
