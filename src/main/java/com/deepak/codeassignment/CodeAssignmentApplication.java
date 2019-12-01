package com.deepak.codeassignment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class CodeAssignmentApplication /*implements CommandLineRunner */ {

    /*@Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ProductRepository productRepository;
*/
    public static void main(String[] args) {
        SpringApplication.run(CodeAssignmentApplication.class, args);
    }

    /*//Only for testing if the data is being saved to the product tables
    //Todo delete this code and move this to a sql file - data.sql for static data
    @Override
    public void run(String... args) throws Exception {


        // Create a Product
        Product product1 = Product.builder()
                .productId(29990L)
                .productName("LG 43 Inch Full HD SMART TV ")
                .section("Electronics")
                .build();

        // Create a Product
        Product product2 = Product.builder()
                .productId(29991L)
                .productName("King size bed mattress")
                .section("Furniture")
                .build();

        // Create a Product
        Product product3 = Product.builder()
                .productId(29999L)
                .productName("The Alchemist")
                .section("Books")
                .build();

        // Create a Product
        Product product4 = Product.builder()
                .productId(39999L)
                .productName("Hotwheels carno 8900 Porshe ")
                .section("Toys")
                .build();


        // Create a LIVE Offer
        Offer offer1 = Offer.builder()
                .offerDescription("Black Friday sale")
                .offerId(MathUtils.secureRandomLong())
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.LIVE)
                .build();

        // Create an expired Offer
        Offer offer2 = Offer.builder()
                .offerDescription("Cyber Monday sale")
                .offerId(MathUtils.secureRandomLong())
                .product(product2)
                .startDateTime(LocalDateTime.now().minusHours(20))
                .endDateTime(LocalDateTime.now().minusHours(15))
                .offerStatus(OfferStatusEnum.EXPIRED)
                .build();

        // Create an Offer
        Offer offer3 = Offer.builder()
                .offerDescription("Chrimstas sale")
                .offerId(MathUtils.secureRandomLong())
                .product(product3)
                .startDateTime(LocalDateTime.now().minusHours(10))
                .endDateTime(LocalDateTime.now().minusHours(20))
                .offerStatus(OfferStatusEnum.LIVE)
                .build();

        // Create an Offer
        Offer offer4 = Offer.builder()
                .offerDescription("Early year sale")
                .offerId(MathUtils.secureRandomLong())
                .product(product4)
                .startDateTime(LocalDateTime.now().minusHours(10))
                .endDateTime(LocalDateTime.now().minusHours(20))
                .offerStatus(OfferStatusEnum.CANCELLED)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        log.info("Saved the demo products!!!!!!");

        offerRepository.save(offer1);
        offerRepository.save(offer2);
        offerRepository.save(offer3);
        offerRepository.save(offer4);


        log.info("Saved the Demo offers!!!!!!");

    }*/
}
