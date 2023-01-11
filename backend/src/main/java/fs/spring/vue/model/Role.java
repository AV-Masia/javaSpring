package fs.spring.vue.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER("USER"),
    ADMIN("ADMIN");

    Role(String name){
    }

//    private final Set<Permission> permissions;
//
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        System.out.println(Role.USER.name());
//        System.out.println(Role.ADMIN.name());
//        return getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//    }
}
