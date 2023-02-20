package com.newseoul.bookfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;import com.newseoul.bookfinder.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
