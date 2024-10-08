package br.com.fiap.foodarch.application.gateways.interfaces.restaurants;

import br.com.fiap.foodarch.domain.entities.restaurants.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RestaurantRepository {

  Restaurant createRestaurant(Restaurant restaurant, UUID userId);

  Page<Restaurant> listRestaurants(Pageable pageable);

  Page<Restaurant> findByOwnerId(UUID ownerId, Pageable pageable);

  Restaurant updateRestaurant(Restaurant restaurant, UUID userId);

  Restaurant findById(UUID id);

  void deleteById(UUID id);
}
