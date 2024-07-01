package zero.dividend.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zero.dividend.exception.impl.NoCompanyException;
import zero.dividend.model.Company;
import zero.dividend.model.Dividend;
import zero.dividend.model.ScrapedResult;
import zero.dividend.model.constants.CacheKey;
import zero.dividend.persist.CompanyRepository;
import zero.dividend.persist.DividendRepository;
import zero.dividend.persist.entity.CompanyEntity;
import zero.dividend.persist.entity.DividendEntity;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;


    @Cacheable(key = "#p0", value = CacheKey.KEY_FINANCE)
    public ScrapedResult getDividendByCompanyName(String companyName) {
        log.info("search company -> " + companyName);

        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(NoCompanyException::new);

        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        List<Dividend> dividends = dividendEntities.stream()
                                                        .map(e -> new Dividend(e.getDate(), e.getDividend()))
                                                        .collect(Collectors.toList());
        return new ScrapedResult(new Company(company.getTicker(), company.getName()), dividends);
    }

}
