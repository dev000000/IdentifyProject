package com.dev001.identify.mapper;

import com.dev001.identify.dto.request.PermissionRequest;
import com.dev001.identify.dto.response.PermissionResponse;
import com.dev001.identify.entity.permission.Permission;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission toPermission(PermissionRequest request) {
        if ( request == null ) {
            return null;
        }

        Permission.PermissionBuilder permission = Permission.builder();

        permission.name( request.getName() );
        permission.description( request.getDescription() );

        return permission.build();
    }

    @Override
    public PermissionResponse toPermissionResponse(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionResponse permissionResponse = new PermissionResponse();

        permissionResponse.setName( permission.getName() );
        permissionResponse.setDescription( permission.getDescription() );

        return permissionResponse;
    }
}
