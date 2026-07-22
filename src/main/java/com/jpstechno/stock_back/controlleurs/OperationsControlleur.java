package com.jpstechno.stock_back.controlleurs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("operations")
public class OperationsControlleur {

    @PostMapping("/ventes/total-journee")
    public double totalventeDeLaJournee(long magasinId) {
        return 0.0;
    }

}
