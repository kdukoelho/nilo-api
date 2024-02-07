package rabbit.niloapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rabbit.niloapi.dto.entry.EntryRequestDTO;
import rabbit.niloapi.dto.entry.EntryResponseDTO;
import rabbit.niloapi.model.Entry;
import rabbit.niloapi.repository.EntryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    private EntryRepository repository;

    public List<EntryResponseDTO> findAll(){
        List<EntryResponseDTO> entryResponseDTOList = new ArrayList<>();
        this.repository.findAll().forEach(entry -> entryResponseDTOList.add(new EntryResponseDTO(entry)));
        return entryResponseDTOList;
    }

    public EntryResponseDTO findById(String id){
        Optional<Entry> entry = this.repository.findById(id);
        return new EntryResponseDTO(
                entry.orElseThrow(() -> new RuntimeException(
                        String.format("User id %s not found.", id))));
    }

    @Transactional
    public EntryResponseDTO create(EntryRequestDTO entryRequestDTO){
        Entry entry = new Entry(entryRequestDTO);
        entry.setPassword(new BCryptPasswordEncoder().encode(entryRequestDTO.password()));
        this.repository.save(entry);
        return new EntryResponseDTO(entry);
    }

    @Transactional
    public EntryResponseDTO update(EntryRequestDTO entryRequestDTO, String id){
        Entry entry = new Entry(entryRequestDTO);
        entry.setId(id);
        entry.setPassword(new BCryptPasswordEncoder().encode(entryRequestDTO.password()));
        this.repository.save(entry);
        return new EntryResponseDTO(entry);
    }

    @Transactional
    public void deleteById(String id){
        this.repository.deleteById(id);
    }
}
