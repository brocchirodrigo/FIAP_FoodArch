package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.usecases.users.CreateUser;
import br.com.fiap.foodarch.domain.usecases.users.ListUsers;
import br.com.fiap.foodarch.domain.usecases.users.UpdateUser;
import br.com.fiap.foodarch.domain.entities.users.UserFactory;
import br.com.fiap.foodarch.application.controller.users.CreateUserController;
import br.com.fiap.foodarch.application.controller.users.GetAllUsersController;
import br.com.fiap.foodarch.application.controller.users.UpdateUserController;
import br.com.fiap.foodarch.infra.external.users.JpaUserRepository;
import br.com.fiap.foodarch.infra.external.users.UserEntityMapper;
import br.com.fiap.foodarch.infra.gateways.persistance.users.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectUserConfig {

  @Bean
  public UserFactory userFactory() {
      return new UserFactory();
  }

  @Bean
  public UserEntityMapper userEntityMapper() {
      return new UserEntityMapper();
  }

  @Bean
  UpdateUser updateUser(UserRepository repository, UserFactory factory) {
    return new UpdateUser(repository, factory);
  }

  @Bean
  ListUsers listUser(UserRepository repository) {
    return new ListUsers(repository);
  }

  @Bean
  CreateUser createUser(UserRepository repository, UserFactory factory) {
    return new CreateUser(repository, factory);
  }

  @Bean
  JpaUserRepository jpaUserRepository(IUserRepository repository, UserEntityMapper mapper) {
    return new JpaUserRepository(repository, mapper);
  }

  @Bean
  UpdateUserController updateUserController(UpdateUser updateUser) {
    return new UpdateUserController(updateUser);
  }

  @Bean
  GetAllUsersController getAllUsersController(ListUsers listUsers) {
    return new GetAllUsersController(listUsers);
  }

  @Bean
  CreateUserController createUserController(CreateUser createUser) {
    return new CreateUserController(createUser);
  }

}
