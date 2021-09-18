package com.telefonica.offerengine.Util;

import com.telefonica.offerengine.Data.*;
import com.telefonica.offerengine.Interface.*;
import com.telefonica.offerengine.Logic.MyFunctions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {

    @Autowired
    private OfferRepository repository;

    @PostConstruct
    public void load() {
        if (repository.findAll().isEmpty()) repository.saveAll(getAll());
    }

    public List<Offer> getAll() {
        List<Offer> offerCache = new ArrayList<>();
        offerCache.add(
            new Offer(
                MyFunctions.convertStringToDate("08-09-2021"),
                MyFunctions.convertStringToDate("08-10-2021")
            )
        );
        offerCache.add(
            new Offer(
                MyFunctions.convertStringToDate("07-10-2021"),
                MyFunctions.convertStringToDate("07-11-2021")
            )
        );
        offerCache.add(
            new Offer(
                MyFunctions.convertStringToDate("12-10-2021"),
                MyFunctions.convertStringToDate("07-11-2021")
            )
        );
        offerCache.add(
            new Offer(
                MyFunctions.convertStringToDate("15-11-2021"),
                MyFunctions.convertStringToDate("15-12-2021")
            )
        );
        return offerCache;
    }
}
