package com.Irctc.Irctc_service.Controller;

import com.Irctc.Irctc_service.Dto.TrainRequestDto;
import com.Irctc.Irctc_service.Dto.TrainResponseDto;
import com.Irctc.Irctc_service.Service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainController {

    private final TrainService trainService;
    @Operation(summary = "create Train")
    @PostMapping
    public ResponseEntity<TrainResponseDto> creaTrain(@Valid @RequestBody TrainRequestDto requestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainService.create(requestDto));
    }

    @Operation(summary = "Get List pf Trains")
    @GetMapping
    public  ResponseEntity<Page<TrainResponseDto>> getAllTrains(@PageableDefault(size = 5) Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(trainService.getAllTrains(pageable));
    }

    @Operation(summary = "Search by source and destination")
    @GetMapping("/search")
    public ResponseEntity<List<TrainResponseDto>> getTrainsBySourceAndDestination(@RequestParam String source, @RequestParam String destination)
    {
        return ResponseEntity.ok(
                trainService.searchTrains(
                        source,
                        destination));
    }

    @Operation(summary = "Reserve seats")
    @PutMapping("/{id}/reserve")
    public ResponseEntity<TrainResponseDto> reserve(
            @PathVariable Long id,
            @RequestParam Integer seats) {

        return ResponseEntity.ok(
                trainService.reserve(
                        id, seats));
    }
    @GetMapping("/trains/{id}")
    public ResponseEntity<TrainResponseDto> getTrainById(@PathVariable Long id) {

        return ResponseEntity.ok().body(trainService.getTrainById(id));
    }

    @PutMapping("/{id}/release")
    public ResponseEntity<TrainResponseDto> release(
            @PathVariable Long id,
            @RequestParam Integer seats) {

        return ResponseEntity.ok(
                trainService.release(id, seats));
    }

}
