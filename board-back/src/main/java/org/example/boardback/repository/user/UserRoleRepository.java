package org.example.boardback.repository.user;

import org.example.boardback.common.enums.RoleType;
import org.example.boardback.entity.user.Role;
import org.example.boardback.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
