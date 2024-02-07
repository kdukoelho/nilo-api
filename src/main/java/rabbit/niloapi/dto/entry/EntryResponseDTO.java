package rabbit.niloapi.dto.entry;

import rabbit.niloapi.model.Entry;

public record EntryResponseDTO(String id, String title, String url, String login, String password) {
    public EntryResponseDTO(Entry entry){
        this(entry.getId(), entry.getTitle(), entry.getUrl(), entry.getLogin(), entry.getPassword());
    }
}
