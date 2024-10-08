package br.com.fiap.foodarch.domain.entities.restaurants.operatingHour;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateRestaurantOperatingHourFactory {
  public RestaurantOperatingHours updateRestaurantOperatingHour(
      UUID id, 
      UUID restaurantId, 
      DayOfWeek dayOfWeek,
      String openTime,
      String closeTime, 
      LocalDateTime createdAt
    ) {
    return RestaurantOperatingHours.updateRestaurantOperatingHour()
        .id(id)
        .restaurantId(restaurantId)
        .dayOfWeek(dayOfWeek)
        .openTime(openTime)
        .closeTime(closeTime)
        .createdAt(createdAt)
        .build();
  }
}
