package br.com.fiap.foodarch.domain.entities.restaurants.assessment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantAssessmentTest {

    private RestaurantAssessment assessment;
    private UUID id;
    private UUID restaurantId;
    private String comment;
    private boolean like;
    private int stars;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        restaurantId = UUID.randomUUID();
        comment = "Great service!";
        like = true;
        stars = 5;
        createdAt = LocalDateTime.now().minusDays(1);
        updatedAt = LocalDateTime.now().minusDays(1);

        assessment = RestaurantAssessment
            .createAssessment()
            .userId(id)
            .restaurantId(restaurantId)
            .comment(comment)
            .like(like)
            .stars(stars)
            .build();

    }

    @Test
    void onUpdate() {
        assessment.onUpdate();
        assertNotNull(assessment.getUpdatedAt());
    }

    @Test
    void testEquals() {
        RestaurantAssessment otherAssessment = assessment = RestaurantAssessment
            .createAssessment()
            .userId(id)
            .restaurantId(restaurantId)
            .comment(comment)
            .like(like)
            .stars(stars)
            .build();
        assertEquals(assessment, otherAssessment);
    }

    @Test
    void canEqual() {
        RestaurantAssessment otherAssessment = new RestaurantAssessment();
        assertTrue(assessment.canEqual(otherAssessment));
    }

    @Test
    void testHashCode() {
        RestaurantAssessment otherAssessment = assessment = RestaurantAssessment
            .createAssessment()
            .userId(id)
            .restaurantId(restaurantId)
            .comment(comment)
            .like(like)
            .stars(stars)
            .build();
        assertEquals(assessment.hashCode(), otherAssessment.hashCode());
    }

    @Test
    void getRestaurantId() {
        assertEquals(restaurantId, assessment.getRestaurantId());
    }

    @Test
    void getComment() {
        assertEquals(comment, assessment.getComment());
    }

    @Test
    void isLike() {
        assertTrue(assessment.isLike());
    }

    @Test
    void getStars() {
        assertEquals(5, assessment.getStars());
    }

    @Test
    void setId() {
        UUID newId = UUID.randomUUID();
        assessment.setId(newId);
        assertEquals(newId, assessment.getId());
    }

    @Test
    void setRestaurantId() {
        UUID newRestaurantId = UUID.randomUUID();
        assessment.setRestaurantId(newRestaurantId);
        assertEquals(newRestaurantId, assessment.getRestaurantId());
    }

    @Test
    void setComment() {
        String newComment = "Not so great.";
        assessment.setComment(newComment);
        assertEquals(newComment, assessment.getComment());
    }

    @Test
    void setLike() {
        assessment.setLike(false);
        assertFalse(assessment.isLike());
    }

    @Test
    void setStars() {
        assessment.setStars(4);
        assertEquals(4, assessment.getStars());
    }

    @Test
    void setCreatedAt() {
        LocalDateTime newCreatedAt = LocalDateTime.now().minusDays(2);
        assessment.setCreatedAt(newCreatedAt);
        assertEquals(newCreatedAt, assessment.getCreatedAt());
    }

    @Test
    void setUpdatedAt() {
        LocalDateTime newUpdatedAt = LocalDateTime.now().minusDays(2);
        assessment.setUpdatedAt(newUpdatedAt);
        assertEquals(newUpdatedAt, assessment.getUpdatedAt());
    }
}