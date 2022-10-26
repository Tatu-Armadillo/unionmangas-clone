package br.com.clone.unionmangas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.reader.ReaderDto;
import br.com.clone.unionmangas.model.Reader;
import br.com.clone.unionmangas.model.User;
import br.com.clone.unionmangas.repository.ReaderRepository;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public void create(ReaderDto readerDto, User user) {
        final Reader reader = new Reader(readerDto.getEmail(), 0, readerDto.getBirthdate(), user);
        this.readerRepository.save(reader);
    }

}
