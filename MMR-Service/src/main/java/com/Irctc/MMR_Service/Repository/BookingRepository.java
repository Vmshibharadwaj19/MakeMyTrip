package com.Irctc.MMR_Service.Repository;

import com.Irctc.MMR_Service.Entitits.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository
        extends JpaRepository<Booking, Long> {

}
