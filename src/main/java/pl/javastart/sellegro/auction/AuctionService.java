package pl.javastart.sellegro.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.util.LambdaSafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AuctionService {

    private List<Auction> auctions;

    private static final String[] ADJECTIVES = {"Niesamowity", "Jedyny taki", "IGŁA", "HIT", "Jak nowy",
            "Perełka", "OKAZJA", "Wyjątkowy"};

    @Autowired
    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<Auction> find4MostExpensive (){
        return auctionRepository.findFirst4ByOrderByPriceDesc();
    }

    public void loadData() {
        List<Auction> auctionList = auctionRepository.findAll();
        for (Auction auction : auctionList){
            Random random = new Random();
            String randomAdjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
            String title = randomAdjective + " " + auction.getCarMake() + " " + auction.getCarModel();
            auction.setTitle(title);
            auctionRepository.save(auction);
        }
    }

    public Page<Auction> findAllForFilters(AuctionFilters auctionFilters, String sort, int page) {
        return auctionRepository.findByTitleContainingAndCarMakeContainingAndCarModelContainingAndColorContainingAllIgnoreCase(auctionFilters.getTitle(), auctionFilters.getCarMaker(), auctionFilters.getCarModel(), auctionFilters.getColor(), PageRequest.of(page,50,new Sort(Sort.Direction.DESC, sort)));
    }

}
