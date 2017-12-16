package ru.sfedu.messenger.model.dto;

/**
 *
 * @author Vovka
 */
public class TopicDTO implements WithID {
    
    private long id;
    private String title;

    public TopicDTO() {
    }

    public TopicDTO(long id, String title) {
        this.id = id;
        this.title = title;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    
    @Override
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    
    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TopicDTO{" + "id=" + id + ", title=" + title + '}';
    }
}
