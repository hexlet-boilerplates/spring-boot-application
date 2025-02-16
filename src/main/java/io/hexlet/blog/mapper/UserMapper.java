package io.hexlet.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import io.hexlet.blog.dto.UserDTO;
import io.hexlet.blog.model.User;

@Mapper(uses = { JsonNullableMapper.class,
    ReferenceMapper.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {
  public abstract User map(UserDTO model);

  public abstract UserDTO map(User model);

  public abstract void update(UserDTO update, @MappingTarget User destination);
}
