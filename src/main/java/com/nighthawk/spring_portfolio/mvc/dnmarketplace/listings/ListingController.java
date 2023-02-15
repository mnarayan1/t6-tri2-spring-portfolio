package com.nighthawk.spring_portfolio.mvc.dnmarketplace.listings;
import com.nighthawk.spring_portfolio.mvc.dnmarketplace.listings.ListingJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/listings") // all requests in file begin with this URI
public class ListingController {
    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private ListingJpaRepository repository;
    /*
     * GET List of Jokes
     *
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Listing>> getAllListings() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable long id) {
        Optional<Listing> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Listing listing = optional.get(); // value from findByID
            return new ResponseEntity<>(listing, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/create")
    public ResponseEntity<Listing> createListing(@RequestBody Listing object) {
        String name = object.getName();
        String seller = object.getSeller();
        int price = object.getPrice();
        String description = object.getDescription();
        String category = object.getCategory();
        String image = object.getImage();
        repository.saveAndFlush(new Listing(null, name, price, seller, description, category, image));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Listing>> searchJoke(@PathVariable String name) {
        List<Listing> listings = repository.findByNameIgnoreCase(name);
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }

    @GetMapping("/filter/{category}")
    public ResponseEntity<List<Listing>> filter(@PathVariable String category) {
        List<Listing> listings = repository.findByCategory(category);
        return new ResponseEntity<>(listings, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Listing> deleteListing(@PathVariable long id) {
        Optional<Listing> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Listing listing = optional.get(); // value from findByID
            repository.deleteById(id); // value from findByID
            return new ResponseEntity<>(listing, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable long id, @RequestBody Listing newListing) {
        Optional<Listing> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Listing listing = optional.get(); // value from findByID
            listing.setName(newListing.getName()); // value from findByID
            listing.setPrice(newListing.getPrice()); // value from findByID
            listing.setSeller(newListing.getSeller()); // value from findByID
            listing.setDescription(newListing.getDescription()); // value from findByID
            repository.save(listing);
            return new ResponseEntity<>(listing, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}







