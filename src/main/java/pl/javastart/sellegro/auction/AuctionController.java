package pl.javastart.sellegro.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
public class AuctionController {
    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    public String auctions(Model model,
                           @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters,
                           int page) {
        List<Auction> auctions;
        Page<Auction> auctionPage = auctionService.findAllForFilters(auctionFilters, sort, page-1);
        int totalPages = auctionPage.getTotalPages();
        int currentPage = auctionPage.getNumber();
        auctions = auctionPage.getContent();


        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage+1);
        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters);
        return "auctions";
    }
}
