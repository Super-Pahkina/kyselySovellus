package superpähkinä.ohjelmistoprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface KyselyRepository extends CrudRepository<Kysely, Long>{

	List<Kysely> findByName(String nimi);
}
