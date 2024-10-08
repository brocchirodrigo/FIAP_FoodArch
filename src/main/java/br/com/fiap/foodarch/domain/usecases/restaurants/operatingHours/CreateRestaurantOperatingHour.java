package br.com.fiap.foodarch.domain.usecases.restaurants.operatingHours;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.operatingHour.RestaurantOperatingHourRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.CreateRestaurantOperatingHourFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.operatingHour.RestaurantOperatingHours;
import br.com.fiap.foodarch.domain.entities.users.User;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantOperatingHourAlreadyExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.domain.exceptions.users.UserUnauthorizedException;
import br.com.fiap.foodarch.domain.records.restaurants.operatingHour.RestaurantOperatingHourInput;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class CreateRestaurantOperatingHour {
    private final RestaurantOperatingHourRepository repository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final CreateRestaurantOperatingHourFactory factory;

    public CreateRestaurantOperatingHour(
            RestaurantOperatingHourRepository repository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository,
            CreateRestaurantOperatingHourFactory factory) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.factory = factory;
    }

    public RestaurantOperatingHours execute(
            UUID restaurantId,
            UUID ownerId,
            RestaurantOperatingHourInput restaurantOperatingHourInput) {

        User userTo = this.userRepository.findById(ownerId);

        if (userTo.getId() == null) {
            throw new UserNotExistsException("User not exists", HttpStatus.BAD_REQUEST);
        }

        Restaurant restaurant = this.restaurantRepository.findById(restaurantId);

        if (restaurant.getId() == null) {
            throw new RestaurantNotFound("Restaurant not found", HttpStatus.BAD_REQUEST);
        }

        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new UserUnauthorizedException("User not authorized", HttpStatus.UNAUTHORIZED);
        }

        if (this.repository.findByRestaurantIdAndDayOfWeek(restaurantId,
                restaurantOperatingHourInput.dayOfWeek()) != null) {
            throw new RestaurantOperatingHourAlreadyExistsException("Restaurant operating hour already exists",
                    HttpStatus.BAD_REQUEST);
        }

        RestaurantOperatingHours restaurantOperatingHours = factory.createRestaurantOperatingHour(
                restaurantOperatingHourInput.restaurantId(),
                restaurantOperatingHourInput.dayOfWeek(),
                restaurantOperatingHourInput.openTime(),
                restaurantOperatingHourInput.closeTime());

        return repository.createRestaurantOperatingHour(restaurantOperatingHours,
                restaurantOperatingHourInput.restaurantId());
    }

}
