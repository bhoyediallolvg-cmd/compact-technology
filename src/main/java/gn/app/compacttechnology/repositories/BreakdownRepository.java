package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Breakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {

    List<Breakdown> findByType(Integer type);

    List<Breakdown> findByDescriptionContainingIgnoreCase(String description);

    List<Breakdown> findByBreakedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Breakdown> findByFixedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Breakdown> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Breakdown> findByWorkforceCostGreaterThanEqual(Integer minCost);

    List<Breakdown> findByPartsCostGreaterThanEqual(Integer minCost);

    List<Breakdown> findByFixedAtIsNull();
}
