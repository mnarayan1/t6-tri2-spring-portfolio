package com.nighthawk.spring_portfolio.mvc.dnmarketplace.listings;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA is an object-relational mapping (ORM) to persistent data, originally relational databases (SQL). Today JPA implementations has been extended for NoSQL.
public interface ListingJpaRepository extends JpaRepository<Listing, Long> {
    // JPA has many built in methods, these few have been prototyped for this
    // application
    List<Listing> findByNameIgnoreCase(String name);
    List<Listing> findByCategory(String category);
}