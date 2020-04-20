package duynguyen.securityjwt.repository;

import duynguyen.securityjwt.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    @Query(value = "SELECT ur.role.roleName FROM UserRoles AS ur WHERE ur.user.userId = :userId")
    List<String> findRoleName(@Param("userId") int userId);

}
