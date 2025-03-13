package java16.taskdto.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    ADMIN,
    OWNER;

    @Override
    public String getAuthority() {
        return name();
    }
}
