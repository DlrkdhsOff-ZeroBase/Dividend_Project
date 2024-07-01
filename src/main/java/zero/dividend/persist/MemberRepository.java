package zero.dividend.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import zero.dividend.model.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
