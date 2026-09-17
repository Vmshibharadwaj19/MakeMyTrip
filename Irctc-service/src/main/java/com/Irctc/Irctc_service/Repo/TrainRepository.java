package com.Irctc.Irctc_service.Repo;

import com.Irctc.Irctc_service.Models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {
    List<Train> findBySourceAndDestination(String source, String destination);

}
