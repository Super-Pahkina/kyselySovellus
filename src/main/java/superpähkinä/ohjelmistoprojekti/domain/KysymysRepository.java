package superpähkinä.ohjelmistoprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KysymysRepository extends CrudRepository<Kysymys, Long> {

	List<Kysymys> findByName(String name);
}
