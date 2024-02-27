package io.vanillabp.template.usecase.aggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseCaseRepository extends JpaRepository<UseCaseAggregate, String> {
}
