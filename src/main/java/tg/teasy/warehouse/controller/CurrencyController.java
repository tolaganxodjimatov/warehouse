package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    //Currency

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency) {
        Result result = currencyService.addCurrency(currency);
        return result;
    }

    @GetMapping("/currencyID")
    public Result getCurrencyById(@PathVariable Integer currencyID) {
        Result result = currencyService.getCurrencyById(currencyID);
        return result;
    }

    @PutMapping("/currencyID")
    public Result editCurrency(@PathVariable Integer currencyID, @RequestBody Currency currency) {
        Result result = currencyService.editCurrency(currencyID, currency);
        return result;
    }

    @DeleteMapping("/currencyID")
    public Result deleteCurrency(@PathVariable Integer currencyID) {
        Result result = currencyService.deleteCurrency(currencyID);
        return result;
    }


}
