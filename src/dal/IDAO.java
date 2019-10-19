package dal;

import java.util.List;

public interface IDAO<E> {

    public void create(E objet);
    public List<E> findAll();
}