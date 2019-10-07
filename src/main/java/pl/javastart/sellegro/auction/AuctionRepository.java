package pl.javastart.sellegro.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import javax.swing.event.ListDataEvent;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction,Long> {

   List<Auction> findFirst4ByOrderByPriceDesc();
   Page<Auction> findByTitleContainingAndCarMakeContainingAndCarModelContainingAndColorContainingAllIgnoreCase(String title, String carMaker, String carMode, String color, Pageable pageable);


}
