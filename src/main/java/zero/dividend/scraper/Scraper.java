package zero.dividend.scraper;

import zero.dividend.model.Company;
import zero.dividend.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);

    ScrapedResult scrap(Company company);
}
