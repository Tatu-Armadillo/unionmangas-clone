package br.com.clone.unionmangas.service;

import br.com.clone.unionmangas.model.Scan;
import br.com.clone.unionmangas.repository.ScanRepository;
import org.springframework.stereotype.Service;

@Service
public class ScanService {
    
    private final ScanRepository scanRepository;

    public ScanService(final ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    public Scan create() {
        this.scanRepository.save(null);
        return null;
    }
    

}
