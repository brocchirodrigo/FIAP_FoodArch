package br.com.fiap.foodarch.domain.entities.restaurants;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurants {

  private UUID id;

  private String name;

  private UUID ownerId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder(builderClassName = "CreateRestaurantBuilder")
  public Restaurants(String name, UUID ownerId) {
    this.name = name;
    this.ownerId = ownerId;
  }

  @Builder(builderClassName = "UpdateRestaurantBuilder")
  public Restaurants(UUID id, String name, UUID ownerId, LocalDateTime createdAt) {
    this.id = id;
    this.name = name;
    this.ownerId = ownerId;
    this.createdAt = createdAt;
  }

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

}
