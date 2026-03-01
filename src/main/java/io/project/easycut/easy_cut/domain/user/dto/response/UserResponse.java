package io.project.easycut.easy_cut.domain.user.dto.response;

import java.util.UUID;

public record UserResponse(
    UUID id,
    String email,
    String name,
    String phone
) {

}
