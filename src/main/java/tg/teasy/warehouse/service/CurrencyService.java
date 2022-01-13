package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName) return new Result("bunday Currency bor", false, null);
        currencyRepository.save(currency);
        return new Result("Saqlandi Currency", true, null);
    }

    public Result getCurrencyById(Integer currencyID) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyID);
        if (!optionalCurrency.isPresent()) return new Result("note found",false,new Currency());
        Currency currency = optionalCurrency.get();
        return new Result("OK",true, currency);
    }

    public Result editCurrency(Integer currencyID, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyID);
        if (!optionalCurrency.isPresent()) return new Result("Bunday Currency yoq", false, null);
        Currency currencyToEdit = optionalCurrency.get();
        currencyToEdit.setName(currency.getName());
        currencyRepository.save(currencyToEdit);
        return new Result("Ozgartirildi Currency", true, currencyToEdit.getId());
    }

    public Result deleteCurrency(Integer currencyID) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyID);
        if (!optionalCurrency.isPresent()) return new Result("Bunday Currency yoq", false, null);
        currencyRepository.deleteById(currencyID);
        return new Result("Ochirildi Currency", true, null);
    }


}
