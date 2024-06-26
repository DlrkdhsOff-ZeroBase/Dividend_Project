package zero.dividend.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FinanceController {

    @GetMapping("/finance/dividend/{companyName}")
    public ResponseEntity<?> searchFinance(@PathVariable String companyName) {
        return null;
    }
}
