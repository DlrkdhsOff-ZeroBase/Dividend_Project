package zero.dividend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @GetMapping("/company/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        return null;
    }

    @GetMapping("/company")
    public ResponseEntity<?> searchCompany() {
        return null;
    }

    @PostMapping("/company")
    public ResponseEntity<?> addCompany() {
        return null;
    }

    @DeleteMapping("/company")
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
