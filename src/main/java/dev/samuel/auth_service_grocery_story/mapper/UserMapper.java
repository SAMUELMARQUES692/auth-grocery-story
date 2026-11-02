package dev.samuel.auth_service_grocery_story.mapper;

import dev.samuel.auth_service_grocery_story.entity.Scope;
import dev.samuel.auth_service_grocery_story.entity.User;
import dev.samuel.auth_service_grocery_story.request.UserRequest;
import dev.samuel.auth_service_grocery_story.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "scopes", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeEntitiesToStringScopes")
    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "scopes", ignore = true)
    void atualizarUser(UserRequest request, @MappingTarget User user);

    @Named("mapScopeEntitiesToStringScopes")
    default List<String> mapScopeEntitiesToStringScopes(List<Scope> scopes) {
        if (scopes == null) return List.of();

        return scopes.stream()
                .map(Scope::getName)
                .toList();
    }
}
