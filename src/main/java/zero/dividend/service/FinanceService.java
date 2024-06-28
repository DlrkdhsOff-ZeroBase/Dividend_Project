package zero.dividend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zero.dividend.model.Company;
import zero.dividend.model.Dividend;
import zero.dividend.model.ScrapedResult;
import zero.dividend.persist.CompanyRepository;
import zero.dividend.persist.DividendRepository;
import zero.dividend.persist.entity.CompanyEntity;
import zero.dividend.persist.entity.DividendEntity;

import java.util.*;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;

    private final DividendRepository dividendRepository;

    public ScrapedResult getDividendByCompanyName(String companyName) {
        CompanyEntity company = this.companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 회사명 입니다."));

        List<DividendEntity> dividendEntities = this.dividendRepository.findAllByCompanyId(company.getId());

        List<Dividend> dividends = new ArrayList<>();

        for (var entity : dividendEntities) {
            dividends.add(Dividend.builder()
                    .date(entity.getDate())
                    .dividend(entity.getDividend())
                    .build());
        }
        return new ScrapedResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(),
                dividends);
    }

}
