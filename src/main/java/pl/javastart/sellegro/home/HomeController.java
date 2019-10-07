package pl.javastart.sellegro.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.javastart.sellegro.auction.Auction;
import pl.javastart.sellegro.auction.AuctionRepository;
import pl.javastart.sellegro.auction.AuctionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    private AuctionService auctionService;

    public HomeController(AuctionService auctionService) {
        this.auctionService = auctionService;
        auctionService.loadData();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cars", auctionService.find4MostExpensive());
        return "home";
    }
}
