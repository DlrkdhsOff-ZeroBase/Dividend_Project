package zero.dividend.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zero.dividend.persist.entity.DividendEntity;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {
}
